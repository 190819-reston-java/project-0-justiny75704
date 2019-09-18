package com.revature.repository;

import java.util.ArrayList;

import com.revature.model.CheckingAccount;
import com.revature.model.SavingsAccount;
import com.revature.model.User;

public interface BankingAppDAO {
	ArrayList<User> getClients();
	boolean updateCheckingAccount(CheckingAccount checkingAccount);
	boolean updateSavingsAccount(SavingsAccount savingsAccount);
}
