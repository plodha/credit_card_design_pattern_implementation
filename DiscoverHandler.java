
// First four digits are 6011. Length is 16 digits.
public class DiscoverHandler implements Handler {

	private Handler nextHandler;

	@Override
	public void setNextHandler(Handler nextHandler) {
		
		this.nextHandler = nextHandler;

	}

	@Override
	public void checkCreditCardNo(CreditCard cc) {
		
		String number = cc.getCreditCardNo();
		
		if((number.substring(0, 3).equals("6011")) && number.length() == 16) {
			cc.setCardType("Discover");
			cc.setIsValid(true);
		}
		
		else {
			nextHandler.checkCreditCardNo(cc);
		}

	}

}
