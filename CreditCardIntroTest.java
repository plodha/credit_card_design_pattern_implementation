import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class CreditCardIntroTest {

	@Test
	public void test() {
		CreditCard cc = new CreditCard();
	    cc.setCreditCardNo("5410000000000000");
	    cc.setName("Pranav Lodha");
	    Date expDate = new Date();
	    cc.setExpDate(expDate);

	    VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

	    MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

	    MCH.checkCreditCardNo(cc);

	    boolean status = cc.getIsValid();
	    assertTrue(status);
	    System.out.println("Test Credit Card Validity: " +status);
	}
	
	@Test
	public void test2() {
		CreditCard cc = new CreditCard();
	    cc.setCreditCardNo("5410000000000000");
	    cc.setName("asdPranav Lodha");
	    Date expDate = new Date();
	    cc.setExpDate(expDate);

	    VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

	    MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

	    MCH.checkCreditCardNo(cc);

	    boolean status = cc.getIsValid();
	    assertTrue(status);
	    System.out.println("Test Date: " +status);
	}
	
	@Test
	public void test3() {
		CreditCard cc = new CreditCard();
	    cc.setCreditCardNo("asd5410000000000000");
	    cc.setName("Pranav Lodha");
	    Date expDate = new Date();
	    cc.setExpDate(expDate);

	    VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

	    MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

	    MCH.checkCreditCardNo(cc);

	    boolean status = cc.getIsValid();
	    assertTrue(status);
	    System.out.println("Test Credit Card Type: " +status);
	}

}
