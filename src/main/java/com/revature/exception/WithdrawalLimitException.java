package com.revature.exception;

public class WithdrawalLimitException extends RuntimeException {
	public WithdrawalLimitException() {
		this("Maximum amount of withdrawals reached. Withdrawal failed.");
	}
	
	public WithdrawalLimitException(String m) {
		super(m);
	}
}
