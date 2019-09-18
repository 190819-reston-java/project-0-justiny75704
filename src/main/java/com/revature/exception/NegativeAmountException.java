package com.revature.exception;

public class NegativeAmountException extends IllegalArgumentException {
	public NegativeAmountException() {
		this("You cannot deposit a number less than or equal to zero.");
	}
	
	public NegativeAmountException(String m) {
		super(m);
	}
}
