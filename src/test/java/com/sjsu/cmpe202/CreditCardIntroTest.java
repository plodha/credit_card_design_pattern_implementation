import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class CreditCardIntroTest {

	// Test MasterCard
	@Test
	public void test() {
		String cardNumber = "5410000000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

		CreditCard cc = MCH.checkCreditCardNo(cardNumber, expDate, name);
		assertEquals(true, cc.getIsValid());

	}

	// Test American Express
	@Test
	public void test2() {
		String cardNumber = "341000000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

		CreditCard cc = MCH.checkCreditCardNo(cardNumber, expDate, name);
		assertEquals(true, cc.getIsValid());

	}

	// Test Discover
	@Test
	public void test3() {
		String cardNumber = "6011000000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

		CreditCard cc = MCH.checkCreditCardNo(cardNumber, expDate, name);
		System.out.println(cc.getIsValid());
		//cc.setIsValid(true);
		assertEquals(true, cc.getIsValid());

	}

	// Test Visa
	@Test
	public void test4() {
		String cardNumber = "4120000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

		CreditCard cc = MCH.checkCreditCardNo(cardNumber, expDate, name);
		assertEquals(true, cc.getIsValid());

	}

}
