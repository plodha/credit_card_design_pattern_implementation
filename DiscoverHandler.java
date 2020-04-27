
import java.util.Date;

public class DiscoverHandler implements Handler {

	private Handler nextHandler;

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;

	}

	public CreditCard checkCreditCardNo(String creditCardNo, Date expDate, String name) {
		String number = creditCardNo;

		if((number.substring(0, 3).equals("6011")) && number.length() == 16 ) {
			DiscoverCC discover = new DiscoverCC(creditCardNo, expDate, name, "Discover", true);
			return discover;
		}
		else {
			return(nextHandler.checkCreditCardNo(creditCardNo, expDate, name));
		}

	}

}
