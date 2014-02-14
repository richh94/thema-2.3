package model;

@SuppressWarnings("serial")
public class NumberBaseException extends RuntimeException{

	public NumberBaseException(char usedNumber, String base){
		super("Value '" + usedNumber + "' not allowed with base '" + base+ "'");
	}
}
