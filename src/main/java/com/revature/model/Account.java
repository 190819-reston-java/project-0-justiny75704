package com.revature.model;

public abstract class Account implements Transaction{
	private String userId;
	private int accountId;
	private double balance;
	
	public Account (String userId, int accountId, double balance) {
		this.userId = userId;
		this.accountId = accountId;
		this.balance = balance;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserID(String userID) {
		this.userId = userID;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
