//Ali Mohammadian - CSC 202 Assignment
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class MyBigDecimal implements NumericalOperand {
	private static ArrayList <String> arraylist1 = new ArrayList<String>();
	private static ArrayList <String> arraylist2 = new ArrayList<String>();
	private static ArrayList <String> arraylistFinal = new ArrayList<String>();
	private static ArrayList <String> arraylist1Sub = new ArrayList<String>();
	private static ArrayList <String> arraylist2Sub = new ArrayList<String>();
	private static ArrayList <String> arraylistFinalSub = new ArrayList<String>();
	private static String answerSum = "";
	private static String answerDifference = "";
	private static boolean moveOneOverDecimal = false;
	private static boolean moveOneOverDecimalSub = false;
	private static boolean isNegative = false;
	private static int countAfterFirstInputDecimal = 0;
	private static int countAfterSecondInputDecimal = 0;
	private static int countAfterFirstInputDecimalSub = 0;
	private static int countAfterSecondInputDecimalSub = 0;
	private static String temp;
	private static boolean checkMoveDecimalTwice = false;
	private static boolean checkMoveDecimalTwiceSub = false;
	private static int anotherCheckWhereFirstDecimal = 0;
	private static int anotherCheckWhereSecondDecimal = 0;
	private static String tempstr1 = "";
	private static String tempstr2 = "";
	private static String tempstr1Sub = "";
	private static String tempstr2Sub = "";
	private static boolean hasDec1 = false;
	private static boolean hasDec2 = false;
	private static boolean hasDec1Sub = false;
	private static boolean hasDec2Sub = false;
	private static boolean isANegative = false;
	private static boolean usedADecimalSub1 = false;
	private static boolean usedADecimalSub2 = false;
	private static double value1 = 0;
	private static double value2 = 0;
	private static double tempStrNum1 = 0;
	private static double tempStrNum2 = 0;
	private static boolean allZeroStr1 = true;
	private static boolean allZeroStr2 = true;
	private static boolean aDecimalSub1 = false;
	private static boolean aDecimalSub2 = false;

	//Define a new reference type, and cast operand to be a NumericalOperand type
	public NumericalOperand add(NumericalOperand operand) {
		NumericalOperand obj = operand;
		return obj;
		//We use this in UseMyBigDecimal.java to show addition result
	}

	public NumericalOperand subtract(NumericalOperand operand) {
		NumericalOperand obj = operand;
		return obj;
		//We use this in UseMyBigDecimal.java to show subtraction result
	}

	public String addOp(String str1, String str2) {
		tempstr1 = str1;
		tempstr2 = str2;

		//Create tempstr1/tempstr2 in case we want to evaluate them later

		int removeZero = 0;
		int removeZero2 = 0;

		//Code below is used in case user inputs a "." first.
		try {
		while((str1.substring(removeZero, removeZero + 1).equals("0")) && removeZero < str1.length()) {
			str1 = str1.substring(1);
		}

		while((str2.substring(removeZero2, removeZero2 + 1).equals("0")) && removeZero2 < str2.length())  {
			str2 = str2.substring(1);
		}
		} catch(Exception e) {
			answerSum = "0";
			return "0";
		}
		if(str1.substring(0, 1).equals(".")) {
			str1 = "0" + str1;
		}

		if(str2.substring(0, 1).equals(".")) {
			str2 = "0" + str2;
			
		}

		for(int i = 0; i < str1.length(); i++) {
			if(str1.substring(i, i +1).equals(".")) {
				hasDec1 = true;
			}
		}

		for(int i = 0; i < str2.length(); i++) {
			if(str2.substring(i, i +1).equals(".")) {
				hasDec2 = true;
			}
		}

		if(hasDec1 == true && hasDec2 == false) {
			str2 = str2 + ".0";
		}

		if(hasDec1 == false && hasDec2 == true) {
			str1 = str1 + ".0";
		}
		//Code above makes sure that if a decimal was found in either input, both inputs will have a decimal for computing sake

		int indexOfFirstDecimalOfFirstString = 0;
		int indexOfsecondDecimalOfSecondString = 0;
		//Two ints above are for checking to see where the decimal was placed.

		for(int i = 0; i < str1.length(); i++) {
			arraylist1.add(str1.substring(i, i + 1));
		}

		//Above code adds the first string to ArrayList1

		for(int i = 0; i < str2.length(); i++) {
			arraylist2.add(str2.substring(i, i + 1));
		}

		//Above code adds the second string to ArrayList2

		for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				for(int j = i; j < str1.length(); j++) {
					countAfterFirstInputDecimal++;
				}
				countAfterFirstInputDecimal--;
			}
		}

		for(int i = 0; i < str2.length() - 1; i++) {
			if(str2.substring(i, i + 1).equals(".")) {
				for(int j = i; j < str2.length(); j++) {
					countAfterSecondInputDecimal++;
				}
				countAfterSecondInputDecimal--;
			}
		}

		//Above two for loops to see how many digits after finding the decimal, if there is a decimal.

		for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				indexOfFirstDecimalOfFirstString = i;
				arraylist1.remove(indexOfFirstDecimalOfFirstString);
				arraylist1.add(0, "0");
				//Remove decimal
			}
		}

		//Above code documents where the decimal point was for first string, below code does it for second string

		for(int i = 0; i < str2.length() - 1; i++) {
			if(str2.substring(i, i + 1).equals(".")) {
				indexOfsecondDecimalOfSecondString = i;
				arraylist2.remove(indexOfsecondDecimalOfSecondString);
				arraylist2.add(0, "0");
				//Remove decimal
			}
		}
		//Code below is for placeholder zeros
		arraylist1.add(0, "0");
		arraylist2.add(0, "0");
		arraylist1.add(0, "0");
		arraylist2.add(0, "0");

		//Adds insignificant zeros
		if(arraylist1.size() > arraylist2.size()) {
			while(arraylist2.size() < arraylist1.size()) {
				arraylist2.add(0, "0");
			}
		}

		if(arraylist2.size() > arraylist1.size()) {
			while(arraylist1.size() < arraylist2.size()) {
				arraylist1.add(0, "0");
			}
		}
		//Above 2 if statements for whole #, then next 2 above them is for decimals
		//Add 0 to first index for use of carry rule

		while(arraylistFinal.size() < arraylist1.size() && arraylistFinal.size() < arraylist2.size()) {
			arraylistFinal.add("0");
		}

		while(countAfterFirstInputDecimal > countAfterSecondInputDecimal) {
			arraylist2.add(arraylist2.size(), "0");
			arraylist2.remove(0);
			countAfterSecondInputDecimal++;
			
		}

		while(countAfterFirstInputDecimal < countAfterSecondInputDecimal) {
			arraylist1.add(arraylist1.size(), "0");
			arraylist1.remove(0);
			countAfterFirstInputDecimal++;
			
		}
		//Code above makes sure both inputs have same digits, by adding insignificant zeros
		//Reverse for loop as we go from right to left
			for(int i = arraylist1.size() - 1; i > 0; i--) {
			arraylistFinal.set(i, resultFinalArrayAdd(i));
			}

		//If single digit, adds a insignificant zero to front to avoid exception
		while(arraylistFinal.get(0).equals("0")) {
			arraylistFinal.remove(0);
		}
		
		if((arraylistFinal.size()) > str1.length() && (arraylistFinal.size())> str2.length()) {
			moveOneOverDecimal = true;
		}

		//Code below adds the decimal back in to show the final result
		if(indexOfFirstDecimalOfFirstString > 0 || indexOfsecondDecimalOfSecondString > 0) {
			if((str1.length() == str2.length()) && moveOneOverDecimal == true) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString + 1, ".");
			
		} else if ((str1.length() != str2.length() && moveOneOverDecimal == false) && (checkMoveDecimalTwice)) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString, ".");
			
		} else if(str1.length() > str2.length()) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString, ".");
			
		} else if(str2.length() > str1.length()) {
			arraylistFinal.add(indexOfsecondDecimalOfSecondString, ".");
			
		} else if((str1.length() == str2.length()) && moveOneOverDecimal == true) {
			
			arraylistFinal.add(indexOfFirstDecimalOfFirstString + 1, ".");
		} else if ((str1.length() == str2.length() && moveOneOverDecimal == false)) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString, ".");
			
		}  
		}	

		if(tempstr1.length() > 1 && tempstr2.length() > 1) {
			if((tempstr1.substring(0, 2).equals(".4") || tempstr1.substring(0, 2).equals(".3") || tempstr1.substring(0, 2).equals(".2") || tempstr1.substring(0, 2).equals(".1")) && (tempstr2.substring(0, 2).equals(".4") || 
				tempstr2.substring(0, 2).equals(".3") || tempstr2.substring(0, 2).equals(".2") || tempstr2.substring(0, 2).equals(".1"))) {
				arraylistFinal.remove(1);
				arraylistFinal.add(0, ".");
			}
		}

		//Code above is for edge cases to make sure decimal place is not mixed up because of indexOfFirstDecimalOfString being initialized to 0 and a number '.4' having same mark.

		String res1 = String.join("", arraylist1);
		String res2 = String.join("", arraylist2);
		String resFinal = String.join("", arraylistFinal);

		answerSum = resFinal;

		return resFinal;

	}

	//Code that was used to test to make sure arraylists converted properly to strings
	public String printArrayLists() {
		String res1 = String.join("", arraylist1);
		String res2 = String.join("", arraylist2);
        return res1 + ", " + res2;
	}
	//Method below processes the addition and does the carry rule as necessary (calls carry function for addition).
	public String resultFinalArrayAdd(int i) {

        		if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("0")) {
        			return "1";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("1")) {
        			return "2";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("2")) {
        			return "3";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("3")) {
        			return "4";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("4")) {
        			return "5";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("5")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("6")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("7")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("8")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("1") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("0")) {
        			return "0";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("1")) {
        			return "1";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("2")) {
        			return "2";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("3")) {
        			return "3";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("4")) {
        			return "4";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("5")) {
        			return "5";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("6")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("7")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("8")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("0") && arraylist2.get(i).equals("9")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("0")) {
        			return "2";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("1")) {
        			return "3";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("2")) {
        			return "4";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("3")) {
        			return "5";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("4")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("5")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("6")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("7")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("2") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("0")) {
        			return "3";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("1")) {
        			return "4";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("2")) {
        			return "5";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("3")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("4")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("5")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("6")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("3") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("0")) {
        			return "4";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("1")) {
        			return "5";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("2")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("3")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("4")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("5")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("6")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("4") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "3";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("0")) {
        			return "5";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("1")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("2")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("3")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("4")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("5")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("6")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "3";
        		} else if(arraylist1.get(i).equals("5") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "4";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("0")) {
        			return "6";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("1")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("2")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("3")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("4")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("5")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("6")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "3";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "4";
        		} else if(arraylist1.get(i).equals("6") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "5";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("0")) {
        			return "7";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("1")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("2")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("3")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("4")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("5")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("6")) {
        			carryRuleAddition(i);
        			return "3";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "4";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "5";
        		} else if(arraylist1.get(i).equals("7") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "6";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("0")) {
        			return "8";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("1")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("2")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("3")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("4")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("5")) {
        			carryRuleAddition(i);
        			return "3";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("6")) {
        			carryRuleAddition(i);
        			return "4";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "5";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "6";
        		} else if(arraylist1.get(i).equals("8") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "7";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("0")) {
        			return "9";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("1")) {
        			carryRuleAddition(i);
        			return "0";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("2")) {
        			carryRuleAddition(i);
        			return "1";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("3")) {
        			carryRuleAddition(i);
        			return "2";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("4")) {
        			carryRuleAddition(i);
        			return "3";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("5")) {
        			carryRuleAddition(i);
        			return "4";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("6")) {
        			carryRuleAddition(i);
        			return "5";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("7")) {
        			carryRuleAddition(i);
        			return "6";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("8")) {
        			carryRuleAddition(i);
        			return "7";
        		} else if(arraylist1.get(i).equals("9") && arraylist2.get(i).equals("9")) {
        			carryRuleAddition(i);
        			return "8";
        		} 

        		return "";
	}
	//Code below is used for carrying, accounts for the placeholder zeros we place in the front of our code


	public void carryRuleAddition(int i) {
		if(arraylist1.get(i - 1).equals("0")) {
			arraylist1.set(i - 1, "1");
		} else if(arraylist1.get(i - 1).equals("1")) {
			arraylist1.set(i - 1, "2");
		} else if(arraylist1.get(i - 1).equals("2")) {
			arraylist1.set(i - 1, "3");
		} else if(arraylist1.get(i - 1).equals("3")) {
			arraylist1.set(i - 1, "4");
		} else if(arraylist1.get(i - 1).equals("4")) {
			arraylist1.set(i - 1, "5");
		} else if(arraylist1.get(i - 1).equals("5")) {
			arraylist1.set(i - 1, "6");
		} else if(arraylist1.get(i - 1).equals("6")) {
			arraylist1.set(i - 1, "7");
		} else if(arraylist1.get(i - 1).equals("7")) {
			arraylist1.set(i - 1, "8");
		} else if(arraylist1.get(i - 1).equals("8")) {
			arraylist1.set(i - 1, "9");
		} else if(arraylist1.get(i - 1).equals("9")) {
			arraylist1.set(i - 1, "0");

			if(arraylist1.get(i - 2).equals("0")) {
				arraylist1.set(i - 2, "1");
			} else if(arraylist1.get(i - 2).equals("1")) {
				arraylist1.set(i - 2, "2");
			} else if(arraylist1.get(i - 2).equals("2")) {
				arraylist1.set(i - 2, "3");
			} else if(arraylist1.get(i - 2).equals("3")) {
				arraylist1.set(i - 2, "4");
			} else if(arraylist1.get(i - 2).equals("4")) {
				arraylist1.set(i - 2, "5");
			} else if(arraylist1.get(i - 2).equals("5")) {
				arraylist1.set(i - 2, "6");
			} else if(arraylist1.get(i - 2).equals("6")) {
				arraylist1.set(i - 2, "7");
			} else if(arraylist1.get(i - 2).equals("7")) {
				arraylist1.set(i - 2, "8");
			} else if(arraylist1.get(i - 2).equals("8")) {
				arraylist1.set(i - 2, "9");
			} else if(arraylist1.get(i - 2).equals("9")) {
				arraylist1.set(i - 2, "0");

				if(arraylist1.get(i - 3).equals("0")) {
				arraylist1.set(i - 3, "1");
			} else if(arraylist1.get(i - 3).equals("1")) {
				arraylist1.set(i - 3, "2");
			} else if(arraylist1.get(i - 3).equals("2")) {
				arraylist1.set(i - 3, "3");
			} else if(arraylist1.get(i - 3).equals("3")) {
				arraylist1.set(i - 3, "4");
			} else if(arraylist1.get(i - 3).equals("4")) {
				arraylist1.set(i - 3, "5");
			} else if(arraylist1.get(i - 3).equals("5")) {
				arraylist1.set(i - 3, "6");
			} else if(arraylist1.get(i - 3).equals("6")) {
				arraylist1.set(i - 3, "7");
			} else if(arraylist1.get(i - 3).equals("7")) {
				arraylist1.set(i - 3, "8");
			} else if(arraylist1.get(i - 3).equals("8")) {
				arraylist1.set(i - 3, "9");
			} else if(arraylist1.get(i - 3).equals("9")) {
				arraylist1.set(i - 3, "0");
				if(arraylist1.get(i - 4).equals("0")) {
				arraylist1.set(i - 4, "1");
			} else if(arraylist1.get(i - 4).equals("1")) {
				arraylist1.set(i - 3, "2");
			} else if(arraylist1.get(i - 4).equals("2")) {
				arraylist1.set(i - 4, "3");
			} else if(arraylist1.get(i - 4).equals("3")) {
				arraylist1.set(i - 4, "4");
			} else if(arraylist1.get(i - 4).equals("4")) {
				arraylist1.set(i - 4, "5");
			} else if(arraylist1.get(i - 4).equals("5")) {
				arraylist1.set(i - 4, "6");
			} else if(arraylist1.get(i - 4).equals("6")) {
				arraylist1.set(i - 4, "7");
			} else if(arraylist1.get(i - 4).equals("7")) {
				arraylist1.set(i - 4, "8");
			} else if(arraylist1.get(i - 4).equals("8")) {
				arraylist1.set(i - 4, "9");
			} else if(arraylist1.get(i - 4).equals("9")) {
				arraylist1.set(i - 4, "0");
				
			} 
			} 
			} 
		} 
		
		}
		// Checks to see if decimal should be placed one over
	public boolean moveOver() {
		if(arraylist1.get(0).equals("5") && arraylist2.get(0).equals("5")) {
			return true;
		} else if(arraylist1.get(0).equals("5") && arraylist2.get(0).equals("6")) {
			return true;
		} else if(arraylist1.get(0).equals("5") && arraylist2.get(0).equals("7")) {
			return true;
		} else if(arraylist1.get(0).equals("5") && arraylist2.get(0).equals("8")) {
			return true;
		} else if(arraylist1.get(0).equals("5") && arraylist2.get(0).equals("9")) {
			return true;
		} else if(arraylist1.get(0).equals("6") && arraylist2.get(0).equals("4")) {
			return true;
		} else if(arraylist1.get(0).equals("6") && arraylist2.get(0).equals("5")) {
			return true;
		} else if(arraylist1.get(0).equals("6") && arraylist2.get(0).equals("6")) {
			return true;
		} else if(arraylist1.get(0).equals("6") && arraylist2.get(0).equals("7")) {
			return true;
		} else if(arraylist1.get(0).equals("6") && arraylist2.get(0).equals("8")) {
			return true;
		} else if(arraylist1.get(0).equals("6") && arraylist2.get(0).equals("9")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("3")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("4")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("5")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("6")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("7")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("8")) {
			return true;
		} else if(arraylist1.get(0).equals("7") && arraylist2.get(0).equals("9")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("2")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("3")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("4")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("5")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("6")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("7")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("8")) {
			return true;
		} else if(arraylist1.get(0).equals("8") && arraylist2.get(0).equals("9")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("1")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("2")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("3")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("4")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("5")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("6")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("7")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("8")) {
			return true;
		} else if(arraylist1.get(0).equals("9") && arraylist2.get(0).equals("9")) {
			return true;
		} 

		return false;
	}
	//Above code is a method that can be used to see if we should move over one decimal place when re-adding our decimal

	//Below code introduces our subtraction method in which we operate on the two inputs
	public String subOp(String str1, String str2) {

		tempstr1Sub = str1;
		tempstr2Sub = str2;

		if(str1.substring(0, 1).equals(".")) {
			str1 = "0" + str1;
		}

		if(str2.substring(0, 1).equals(".")) {
			str2 = "0" + str2;
			
		}

		if(str1.equals(str2)) {
			answerDifference = "0";
			return "0";
		}

		for(int i = 0; i < str1.length(); i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				hasDec1Sub = true;
			}
		}

		for(int i = 0; i < str2.length(); i++) {
			if(str2.substring(i, i +1).equals(".")) {
				hasDec2Sub = true;
			}
		}

		if(hasDec1Sub == true && hasDec2Sub == false) {
			str2 = str2 + ".0";
		}

		if(hasDec1Sub == false && hasDec2Sub == true) {
			str1 = str1 + ".0";
		}
		//If either have decimals, make both have decimals, as shown above


		if(isNegativeChecker(str1, str2)) {
			isANegative = true;
		}
		//If it is a negative, use this code to add a "-" sign.

		if(biggerOnTop(str1, str2)) { 
			String myTemp = str1;
			str1 = str2;
			str2 = myTemp;
		}
		//Above code makes sure bigger number always comes first when operating.
	
		//Code below is used in case user inputs a "." first.
		if(str1.substring(0, 1).equals(".")) {
			str1 = "0" + str1;
		}

		if(str2.substring(0, 1).equals(".")) {
			str2 = "0" + str2;
		}

		/***
		The following code is used to initialize the location of each String's decimal place, so that it may be appended
		at the end of the program.
		***/

		int indexOfFirstDecimalOfFirstStringSub = 0;
		int indexOfsecondDecimalOfSecondStringSub = 0;

		//Takes our two strings and makes an ArrayList out of them.

		for(int i = 0; i < str1.length(); i++) {
			arraylist1Sub.add(str1.substring(i, i + 1));
		}

		//Above code adds the first string to arraylist1Sub, below adds to second string to arraylist2Sub

		for(int i = 0; i < str2.length(); i++) {
			arraylist2Sub.add(str2.substring(i, i + 1));
		}

		for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				for(int j = i; j < str1.length(); j++) {
					countAfterFirstInputDecimalSub++;
				}
				countAfterFirstInputDecimalSub--;
			}
		}

		for(int i = 0; i < str2.length() - 1; i++) {
			if(str2.substring(i, i + 1).equals(".")) {
				for(int j = i; j < str2.length(); j++) {
					countAfterSecondInputDecimalSub++;
				}
				countAfterSecondInputDecimalSub--;
			}
		}

		//Above code looks at where the decimal point was after the first/second input, if there was a decimal

		for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				indexOfFirstDecimalOfFirstStringSub = i;
				arraylist1Sub.remove(indexOfFirstDecimalOfFirstStringSub);
				arraylist1Sub.add(0, "0");
				//Remove decimal
			}
		}

		//Above code documents where the decimal point was for first string and removes it, below code does it for second string

		for(int i = 0; i < str2.length() - 1; i++) {
			if(str2.substring(i, i + 1).equals(".")) {
				indexOfsecondDecimalOfSecondStringSub = i;
				arraylist2Sub.remove(indexOfsecondDecimalOfSecondStringSub);
				arraylist2Sub.add(0, "0");
				//Remove decimal
			}
		}
		arraylist1Sub.add(0, "0");
		arraylist2Sub.add(0, "0");
		arraylist1Sub.add(0, "0");
		arraylist2Sub.add(0, "0");

		//Placeholder zeros
		
		
		//Adds placeholder zeros to make sure sizes are the same
			if(arraylist1Sub.size() > arraylist2Sub.size()) {
			while(arraylist2Sub.size() < arraylist1Sub.size()) {
				arraylist2Sub.add(0, "0");
			}
		}

		if(arraylist2Sub.size() > arraylist1Sub.size()) {
			while(arraylist1Sub.size() < arraylist2Sub.size()) {
				arraylist1Sub.add(0, "0");
			}
		}

		while(arraylistFinalSub.size() < arraylist1Sub.size() && arraylistFinalSub.size() < arraylist2Sub.size()) {
			arraylistFinalSub.add("0");
		}

		while(countAfterFirstInputDecimalSub > countAfterSecondInputDecimalSub) {
			arraylist2Sub.add(arraylist2Sub.size(), "0");
			arraylist2Sub.remove(0);
			countAfterSecondInputDecimalSub++;
			
		}

		while(countAfterFirstInputDecimalSub < countAfterSecondInputDecimalSub) {
			arraylist1Sub.add(arraylist1Sub.size(), "0");
			arraylist1Sub.remove(0);
			countAfterFirstInputDecimalSub++;
			
		}
		
			for(int i = arraylist1Sub.size() - 1; i > 0; i--) {
			arraylistFinalSub.set(i, resultFinalArraySubtract(i));
			}
		//Reverse for-loop above as we iterate from right to left with addition/subtraction using carry rule

		//If single digit, adds a insignificant zero to front
		while(arraylistFinalSub.get(0).equals("0")) {
			arraylistFinalSub.remove(0);
		}

		//Below code re-adds the decimal before showing final output.		
		if((arraylistFinalSub.size()) < str1.length() && (arraylistFinalSub.size()) < str2.length()) {
			moveOneOverDecimalSub = true;
		}

		if(indexOfFirstDecimalOfFirstStringSub > 0 || indexOfsecondDecimalOfSecondStringSub > 0) {
			if((str1.length() == str2.length()) && moveOneOverDecimalSub == true) {
			arraylistFinalSub.add(indexOfFirstDecimalOfFirstStringSub - 1, ".");
			
		} else if((str1.length() == str2.length() && moveOneOverDecimalSub == false) && indexOfsecondDecimalOfSecondStringSub > indexOfFirstDecimalOfFirstStringSub) {
			arraylistFinalSub.add(indexOfsecondDecimalOfSecondStringSub, ".");
		
		}
		else if ((str1.length() == str2.length() && moveOneOverDecimalSub == false) && indexOfFirstDecimalOfFirstStringSub > indexOfsecondDecimalOfSecondStringSub) {
		arraylistFinalSub.add(indexOfFirstDecimalOfFirstStringSub, ".");
		
		} 
		else if((str1.length() > str2.length() && arraylist1Sub.size() > arraylistFinalSub.size())) {
			arraylistFinalSub.add(indexOfFirstDecimalOfFirstStringSub, ".");
			
		} 
		else if((str2.length() > str1.length() && arraylist2Sub.size() > arraylistFinalSub.size())) {
		arraylistFinalSub.add(indexOfsecondDecimalOfSecondStringSub, ".");
			
		} 
		 
		}

		String res1Sub = String.join("", arraylist1Sub);
		String res2Sub = String.join("", arraylist2Sub);
		String resFinalSub = String.join("", arraylistFinalSub);

		//Parse our arraylist answer as a string before returning it and assigning it to our object's difference String.



		if(isANegative) {
			resFinalSub = "-" + resFinalSub;
		}

		//If it is a negative, adds a "-".

		answerDifference = resFinalSub;

		

		return resFinalSub;

	}

	//Below code gets our operations done for our subtraction

	public String resultFinalArraySubtract(int i) {

        		if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("0")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("1")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("2")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("3")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("4")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("5")) {
        			carryRuleSubtraction(i);
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("6")) {
        			carryRuleSubtraction(i);
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("0")) {
        			
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("1")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("2")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("3")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("4")) {
        			carryRuleSubtraction(i);
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("5")) {
        			carryRuleSubtraction(i);
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("6")) {
        			carryRuleSubtraction(i);
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("0")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("1")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("2")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("3")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("4")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("5")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("6")) {
        			carryRuleSubtraction(i);
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("0")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("1")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("2")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("3")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("4")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("5")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("6")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("0")) {
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("1")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("2")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("3")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("4")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("5")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("6")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("0")) {
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("1")) {
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("2")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("3")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("4")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("5")) {
        			
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("6")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("0")) {
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("1")) {
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("2")) {
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("3")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("4")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("5")) {
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("6")) {
        			
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("7")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("0")) {
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("1")) {
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("2")) {
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("3")) {
        			
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("4")) {
        			
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("5")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("6")) {
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("7")) {
        			
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("8")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("0")) {
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("1")) {
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("2")) {
        			
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("3")) {
        			
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("4")) {
        			
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("5")) {
        			
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("6")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("7")) {
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("8") && arraylist2Sub.get(i).equals("9")) {
        			carryRuleSubtraction(i);
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("0")) {

        			return "9";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("1")) {
        			
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("2")) {
        			
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("3")) {
        			
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("4")) {
        			
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("5")) {
        			
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("6")) {
        			
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("7")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("9") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "0";
        		} 

        		return "";
        	}
        	//Below code is for our carry rule, where we create a while loop in a special case with multiple zeros
	public void carryRuleSubtraction(int i) {
		if(arraylist1Sub.get(i - 1).equals("0")) {
			int carryRuleZeroCounter = 0;
			for(int j = i - 1; j >= 0; j--) {

				if(!arraylist1Sub.get(j).equals("0")) {
					if(arraylist1Sub.get(j).equals("1")) {
					arraylist1Sub.set(j, "0");
					} else if(arraylist1Sub.get(j).equals("2")) {
					arraylist1Sub.set(j, "1");
					} else if(arraylist1Sub.get(j).equals("3")) {
					arraylist1Sub.set(j, "2");
					} else if(arraylist1Sub.get(j).equals("4")) {
					arraylist1Sub.set(j, "3");
					} else if(arraylist1Sub.get(j).equals("5")) {
					arraylist1Sub.set(j, "4");
					} else if(arraylist1Sub.get(j).equals("6")) {
					arraylist1Sub.set(j, "5");
					} else if(arraylist1Sub.get(j).equals("7")) {
					arraylist1Sub.set(j, "6");
					} else if(arraylist1Sub.get(j).equals("8")) {
					arraylist1Sub.set(j, "7");
					} else if(arraylist1Sub.get(j).equals("9")) {
					arraylist1Sub.set(j, "8");
					}
					j = -1;
				} else {
					arraylist1Sub.set(j, "9");
						}
					}
			
					} 
					//End of first if statement
					else if(arraylist1Sub.get(i - 1).equals("1")) {
					arraylist1Sub.set(i - 1, "0");
					} else if(arraylist1Sub.get(i - 1).equals("2")) {
					arraylist1Sub.set(i - 1, "1");
					} else if(arraylist1Sub.get(i - 1).equals("3")) {
					arraylist1Sub.set(i - 1, "2");
					} else if(arraylist1Sub.get(i - 1).equals("4")) {
					arraylist1Sub.set(i - 1, "3");
					} else if(arraylist1Sub.get(i - 1).equals("5")) {
					arraylist1Sub.set(i - 1, "4");
					} else if(arraylist1Sub.get(i - 1).equals("6")) {
					arraylist1Sub.set(i - 1, "5");
					} else if(arraylist1Sub.get(i - 1).equals("7")) {
					arraylist1Sub.set(i - 1, "6");
					} else if(arraylist1Sub.get(i - 1).equals("8")) {
					arraylist1Sub.set(i - 1, "7");
					} else if(arraylist1Sub.get(i - 1).equals("9")) {
					arraylist1Sub.set(i - 1, "8");
					}  	
		}

	public boolean isNegativeChecker(String str1, String str2) {
		int anotherTempCounter = 0;
		for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				anotherCheckWhereFirstDecimal = i;
				usedADecimalSub1 = true;
				}
			}

			for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				anotherCheckWhereSecondDecimal = i;
				usedADecimalSub2 = true;
			}
		}

		if((str1.length() < str2.length()) && (anotherCheckWhereFirstDecimal <= anotherCheckWhereSecondDecimal)) {
			return true;
		} else if((str1.length() <= str2.length()) && (anotherCheckWhereFirstDecimal == anotherCheckWhereSecondDecimal)) {

			String value1String = str1.substring(0, 1);
			String value2String = str2.substring(0, 1);
			if(!value1String.equals(".")) {
			double value1 = Double.parseDouble(value1String);
			double value2 = Double.parseDouble(value2String);
			}
			int len1 = str1.length();
			while(anotherTempCounter < len1) {
				if(value1 < value2) {
					return true;
				}
				value1String = str1.substring(anotherTempCounter, anotherTempCounter + 1);
				value2String = str2.substring(anotherTempCounter, anotherTempCounter + 1);
				if(!value1String.equals(".") || !value2String.equals(".")) {
				value1 = Double.parseDouble(value1String);
				value2 = Double.parseDouble(value2String);
				}
				System.out.println(value1);
				System.out.println(value2);
				if(value1 < value2) {
					return true;
				}
				anotherTempCounter++;
			}
		}
		return false;
	}

	//This method computes which input is bigger, and is subsequently called in our subOp. 
	public boolean biggerOnTop(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		if(str2.length() > str1.length()) {
			return true;
		} else if(str2.length() == str1.length() && str1.length() != 0) {
				int myTempCounter = 0;
				while(myTempCounter < len1) {
					if(!str1.substring(myTempCounter, myTempCounter + 1).equals(".") || !str2.substring(myTempCounter, myTempCounter + 1).equals(".")) {
					tempStrNum1 = Double.parseDouble(str1.substring(myTempCounter, myTempCounter + 1));
					tempStrNum2 = Double.parseDouble(str2.substring(myTempCounter, myTempCounter + 1));
					}
				if(tempStrNum2 > tempStrNum1) {
					return true;
				} else if(tempStrNum1 > tempStrNum2) {
					return false;
				}
				myTempCounter++;
			}
		}
		return false;
	}

	//Above two methods check for negative number evaluation as well as checking which number is larger to put on top before the operation takes place

	// Method that checks for both strings inputted to make sure they only have digits of 0-9 or a period. 
	public boolean checkNumIterate(String str) {
		if(str.length() < 1 || str.length() > 100) {
			return false;
		}
		for(int i = 0; i < str.length(); i++) {
			if(!str.substring(i, i + 1).equals("0") && !str.substring(i, i + 1).equals("1") && !str.substring(i, i + 1).equals("2") && !str.substring(i, i + 1).equals("3")
		&& !str.substring(i, i + 1).equals("4") && !str.substring(i, i + 1).equals("5") && !str.substring(i, i + 1).equals("6") && !str.substring(i, i + 1).equals("7")
		&& !str.substring(i, i + 1).equals("8") && !str.substring(i, i + 1).equals("9") && !str.substring(i, i + 1).equals(".")) {
				return false;
			}
		}
		return true;
	}
	//We create a toString for our MyBigDecimal object, which inherited NumericalOperand interface add() and subtract() methods, and was used in our main driver 'UseMyBigDecimal.java'
	public String toString() {
		return "The answer for your sum: " + answerSum + " \n" + "The answer for your difference: " + answerDifference;
	}
}

