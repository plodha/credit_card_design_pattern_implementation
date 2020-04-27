
import java.util.Date;

public class MasterCardHandler implements Handler {

	private Handler nextHandler;

	public void setNextHandler(Handler nextHandler) {
		this.nextHandler = nextHandler;

	}

	public CreditCard checkCreditCardNo(String creditCardNo, Date expDate, String name) {
		String number = creditCardNo;

		if((number.charAt(0) == '5') && ((Character.getNumericValue(number.charAt(1)) >= 1) && (Character.getNumericValue(number.charAt(1)) <= 5) && number.length() == 16) ) {
			MasterCC master = new MasterCC(creditCardNo, expDate, name, "MasterCard", true);
			return master;
		}
		else {
			return(nextHandler.checkCreditCardNo(creditCardNo, expDate, name));
		}


	}

}
