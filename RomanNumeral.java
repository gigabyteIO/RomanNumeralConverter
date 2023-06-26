/**
 * This enumerated type represents all the Roman Numerals.
 * Each numeral as an Arabic value and a String representation of the Roman value. 
 * @author martin
 *
 */
public enum RomanNumeral {
	
	M(1000, "M"),
	CM(900, "CM"),
	D(500, "D"),
	CD(400, "CD"),
	C(100, "C"),
	XC(90, "XC"),
	L(50, "L"),
	XL(40, "XL"),
	X(10, "X"),
	IX(9, "IX"),
	V(5, "V"),
	IV(4, "IV"),
	I(1, "I");
	
	private final int value; 	 // the arabic value of the roman numeral.
	private final String symbol; // the roman numeral representation as a string.
	
	private RomanNumeral(int value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}
	
	/**
	 * Returns the Arabic value of the Roman numeral.
	 * @return the value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Returns the string representation of a Roman numeral.
	 */
	public String getString() {
		return symbol;
	}
	
}
