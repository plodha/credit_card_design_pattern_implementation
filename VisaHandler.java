
import java.util.Date;

public class VisaHandler implements Handler {

	private Handler nextHandler;

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;

	}

	public CreditCard checkCreditCardNo(String creditCardNo, Date expDate, String name) {
		String number = creditCardNo;

		if((number.charAt(0) == '4') && ((number.length() == 16) || number.length() == 13) ) {
			VisaCC visa = new VisaCC(creditCardNo, expDate, name, "Visa", true);
			return visa;
		}
		else {
			CreditCard cc = new CreditCard(creditCardNo, expDate, name, "Invalid", false);
			return cc;
		}

	}

}
