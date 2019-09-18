package com.revature.model;

import com.revature.exception.NegativeAmountException;

public class CheckingAccount extends Account {
	private double overdraftFee = 35.00;
	
	public CheckingAccount(String userId, int accountId, double balance) {
		super(userId, accountId, balance);
	}

	public double getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(double overdraftFee) {
		this.overdraftFee = overdraftFee;
	}

	@Override
	public boolean withdraw(double amount) {
		if (this.getBalance() <= 0 || amount > this.getBalance()) {
			this.setBalance(this.getBalance() - (amount + this.getOverdraftFee()));
			System.out.println("Overdraft fee applied.");
			return true;
		} else {
			this.setBalance(this.getBalance() - amount);
			return true;
		}
		
	}

	@Override
	public boolean deposit(double amount) throws NegativeAmountException{
		if (amount <= 0) {
			throw new NegativeAmountException();
		} else {
			this.setBalance(this.getBalance() + amount);
			return true;
		}
	}
	
	
}
