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
		assertTrue(cc.getIsValid());

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
		assertTrue(cc.getIsValid());

	}

	// Test Discover
	@Test
	public void test3() {
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
		System.out.println(cc.getIsValid());
		
		assertTrue(cc.getIsValid());

	}

	// Test Visa
	@Test
	public void test4() {
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
		assertTrue(cc.getIsValid());

	}

	// Test Visa
	@Test
	public void test5() {
		String cardNumber = "8620000000000";
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
		assertFalse(cc.getIsValid());

	}

	// Test MasterCard
	@Test
	public void test6() {
		String cardNumber = "9620000000000";
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
		assertFalse(cc.getIsValid());

	}

	// Test American Express
	@Test
	public void test7() {
		String cardNumber = "6620000000000";
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
		assertFalse(cc.getIsValid());

	}

	// Test Discover
	@Test
	public void test8() {
		String cardNumber = "9620000000000";
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
		assertFalse(cc.getIsValid());

	}

	// Test MasterCard
	@Test
	public void test9() {
		String cardNumber = "5410000000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		MasterCardHandler MCH = new MasterCardHandler();
		MasterCC MC = new MasterCC(cardNumber, expDate, name, "MasterCard", true);

		CreditCard cc = MCH.checkCreditCardNo(cardNumber, expDate, name);
		assertEquals(cc.getClass(), MC.getClass());

	}

	// Test American Express
	@Test
	public void test10() {
		String cardNumber = "341000000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		AmericanExpressHandler AEH = new AmericanExpressHandler();
		AmexCC amex = new AmexCC(cardNumber, expDate, name, "AmericanExpress", true);

		CreditCard cc = AEH.checkCreditCardNo(cardNumber, expDate, name);
		assertEquals(cc.getClass(), amex.getClass());

	}

	// Test Discover
	@Test
	public void test11() {
		String cardNumber = "6011000000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		DiscoverHandler DH = new DiscoverHandler();
		DiscoverCC discover = new DiscoverCC(cardNumber, expDate, name, "Discover", true);

		CreditCard cc = DH.checkCreditCardNo(cardNumber, expDate, name);
		System.out.println(cc.getIsValid());

		assertEquals(cc.getClass(), discover.getClass());

	}

	// Test Visa
	@Test
	public void test12() {
		String cardNumber = "4120000000000";
		String name = "Pranav Lodha";
		Date expDate = new Date();

		VisaHandler VH = new VisaHandler();
		VisaCC visa = new VisaCC(cardNumber, expDate, name, "Visa", true);

		CreditCard cc = VH.checkCreditCardNo(cardNumber, expDate, name);
		assertEquals(cc.getClass(), visa.getClass());

	}

}
