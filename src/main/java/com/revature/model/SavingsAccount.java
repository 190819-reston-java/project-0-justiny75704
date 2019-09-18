package com.revature.model;

import com.revature.exception.MinimumBalanceException;
import com.revature.exception.NegativeAmountException;
import com.revature.exception.WithdrawalLimitException;

public class SavingsAccount extends Account {
	private int withdrawalLimit = 6;
	private int currentWithdrawalLimit = 0;
	private double minimumBalance = 25.00;
	
	public SavingsAccount(String userId, int accountId, double balance, int withdrawal) {
		super(userId, accountId, balance);
		this.setCurrentWithdrawalLimit(withdrawal);
	}

	public int getWithdrawalLimit() {
		return withdrawalLimit;
	}

	public void setWithdrawalLimit(int withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}
	
	public int getCurrentWithdrawalLimit() {
		return currentWithdrawalLimit;
	}

	public void setCurrentWithdrawalLimit(int currentWithdrawalLimit) {
		this.currentWithdrawalLimit = currentWithdrawalLimit;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	@Override
	public boolean withdraw(double amount) throws MinimumBalanceException, WithdrawalLimitException{
		if (this.getCurrentWithdrawalLimit() < this.getWithdrawalLimit()) {
			if (this.getBalance() <= this.getMinimumBalance()) {
				throw new MinimumBalanceException();
			} else if (this.getBalance() - amount <= this.getMinimumBalance()) {
				throw new MinimumBalanceException();
			} else {
				this.setBalance(this.getBalance() - amount);
				this.setCurrentWithdrawalLimit(this.getCurrentWithdrawalLimit() + 1);
				return true;
			}
		} else {
			throw new WithdrawalLimitException();
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
