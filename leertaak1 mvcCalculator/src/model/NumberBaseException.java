package model;

public class NumberBaseException extends RuntimeException{

	public NumberBaseException(char usedNumber, String base){
		super("Value '" + usedNumber + "' not allowed with base '" + base+ "'");
	}
}
