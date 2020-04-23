

// First digit is a 4. Length is either 13 or 16 digits.
public class VisaHandler implements Handler {

	private Handler nextHandler;

	@Override
	public void setNextHandler(Handler nextHandler) {
		
		this.nextHandler = nextHandler;

	}

	@Override
	public void checkCreditCardNo(CreditCard cc) {
		
		String number = cc.getCreditCardNo();
		
		if((number.charAt(0) == '4') && ((number.length() == 16) || number.length() == 13)) {
			cc.setCardType("Visa");
			cc.setIsValid(true);
		}
		
		else {
			cc.setIsValid(false);
		}

	}

}
