import java.util.Scanner;

/**
 * This class represents Roman Numerals. 
 * Each numeral has an Arabic value and a Roman value.
 * @author martin
 *
 */
public class RomanNumerals {

	public static void main(String[] args) {
		
//		// Tests for Arabic to Roman
//		System.out.println("********Arabic to Roman**********");
//		RomanNumeralConverter test1 = new RomanNumeralConverter(17);
//		System.out.println("17: " + test1.romanValue);
//		RomanNumeralConverter test2 = new RomanNumeralConverter(1953);
//		System.out.println("1953: " + test2.romanValue);
//		RomanNumeralConverter test3 = new RomanNumeralConverter(1999);
//		System.out.println("1999: " + test3.romanValue);
//		
//		// Tests for Roman to Arabic
//		System.out.println("\n********Roman to Arabic**********");
//		RomanNumeralConverter test4 = new RomanNumeralConverter("V");
//		System.out.println("V: " + test4.arabicValue);
//		RomanNumeralConverter test5 = new RomanNumeralConverter("X");
//		System.out.println("X: " + test5.arabicValue);
//		RomanNumeralConverter test6 = new RomanNumeralConverter("M");
//		System.out.println("M: " + test6.arabicValue);
//		RomanNumeralConverter test7 = new RomanNumeralConverter("MCMLIII");
//		System.out.println("MCMLIII: " + test7.arabicValue);
//		RomanNumeralConverter test8 = new RomanNumeralConverter("MCMXCV");
//		System.out.println("MCMXCV: " + test8.arabicValue);
//		RomanNumeralConverter test9 = new RomanNumeralConverter("XVII");
//		System.out.println("XVII: " + test9.arabicValue);
		
		String input;
		int arabicInput;
		String romanInput;
		RomanNumerals rn;
		Scanner in = new Scanner(System.in);

		System.out.println("This program converts Arabic and Roman numerals.");
		System.out.println("Enter either Arabic or Roman numerals and they will be converted into the other.");
		System.out.println("Arabic numbers must be between 1 and 3999 inclusive.");

		while(true) {

			try {
				System.out.print(">: ");
				input = in.nextLine().trim();

				if(input.equals("")) {
					break;
				}
				else if(isInteger(input)) {
					arabicInput = Integer.parseInt(input);
					//rn = new RomanNumerals(arabicInput);
					System.out.println(new RomanNumerals(arabicInput).toString());
				}
				else {
					romanInput = input.toUpperCase().trim();
					//rn = new RomanNumerals(romanInput);
					System.out.println(new RomanNumerals(romanInput).toInt());
				}

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		
		System.out.println("OK. Bye for now.");
	}

	//***************** UTILITY FUNCTIONS ************************* //
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	/**
	 * Determines if a String is a legal double value in the specified radix.
	 * @param s the String being checked.
	 * @param radix the range of legal digits.
	 * @return true if the String is a legal double value, false otherwise.
	 */
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	/**
	 * Determines if a String is a legal Roman numeral value.
	 * @param s the String being checked.
	 * @param numerals the legal Roman numeral values.
	 * @return true if the String is a legal Roman numeral value, false otherwise. 
	 */
	public static boolean isRomanNumeral(String s, RomanNumeral[] numerals) {
		if(s.isEmpty()) return false;
		char ch;
		for(int i = 0; i < s.length(); i++) {
			ch = s.charAt(i);		
			if(isContained(Character.toString(ch), numerals)) 
				continue;
			else
				return false;
		}
		return true;
	}
	
	/**
	 * This is a utility method for the isRomanNumeral(String, RomanNumeral[]) method.
	 * @param s
	 * @param numerals
	 * @return
	 */
	public static boolean isContained(String s, RomanNumeral[] numerals) {
		String numeral;	
		for(int i = 0; i < numerals.length; i++) {
			numeral = numerals[i].getString();
			if(s.equals(numeral))
				return true;
		}		
		return false;		
	}
	
	//---------------------------MAIN METHOD------------------------------//
	
	/* This array holds all the roman numerals in descending order. */
	private static RomanNumeral numerals[] = { RomanNumeral.M, RomanNumeral.CM, RomanNumeral.D, RomanNumeral.CD, 
			                                   RomanNumeral.C, RomanNumeral.XC, RomanNumeral.L, RomanNumeral.XL, 
			                                   RomanNumeral.X, RomanNumeral.IX, RomanNumeral.V, RomanNumeral.IV, 
			                                   RomanNumeral.I };
	
	private int arabicValue;   // the arabic numeral value.
	private String romanValue; // the roman numeral value. 
	
	/**
	 * Constructor for converting Arabic numerals to Roman numerals.
	 * @param value the Arabic numeral.
	 */
	public RomanNumerals(int value) {
		if( (value < 1) || (value > 3999))
			throw new NumberFormatException("Illegal integer value. Please enter an integer between 1 and 3999(inclusive).");
		arabicValue = value;
		romanValue = arabicToRoman(value);
	}
	
	/**
	 * Constructor for converting Roman numerals to Arabic numerals.
	 * @param value the Roman numeral.
	 */
	public RomanNumerals(String value) {
		if( !isRomanNumeral(value, numerals) ) 
			throw new NumberFormatException("Illegal Roman numeral value. Please enter any combination of (M, D, C, L, X, V, I).");
		romanValue = value;
		arabicValue = romanToArabic(value);
	}
	
	
	/**
	 * Converts an Arabic numeral value to a Roman numeral value.
	 * @param arabic the arabic value that will be converted.
	 * @return the Roman numeral as a string.
	 */
	private String arabicToRoman(int arabic) {
		String roman = "";

		while(arabic > 0) {
			int numeralVal;
			String numeralString;
			for(int i = 0; i < numerals.length; i++) {
				numeralVal = numerals[i].getValue();
				numeralString = numerals[i].toString();
				while(arabic >= numeralVal) {
					roman += numeralString;
					arabic -= numeralVal;
				}
			}
		}
		return roman;
	}
	
	/**
	 * Converts a Roman numeral string to an Arabic numeral value.
	 * @param roman the roman value that will be converted.
	 * @return the Arabic numeral as an integer.
	 */
	private int romanToArabic(String roman) {
		int arabic = 0;
		
		int pos = 0;
		while(pos < roman.length()) {
			
			char letter = roman.charAt(pos);
			RomanNumeral number = letterToNumeral(Character.toString(letter));
			
			pos++;
			
			if(pos == roman.length())
				arabic += number.getValue();
			else {		
				RomanNumeral nextNumber = letterToNumeral(Character.toString(roman.charAt(pos)));
				if(nextNumber.getValue() > number.getValue()) {
					arabic += (nextNumber.getValue() - number.getValue());
					pos++;
				}
				else
					arabic += number.getValue();
			}
			
		}
		
		return arabic;
	}
	
	
	/**
	 * Returns the corresponding Roman Numeral of the passed string parameter.
	 * @param n the string representation of the roman numeral.
	 * @return the enum type RomanNumeral.
	 */
	public RomanNumeral letterToNumeral(String n) {
		RomanNumeral rn;	
		switch(n) {
			case "M"  -> rn = RomanNumeral.M;
			case "CM" -> rn = RomanNumeral.CM;
			case "D"  -> rn = RomanNumeral.D;
			case "CD" -> rn = RomanNumeral.CD;
			case "C"  -> rn = RomanNumeral.C;
			case "XC" -> rn = RomanNumeral.XC;
			case "L"  -> rn = RomanNumeral.L;
			case "XL" -> rn = RomanNumeral.XL;
			case "X"  -> rn = RomanNumeral.X;
			case "IX" -> rn = RomanNumeral.IX;
			case "V"  -> rn = RomanNumeral.V;
			case "IV" -> rn = RomanNumeral.IV;
		    default   -> rn = RomanNumeral.I;
		}	
		return rn;
	}
	
	/**
	 * Returns the Roman numeral as a string.
	 * @return the roman numeral.
	 */
	public String toString() {
		return romanValue;
	}
	
	/**
	 * Returns the Arabic numeral as a int. 
	 * @return
	 */
	public int toInt() {
		return arabicValue;
	}
	
}
