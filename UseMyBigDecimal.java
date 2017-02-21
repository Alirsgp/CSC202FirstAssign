//Ali Mohammadian CSC 202 Assignment 1
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class UseMyBigDecimal {
	private static boolean correctNumberEntered = false;
	private static int counterFirstNumber = 0;
	private static int counterSecondNumber = 0;
	private static Scanner myScanner = new Scanner(System.in);
	private static String firstNumberToUse = "";
	private static String secondNumberToUse = "";
	private static boolean passFirstCheckFirstEnter = false;
	private static boolean passSecondCheckSecondEnter = false;
	private static String addOrSub = "";
	private static boolean operationAnswer = false;
	

	public static void main (String [] args) {

		//Create a object for MyBigDecimal class that implemented NumericalOperand interface methods add(object) and subtract(object) and used them
	   	MyBigDecimal object = new MyBigDecimal();
	   	
		
		while(correctNumberEntered == false) {
		// If user entry fails, and more than one decimal inputted, resets # for counter to 0 again for next try.
		counterFirstNumber = 0;
		counterSecondNumber = 0;
			System.out.println("Enter a whole or decimal number, with up to 100 digits, \n to be added or substracted by another whole or decimal number with up to 100 digits");
		try {
		System.out.println("Enter your first number");
		firstNumberToUse = myScanner.nextLine();
		for(int i = 0; i < firstNumberToUse.length() - 1; i++) {
			if(firstNumberToUse.substring(i, i + 1).equals(".")) {
				counterFirstNumber++;
				}
			}
		if(counterFirstNumber <= 1 && object.checkNumIterate(firstNumberToUse)) {
			passSecondCheckSecondEnter = true;
			} 
		if(passSecondCheckSecondEnter == false) {
			System.out.println("Invalid input");
		}
		if(passSecondCheckSecondEnter) {
		System.out.println("Enter your second number");
		secondNumberToUse = myScanner.nextLine();
		}
		//Following two for loops check to make sure only one decimal is used. If counterFirstNumber and counterSecondNumber 
		// are more than 1 for either inputs, it will print out Invalid input and the while loop will loop again.
		for(int j = 0; j < secondNumberToUse.length() - 1; j++) {
			if(secondNumberToUse.substring(j, j + 1).equals(".")) {
				counterSecondNumber++;
			}
		}

		if(object.checkNumIterate(secondNumberToUse) && counterSecondNumber <= 1) {
			correctNumberEntered = true;
		}
		if(correctNumberEntered == false && passSecondCheckSecondEnter == true) {
			System.out.println("First input was right, but second was wrong.");
		} else if(correctNumberEntered == false) {
			System.out.println("Try again");
		}

			} catch(Exception e) {
				System.out.println("Please enter a valid number, with one decimal point, and only up to 100 digits total");
		}
	}

		//Methods we call below are SEPARATE from the add() and subtract() methods we implemented. They serve the purpose of deriving the answer, the ones 
		// we call below serve the purpose of obtaining that answer and returning it via its own custom toString method.
		object.addOp(firstNumberToUse, secondNumberToUse);
		object.subOp(firstNumberToUse, secondNumberToUse);
		//Addition works, 

		//Our object's add/sub method (implemented from interface given, utilise the added method of addOp and subOp we called above, 
		//and return the answer value for respective sum/difference of NumericalOperand object, which has a toString method that returns the answers the user sees below
		//
		object.add(object);
		System.out.println(object.subtract(object));
		//We use both add() and subtract() from our NumericalOperand interface we implemented in MyBigDecimal.java, but since
		// we are returning the object as a toString with both our results in one toString (design request by professor), we 
		// only display calling the object once, (No need to show results twice) after we call the add(object) method, we call subtract(object) method 
		// and display both of our results as one message to user. 
		
	}
}
