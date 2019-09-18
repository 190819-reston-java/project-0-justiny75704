package com.revature.exception;

public class MinimumBalanceException extends RuntimeException {
	public MinimumBalanceException() {
		this("Minimum balance of $25.00 reached. Withdrawal failed.");
	}
	
	public MinimumBalanceException(String m) {
		super(m);
	}
}
