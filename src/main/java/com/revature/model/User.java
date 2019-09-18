package com.revature.model;

public class User {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	
	public User (String userId, String password, String firstName, String lastName, CheckingAccount ca, SavingsAccount sa) {
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.checkingAccount = ca;
		this.savingsAccount = sa;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserID(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}
	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}
	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}
	
}
