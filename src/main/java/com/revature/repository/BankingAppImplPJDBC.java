package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.model.CheckingAccount;
import com.revature.model.SavingsAccount;
import com.revature.model.User;

public class BankingAppImplPJDBC implements BankingAppDAO {

	@Override
	public ArrayList<User> getClients() {
		ArrayList<User> clients = new ArrayList<User>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection conn = ConnectionUtility.getConnection();
		
		try {
			statement = conn.prepareStatement("SELECT * FROM client"
					+ " INNER JOIN checking_account ON client.id = checking_account.client_id"
					+ " INNER JOIN savings_account ON client.id = savings_account.client_id;");
			
			if (statement.execute()) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					clients.add(new User(
							resultSet.getString("id"),
							resultSet.getString("password"),
							resultSet.getString("first_name"),
							resultSet.getString("last_name"),
							new CheckingAccount(resultSet.getString("client_id"), resultSet.getInt("checking_id"), resultSet.getDouble("checking_balance")),
							new SavingsAccount(resultSet.getString("client_id"), resultSet.getInt("savings_id"), resultSet.getDouble("savings_balance"), resultSet.getInt("curr_withdrawal_limit"))
							));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet, statement, conn);
		}
		return clients;
	}

	@Override
	public boolean updateCheckingAccount(CheckingAccount checkingAccount) {
		Connection conn = ConnectionUtility.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement("UPDATE checking_account SET checking_balance = ? WHERE checking_id = ?");
			statement.setDouble(1, checkingAccount.getBalance());
			statement.setInt(2, checkingAccount.getAccountId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(statement, conn);
		}
		return true;
	}
	
	@Override
	public boolean updateSavingsAccount(SavingsAccount savingsAccount) {
		Connection conn = ConnectionUtility.getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = conn.prepareStatement("UPDATE savings_account SET savings_balance = ?, curr_withdrawal_limit =? WHERE savings_id = ?");
			statement.setDouble(1, savingsAccount.getBalance());
			statement.setDouble(2, savingsAccount.getCurrentWithdrawalLimit());
			statement.setInt(3, savingsAccount.getAccountId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(statement, conn);
		}
		return true;
	}

}
