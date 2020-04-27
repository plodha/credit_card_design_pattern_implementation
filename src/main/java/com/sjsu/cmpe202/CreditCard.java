
import java.util.Date;

public class CreditCard {
	private String creditCardNo;
	private Date expDate;
	private String name;
	private String cardType;
	private boolean isValid;


	public CreditCard(String creditCardNo, Date expDate, String name, String cardType, boolean isValid) {
		this.creditCardNo = creditCardNo;
		this.expDate = expDate;
		this.name = name;
		this.cardType = cardType;
		this.isValid = isValid;
	}


	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

}
