
import java.util.Date;

public interface Handler {

	public void setNextHandler(Handler nextHandler);

	public CreditCard checkCreditCardNo(String creditCardNo, Date expDate, String name);

}
