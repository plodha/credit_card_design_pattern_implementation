
import java.util.Date;

public class AmericanExpressHandler implements Handler {

	private Handler nextHandler;

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;

	}

	public CreditCard checkCreditCardNo(String creditCardNo, Date expDate, String name) {
		String number = creditCardNo;


		if((number.charAt(0) == '3') && ((number.charAt(1) == '4') || (number.charAt(1) == '7') && number.length() == 15)  ) {
			AmexCC amex = new AmexCC(creditCardNo, expDate, name, "AmericanExpress", true);
			return amex;
		}
		else {
			return(nextHandler.checkCreditCardNo(creditCardNo, expDate, name));
		}


	}

}
