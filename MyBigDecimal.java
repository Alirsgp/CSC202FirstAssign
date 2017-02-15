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
	private static String sumAsString = "";
	private static boolean moveOneOverDecimal = false;
	private static boolean isNegative = false;
	private static int countAfterFirstInputDecimal = 0;
	private static int countAfterSecondInputDecimal = 0;
	private static boolean firstCheckaUnderPoint5 = false;
	private static boolean secondCheckaUnderPoint5 = false;
	private static double underPoint5First;
	private static double underPoint5Second;
	private static String temp;
	private static int anotherCheckWhereFirstDecimal = 0;
	private static int anotherCheckWhereSecondDecimal = 0;
	//Two ints above for negative value checks

	//Define a new reference type, and cast operand to be a NumericalOperand type
	public NumericalOperand add(NumericalOperand operand) {
		NumericalOperand obj = (NumericalOperand)operand;
		return obj;
	}

	public NumericalOperand subtract(NumericalOperand operand) {
		NumericalOperand obj = (NumericalOperand)operand;
		return obj;
	}

	//See's whether the user has chosen addition or subtraction, and calls appropriate method
	// public void carryRuleAdd(int i) {
		
		
	// }

	// public void carryRuleSubtract() {

	// }

	public String addOp(String str1, String str2) {
		//Code below is used in case user inputs a "." first.
		if(str1.substring(0, 1).equals(".")) {
			str1 = "0" + str1;
			firstCheckaUnderPoint5 = true;
		}

		if(str2.substring(0, 1).equals(".")) {
			str2 = "0" + str2;
			secondCheckaUnderPoint5 = true;
		}


		try{
			underPoint5First = Double.parseDouble(str1);
			underPoint5Second = Double.parseDouble(str2);
		} catch(Exception e) {
			System.out.println("Too large to convert");
		}

		/***
		The following code is used to initialize the location of each String's decimal place, so that it may be appended
		at the end of the program.
		***/

		int indexOfFirstDecimalOfFirstString = 0;
		int indexOfSecondDecimalOfSecondString = 0;


		//Takes our two strings and makes an ArrayList out of them.

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
				indexOfSecondDecimalOfSecondString = i;
				arraylist2.remove(indexOfSecondDecimalOfSecondString);
				arraylist2.add(0, "0");
				//Remove decimal
			}
		}
		arraylist1.add(0, "0");
		arraylist2.add(0, "0");
		arraylist1.add(0, "0");
		arraylist2.add(0, "0");

		//Following for-loop looks at how many digits after decimal for both inputs, and adds zeros to make both amount of digits AFTER decimal the same.

		
		
		

		

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
		

			



		
			for(int i = arraylist1.size() - 1; i > 0; i--) {
			arraylistFinal.set(i, resultFinalArrayAdd(i));
			}



		
		
		//Reverse for-loop as we iterate from right to left with addition/subtraction using carry rule

		//If single digit, adds a insignificant zero to front
		while(arraylistFinal.get(0).equals("0")) {
			arraylistFinal.remove(0);
		}
		
		if((arraylistFinal.size()) > str1.length() && (arraylistFinal.size())> str2.length()) {
			moveOneOverDecimal = true;
		}


		if(indexOfFirstDecimalOfFirstString > 0 || indexOfSecondDecimalOfSecondString > 0) {
			if(str1.length() > str2.length()) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString, ".");
		} else if(str2.length() > str1.length()) {
			arraylistFinal.add(indexOfSecondDecimalOfSecondString, ".");
		} else if((str1.length() == str2.length()) && moveOneOverDecimal == true) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString + 1, ".");
		} else if ((str1.length() == str2.length() && moveOneOverDecimal == false) && (firstCheckaUnderPoint5 && secondCheckaUnderPoint5) && (underPoint5First < .4999999 && underPoint5Second < .4999999)) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString - 1, ".");
		} else if (str1.length() == str2.length() && moveOneOverDecimal == false) {
			arraylistFinal.add(indexOfFirstDecimalOfFirstString, ".");
			} 
		}	




		String res1 = String.join("", arraylist1);
		String res2 = String.join("", arraylist2);
		String resFinal = String.join("", arraylistFinal);

		return resFinal;

	}

	//Code that was used to test to make sure arraylists converted properly to strings
	public String printArrayLists() {
		String res1 = String.join("", arraylist1);
		String res2 = String.join("", arraylist2);
        return res1 + ", " + res2;
	}

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


	public String subOp(String str1, String str2) {


		if(str1.length() < 16 && str2.length() < 16) {
			double myValue1 = Double.parseDouble(str1);
			double myValue2 = Double.parseDouble(str2);
			return "" + (myValue1 - myValue2);
		}

		//Code below is used in case user inputs a "." first.
		if(str1.substring(0, 1).equals(".")) {
			str1 = "0" + str1;
			firstCheckaUnderPoint5 = true;
		}

		if(str2.substring(0, 1).equals(".")) {
			str2 = "0" + str2;
			secondCheckaUnderPoint5 = true;
		}


		try{
			underPoint5First = Double.parseDouble(str1);
			underPoint5Second = Double.parseDouble(str2);
		} catch(Exception e) {
			System.out.println("Too large to convert");
		}

		/***
		The following code is used to initialize the location of each String's decimal place, so that it may be appended
		at the end of the program.
		***/

		int indexOfFirstDecimalOfFirstString = 0;
		int indexOfSecondDecimalOfSecondString = 0;


		//Takes our two strings and makes an ArrayList out of them.

		for(int i = 0; i < str1.length(); i++) {
			arraylist1Sub.add(str1.substring(i, i + 1));
		}

		//Above code adds the first string to arraylist1Sub

		for(int i = 0; i < str2.length(); i++) {
			arraylist2Sub.add(str2.substring(i, i + 1));
		}

		//Above code adds the second string to arraylist2Sub

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
				arraylist1Sub.remove(indexOfFirstDecimalOfFirstString);
				arraylist1Sub.add(0, "0");
				//Remove decimal
			}
		}

		//Above code documents where the decimal point was for first string, below code does it for second string

		for(int i = 0; i < str2.length() - 1; i++) {
			if(str2.substring(i, i + 1).equals(".")) {
				indexOfSecondDecimalOfSecondString = i;
				arraylist2Sub.remove(indexOfSecondDecimalOfSecondString);
				arraylist2Sub.add(0, "0");
				//Remove decimal
			}
		}
		arraylist1Sub.add(0, "0");
		arraylist2Sub.add(0, "0");
		//arraylist1Sub.add(0, "0");
		//arraylist2Sub.add(0, "0");

		//Following for-loop looks at how many digits after decimal for both inputs, and adds zeros to make both amount of digits AFTER decimal the same.

		
		
		

		

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
		//Above 2 if statements for whole #, then next 2 above them is for decimals
		//Add 0 to first index for use of carry rule

		while(arraylistFinalSub.size() < arraylist1Sub.size() && arraylistFinalSub.size() < arraylist2Sub.size()) {
			arraylistFinalSub.add("0");
		}

		while(countAfterFirstInputDecimal > countAfterSecondInputDecimal) {
			arraylist2Sub.add(arraylist2Sub.size(), "0");
			arraylist2Sub.remove(0);
			countAfterSecondInputDecimal++;
			
		}

		while(countAfterFirstInputDecimal < countAfterSecondInputDecimal) {
			arraylist1Sub.add(arraylist1Sub.size(), "0");
			arraylist1Sub.remove(0);
			countAfterFirstInputDecimal++;
			
		}
		

			



		
			for(int i = arraylist1Sub.size() - 1; i > 0; i--) {
			arraylistFinalSub.set(i, resultFinalArraySubtract(i));
			}



		
		
		//Reverse for-loop as we iterate from right to left with addition/subtraction using carry rule

		//If single digit, adds a insignificant zero to front
		while(arraylistFinalSub.get(0).equals("0")) {
			arraylistFinalSub.remove(0);
		}
		
		if((arraylistFinalSub.size()) > str1.length() && (arraylistFinalSub.size())> str2.length()) {
			moveOneOverDecimal = true;
		}


		if(indexOfFirstDecimalOfFirstString > 0 || indexOfSecondDecimalOfSecondString > 0) {
			if(str1.length() > str2.length()) {
			arraylistFinalSub.add(indexOfFirstDecimalOfFirstString, ".");
		} else if(str2.length() > str1.length()) {
			arraylistFinalSub.add(indexOfSecondDecimalOfSecondString, ".");
		} else if((str1.length() == str2.length()) && moveOneOverDecimal == true) {
			arraylistFinalSub.add(indexOfFirstDecimalOfFirstString + 1, ".");
		} else if ((str1.length() == str2.length() && moveOneOverDecimal == false) && (firstCheckaUnderPoint5 && secondCheckaUnderPoint5) && (underPoint5First < .4999999 && underPoint5Second < .4999999)) {
			arraylistFinalSub.add(indexOfFirstDecimalOfFirstString - 1, ".");
		} else if (str1.length() == str2.length() && moveOneOverDecimal == false) {
			arraylistFinalSub.add(indexOfFirstDecimalOfFirstString, ".");
			} 
		}	




		String res1 = String.join("", arraylist1Sub);
		String res2 = String.join("", arraylist2Sub);
		String resFinal = String.join("", arraylistFinalSub);

		if(isNegativeChecker(str1, str2)) {
			resFinal = "-" + resFinal;
		}

		

		return resFinal;

	}

	public String resultFinalArraySubtract(int i) {

        		if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("0")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("1")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("2")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("3")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("4")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("5")) {
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("6")) {
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("7")) {
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("8")) {
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("1") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("0")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("1")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("2")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("3")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("4")) {
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("5")) {
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("6")) {
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("7")) {
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("8")) {
        			return "8";
        		} else if(arraylist1Sub.get(i).equals("0") && arraylist2Sub.get(i).equals("9")) {
        			return "9";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("0")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("1")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("2")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("3")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("4")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("5")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("6")) {
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("7")) {
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "6";
        		} else if(arraylist1Sub.get(i).equals("2") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "7";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("0")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("1")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("2")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("3")) {
        			return "0";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("4")) {
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("5")) {
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("6")) {
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("7")) {
        			
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "5";
        		} else if(arraylist1Sub.get(i).equals("3") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "6";
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
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("6")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("7")) {
        			
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "4";
        		} else if(arraylist1Sub.get(i).equals("4") && arraylist2Sub.get(i).equals("9")) {
        		
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
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("7")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "3";
        		} else if(arraylist1Sub.get(i).equals("5") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "4";
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
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("8")) {
        			
        			return "2";
        		} else if(arraylist1Sub.get(i).equals("6") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "3";
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
        			
        			return "1";
        		} else if(arraylist1Sub.get(i).equals("7") && arraylist2Sub.get(i).equals("9")) {
        			
        			return "2";
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
        			
        			return "1";
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

	// public void carryRuleSubtract(int i) {

	// }

	public boolean isNegativeChecker(String str1, String str2) {
		if(str1.length() <= str2.length()) {
		
			for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				anotherCheckWhereFirstDecimal = i;
				
				}
			}

			for(int i = 0; i < str1.length() - 1; i++) {
			if(str1.substring(i, i + 1).equals(".")) {
				anotherCheckWhereSecondDecimal = i;
			}
		}

		if(anotherCheckWhereSecondDecimal >= anotherCheckWhereFirstDecimal) {
			
			String value1String = str1.substring(0, 1);
			String value2String = str2.substring(0, 1);
			double value1 = Double.parseDouble(value1String);
			double value2 = Double.parseDouble(value2String);
			if(value1 <= value2) {
			temp = str1;
			str1 = str2;
			str2 = temp;
			return true;
				}
			}
		}
		return false;
	}

	//Above code provides instructions for a negative ^


	




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

	public String toString() {
		return sumAsString;
	}
}

