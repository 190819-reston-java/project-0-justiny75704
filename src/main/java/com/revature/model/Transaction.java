package com.revature.model;

public interface Transaction {
	public boolean withdraw(double amount);
	public boolean deposit(double amount);
}
