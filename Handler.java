
public interface Handler {
	
	public void setNextHandler(Handler nextHandler);
	
	public void checkCreditCardNo(CreditCard cc);
	
}
