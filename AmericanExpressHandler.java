

//First digit is a 3 and second digit a 4 or 7. Length is 15 digits.
public class AmericanExpressHandler implements Handler {

	private Handler nextHandler;

	@Override
	public void setNextHandler(Handler nextHandler) {

		this.nextHandler = nextHandler;

	}

	@Override
	public void checkCreditCardNo(CreditCard cc) {

		String number = cc.getCreditCardNo();

		if((number.charAt(0) == '3') && ((number.charAt(1) == '4') || (number.charAt(1) == '7') && number.length() == 15) ) {
			cc.setCardType("AmericanExpress");
			cc.setIsValid(true);
		}

		else {
			nextHandler.checkCreditCardNo(cc);
		}

	}

}
