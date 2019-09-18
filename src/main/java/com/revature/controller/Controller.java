package com.revature.controller;
// make a new class + method for answer if statements
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.CheckingAccount;
import com.revature.model.SavingsAccount;
import com.revature.model.User;
import com.revature.service.Service;

public class Controller {
	private Scanner sc = new Scanner(System.in);
	private Service service = new Service();
	public static Logger logger = Logger.getLogger(Controller.class);
	
	public void verifyUserId() {
		logger.info("Begin populating banking application with database information.");
		service.insertInfo();
		service.insertUser();
		logger.info("Begin user idenification authentication.");
		System.out.print("Please enter your user identification: ");
		String userId = sc.nextLine();
		
		if (service.authenticate(userId) != null) {
			logger.info("User identification exists within the database.");
			logger.info("Begin user password authentication.");
			this.verifyPassword(userId);
		} else {
			logger.debug("User identification " + userId + " was not found within the database.");
			System.out.print("User indentification incorrect. Would you like to try again? (Yes/No): ");
			String again = sc.nextLine();
			if (again.equalsIgnoreCase("yes")) {
				this.verifyUserId();
				return;
			} else if(again.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + again + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.verifyUserId();
				return;
			}
		}
	}

	public void verifyPassword(String userId) {
		System.out.print("Please enter your password: ");
		String password = sc.nextLine();
		
		if (password.equals(service.authenticate(userId))) {
			logger.info("User identification and password successfully correlate.");
			System.out.println("Login successful.");
			logger.info("Executing main menu.");
			this.accountManager(userId);
		} else {
			logger.debug("User password " + password + " does not correlate with user identification.");
			System.out.print("Password incorrect. Would you like to try again? (Yes/No): ");
			String again = sc.nextLine();
			if (again.equalsIgnoreCase("yes")) {
				this.verifyPassword(userId);
				return;
			} else if(again.equalsIgnoreCase("no")) {
				System.out.println("Returning to user identification.");
				this.verifyUserId();
				return;
			} else {
				logger.debug("User input " + again + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.verifyPassword(userId);
				return;
			}
		}
	}

	public void accountManager(String userId) {
		User user = service.getUser(userId);
		System.out.println("Welcome to the main menu, " + user.getFirstName() + " " + user.getLastName() + ". What would you like to do?");
		System.out.print("Enter 'C' to access your checking account, 'S' to access your savings account, or 'L' to log out: ");
		String choice = sc.nextLine();
		
		if (choice.equalsIgnoreCase("C")) {
			if (user.getCheckingAccount() != null) {
				logger.info("User's checking account successfully discovered.");
				this.checkingAccountManager(user.getCheckingAccount());
			} else {
				logger.error("Cannot discover user's checking account within the database.");
				System.out.println("It appears you do not have a checking account.");
				System.out.print("Would you like to access a different account? (Yes/No): ");
				String answer = sc.nextLine();
				if (answer.equalsIgnoreCase("yes")) {
					this.accountManager(userId);
					return;
				} else if (answer.equalsIgnoreCase("no")) {
					System.out.println("Logging out. Goodbye.");
					System.exit(0);
				} else {
					logger.debug("User input " + answer + " was not recognized.");
					System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
					this.accountManager(userId);
					return;
				}
			}
		} else if (choice.equalsIgnoreCase("S")) {
			if (user.getSavingsAccount() != null) {
				logger.info("User's savings account successfully discovered.");
				this.savingsAccountManager(user.getSavingsAccount());
			} else {
				logger.error("Cannot discover user's savings account within the database.");
				System.out.println("It appears you do not have a savings account.");
				System.out.print("Would you like to access a different account? (Yes/No): ");
				String answer = sc.nextLine();
				if (answer.equalsIgnoreCase("yes")) {
					this.accountManager(userId);
					return;
				} else if (answer.equalsIgnoreCase("no")) {
					System.out.println("Logging out. Goodbye.");
					System.exit(0);
				} else {
					logger.debug("User input " + answer + " was not recognized.");
					System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
					this.accountManager(userId);
					return;
				}
			}
		} else if (choice.equalsIgnoreCase("L")) {
			System.out.println("Logging out. Goodbye.");
			System.exit(0);
		} else {
			logger.debug("User input " + choice + " was not recognized.");
			System.out.print("You did not enter either 'C', 'S', or 'L'. Please try again.");
			this.accountManager(userId);
			return;
		}
	}
	
	public void checkingAccountManager(CheckingAccount checkingAccount) {
		System.out.println("What would you like to do with your checking account?");
		System.out.print("Enter 'V' to view your balance, 'O' to view overdraft fees, 'W' to withdraw funds, or 'D' to deposit funds, 'R' to return to the main menu, or 'L' to log out: ");
		String choice = sc.nextLine().toUpperCase();
		
		if (choice.equalsIgnoreCase("V")) {
			System.out.println("Current balance: $" + checkingAccount.getBalance());
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.checkingAccountManager(checkingAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.checkingAccountManager(checkingAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("O")) {
			System.out.println("An overdraft fee of $" + checkingAccount.getOverdraftFee() + " is applied to every withdrawal when the current balance is in a deficit.");
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.checkingAccountManager(checkingAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.checkingAccountManager(checkingAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("W")) {
			System.out.print("Please enter the amount you wish to withdraw: $");
			double amount = sc.nextDouble();
			sc.nextLine();
			if(checkingAccount.withdraw(amount)) {
				service.updateCheckingBalance(checkingAccount);
				System.out.println("Withdrawal successful.");
			} else {
				logger.fatal("Withdrawal unsuccessful. Throwing custom exception.");
			}
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.checkingAccountManager(checkingAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.checkingAccountManager(checkingAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("D")) {
			System.out.print("Please enter the amount you wish to deposit: $");
			double amount = sc.nextDouble();
			sc.nextLine();
			if (checkingAccount.deposit(amount)) {
				service.updateCheckingBalance(checkingAccount);
				System.out.println("Deposit successful.");
			} else {
				logger.fatal("Deposit unsuccessful. Throwing custom exception.");
			}
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.checkingAccountManager(checkingAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.checkingAccountManager(checkingAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("R")) {
			this.accountManager(checkingAccount.getUserId());
			return;
		} else if (choice.equalsIgnoreCase("L")) {
			System.out.println("Logging out. Goodbye.");
			System.exit(0);
		} else {
			logger.debug("User input " + choice + " was not recognized.");
			System.out.print("You did not enter either 'V', 'O', 'W' , 'D' 'R' or 'L'. Please try again.");
			this.checkingAccountManager(checkingAccount);
			return;
		}
	}
	
	public void savingsAccountManager(SavingsAccount savingsAccount) {
		System.out.println("What would you like to do with your savings account?");
		System.out.print("Enter 'V' to view your balance, 'M' to view your current amount of withdrawals, 'W' to withdraw funds, or 'D' to deposit funds, 'R' to return to the main menu, or 'L' to log out: ");
		String choice = sc.nextLine().toUpperCase();
		
		if (choice.equalsIgnoreCase("V")) {
			System.out.println("Current balance: $" + savingsAccount.getBalance());
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.savingsAccountManager(savingsAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.savingsAccountManager(savingsAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("M")) {
			System.out.println("Only a maximum of " + savingsAccount.getWithdrawalLimit() + " withdrawals are allowed per month.");
			System.out.println("You currently have " + (savingsAccount.getWithdrawalLimit() - savingsAccount.getCurrentWithdrawalLimit()) + " left.");
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.savingsAccountManager(savingsAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.savingsAccountManager(savingsAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("W")) {
			System.out.print("Please enter the amount you wish to withdraw: $");
			double amount = sc.nextDouble();
			sc.nextLine();
			if (savingsAccount.withdraw(amount)) {
				service.updateSavingsBalance(savingsAccount);
				System.out.println("Withdrawal successful.");
			} else {
				logger.fatal("Withdrawal unsuccessful. Throwing custom exception.");
			}
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.savingsAccountManager(savingsAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.savingsAccountManager(savingsAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("D")) {
			System.out.print("Please enter the amount you wish to deposit: $");
			double amount = sc.nextDouble();
			sc.nextLine();
			if (savingsAccount.deposit(amount)) {
				service.updateSavingsBalance(savingsAccount);
				System.out.println("Deposit successful.");
			} else {
				logger.fatal("Deposit unsuccessful. Throwing custom exception.");
			}
			System.out.print("Would you like to do something else? (Yes/No): ");
			String answer = sc.nextLine();
			if (answer.equalsIgnoreCase("yes")) {
				this.savingsAccountManager(savingsAccount);
				return;
			} else if (answer.equalsIgnoreCase("no")) {
				System.out.println("Logging out. Goodbye.");
				System.exit(0);
			} else {
				logger.debug("User input " + answer + " was not recognized.");
				System.out.println("You did not enter either 'Yes' or 'No'. Please try again.");
				this.savingsAccountManager(savingsAccount);
				return;
			}
		} else if (choice.equalsIgnoreCase("R")) {
			this.accountManager(savingsAccount.getUserId());
			return;
		} else if (choice.equalsIgnoreCase("L")) {
			System.out.println("Logging out. Goodbye.");
			System.exit(0);
		} else {
			logger.debug("User input " + choice + " was not recognized.");
			System.out.print("You did not enter either 'V', 'O', 'W' , 'D' 'R' or 'L'. Please try again.");
			this.savingsAccountManager(savingsAccount);
			return;
		}
	}
}
