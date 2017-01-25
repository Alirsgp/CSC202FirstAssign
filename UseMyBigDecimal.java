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
	private static boolean passFirstCheck = false;

	public static void main (String [] args) {

		
		while(correctNumberEntered == false) {
			System.out.println("Enter a whole or decimal number, with up to 100 digits, \n to be added or substracted by another whole or decimal number with up to 100 digits");
		try {
		System.out.println("Enter your first number");
		firstNumberToUse = myScanner.nextLine();
		System.out.println("Enter your second number");
		secondNumberToUse = myScanner.nextLine();
		for(int i = 0; i < firstNumberToUse.length() - 1; i++) {
			if(firstNumberToUse.substring(i, i + 1).equals(".")) {
				counterFirstNumber++;
			}
		}
		for(int j = 0; j < secondNumberToUse.length() - 1; j++) {
			if(secondNumberToUse.substring(j, j + 1).equals(".")) {
				counterSecondNumber++;
			}
		}
		if(checkNum(firstNumberToUse) && checkNum(secondNumberToUse)) {
			passFirstCheck = true;
		} else {
			System.out.println("First check not passed");
			//System.out.println("Invalid input. Only use numbers, with a limit of 100, and just one decimal. Try Again.");
		}
		if(passFirstCheck) {
			if(counterFirstNumber <= 1 && counterSecondNumber <= 1) {
			correctNumberEntered = true;
			} else {
				System.out.println("Too many decimals inputted");
			}
		}

		} catch(Exception e) {
			System.out.println("Please enter a valid number, with one decimal point, and only up to 100 digits total");
		}
	}

	System.out.println("Your first number is " + firstNumberToUse);
	System.out.println("Your second number is " + secondNumberToUse);

	}

	public static boolean checkNum(String str) {
		if(str.length() < 1) {
			return true;
		}
		if(!str.substring(0, 1).equals("0") || !str.substring(0, 1).equals("1") || !str.substring(0, 1).equals("2") || !str.substring(0, 1).equals("3")
		|| !str.substring(0, 1).equals("4") || !str.substring(0, 1).equals("5") || !str.substring(0, 1).equals("6") || !str.substring(0, 1).equals("7")
		|| !str.substring(0, 1).equals("8") || !str.substring(0, 1).equals("9") || !str.substring(0, 1).equals(".")) {
			return false;
		}
		return checkNum(str.substring(1));
	}
}