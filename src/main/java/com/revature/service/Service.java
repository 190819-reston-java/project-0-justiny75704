package com.revature.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.revature.model.CheckingAccount;
import com.revature.model.SavingsAccount;
import com.revature.model.User;
import com.revature.repository.BankingAppImplPJDBC;

public class Service {
	private HashMap<String, String> loginInfo = new HashMap<String, String>(); //userId is the key, which points to password
	private HashMap<String, User> users = new HashMap<String, User>(); //userId is the key, which points to user object
	private BankingAppImplPJDBC bankingDAO = new BankingAppImplPJDBC();

	public String authenticate(String userId) {
		if (loginInfo.containsKey(userId)) {
			return loginInfo.get(userId);
		} else {
			return null;
		}
	}
	
	public void insertInfo() {
		ArrayList<User> clients = bankingDAO.getClients();
		for (User ele : clients) {
			loginInfo.put(ele.getUserId(), ele.getPassword());
		}
	}
	
	public void insertUser() {
		ArrayList<User> clients = bankingDAO.getClients();
		for (User ele : clients) {
			users.put(ele.getUserId(), ele);
		}
	}
	
	public User getUser(String userId) {
		return users.get(userId);
	}
	
	public void updateCheckingBalance(CheckingAccount account) {
		bankingDAO.updateCheckingAccount(account);
	}
	
	public void updateSavingsBalance(SavingsAccount account) {
		bankingDAO.updateSavingsAccount(account);
	}
}
