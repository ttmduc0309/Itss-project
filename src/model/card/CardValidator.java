package model.card;

import java.time.YearMonth;

import common.exception.*;

public class CardValidator {
	public void validateRequiredFields(String holderName, String cardNumber, String cvv, String expirationDate) throws NullCardHolderNameException,
	NullCardNumberException, NullExpirationDateException, NullSecurityCodeException {
		if (holderName == null || holderName.isBlank()) throw new NullCardHolderNameException();
		if (cardNumber == null || cardNumber.isBlank()) throw new NullCardNumberException();
		if (cvv == null || cvv.isBlank()) throw new NullSecurityCodeException();
		if (expirationDate == null || expirationDate.isBlank()) throw new NullExpirationDateException();
	}
	public void validateCardHolderName(String holderName) throws InvalidCardHolderNameException, NullCardHolderNameException {
		if (!holderName.matches("^[a-zA-Z ]+$")) throw new InvalidCardHolderNameException();
	}
	
	public void validateCardNumber(String cardNumber) throws InvalidCardNumberException, NullCardNumberException {
		if (!cardNumber.matches("^[a-zA-Z0-9_]+$")) throw new InvalidCardNumberException();
	}
	
	public void validateSecurityCode(String cvv) throws InvalidSecurityCodeException, NullSecurityCodeException {
		if (cvv.length() != 3 || !cvv.matches("^[0-9]+$")) throw new InvalidSecurityCodeException();
	}
	
	public void validateExpirationDate(String expirationDate) throws InvalidExpirationDateException, NullExpirationDateException{
		// format MM/YY
		if (!expirationDate.matches("^(0[1-9]|1[0-2])/(\\d{2})$")) {
			throw new InvalidExpirationDateException();
		}
		
		String[] parts = expirationDate.split("/");
		int month, year;
		
		try {
			month = Integer.parseInt(parts[0]);
			year = Integer.parseInt(parts[1]);
		} catch(NumberFormatException e) {
			throw new InvalidExpirationDateException();
		}
		
		// check if the input date is after the current date
		if (YearMonth.of(year + 2000, month).isBefore(YearMonth.now())) {
			throw new InvalidExpirationDateException();
		}
	}
}