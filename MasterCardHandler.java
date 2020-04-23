

// First digit is a 5, second digit is in range 1 through 5 inclusive. Only valid length of number is 16 digits.
public class MasterCardHandler implements Handler {
	
	private Handler nextHandler;

	@Override
	public void setNextHandler(Handler nextHandler) {
		
		this.nextHandler = nextHandler;

	}

	@Override
	public void checkCreditCardNo(CreditCard cc) {
		
		String number = cc.getCreditCardNo();
		
		if((number.charAt(0) == '5') && ((Character.getNumericValue(number.charAt(1)) >= 1) && (Character.getNumericValue(number.charAt(1)) <= 5) && number.length() == 16) ) {
			cc.setCardType("MasterCard");
			cc.setIsValid(true);
		}
		
		else {
			nextHandler.checkCreditCardNo(cc);
		}

	}

}
