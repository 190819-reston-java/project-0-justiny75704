package com.revature.model;

import org.junit.Test;

import com.revature.exception.MinimumBalanceException;
import com.revature.exception.NegativeAmountException;
import com.revature.exception.WithdrawalLimitException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class AccountTest {
	private static CheckingAccount testCheckingAccount;
	private static SavingsAccount testSavingsAccount1;
	private static SavingsAccount testSavingsAccount2;
	
	@BeforeClass
	public static void setUpTestAccount() {
		testCheckingAccount = new CheckingAccount("Justin123", 1, 5000);
		testSavingsAccount1 = new SavingsAccount("Justin123", 1, 5000, 0);
		testSavingsAccount2 = new SavingsAccount("Justin123", 1, 5000, 6);
	}
	
	@Test
	public void testCheckingAccountDepositMethodWithValidArgument() {
		Assert.assertTrue(testCheckingAccount.deposit(100.00) == true);
	}
	
	@Test(expected = NegativeAmountException.class)
	public void testCheckingAccountDepositMethodWithNegativeArgument() {
		testCheckingAccount.deposit(-100.00);
	}
	
	@Test(expected = NegativeAmountException.class)
	public void testSavingsAccountDepositMethodWithNegativeArgument() {
		testSavingsAccount1.deposit(-100.00);
	}
	
	@Test
	public void testSavingsAccountWithdrawMethodWithValidArgument() {
		Assert.assertTrue(testSavingsAccount1.deposit(1000.00) == true);
	}
	
	@Test(expected = MinimumBalanceException.class)
	public void testSavingsAccountWithdrawMethodWithLargeAmountArgument() {
		testSavingsAccount1.withdraw(6000.00);
	}
	
	@Test(expected = WithdrawalLimitException.class)
	public void testSavingsAccountWithdrawMethodBeyondWithdrawalLimit() {
		testSavingsAccount2.withdraw(50.00);
	}
	
	@AfterClass
	public static void tearDownTestAccount() {
		testCheckingAccount = null;
		testSavingsAccount1 = null;
		testSavingsAccount2 = null;
	}
}
