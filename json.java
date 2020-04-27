import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class json {

  public static String cleaner(String line) {
		String[] row = line.split(":");
		row[1] = row[1].replace("\"", "");
		row[1] = row[1].replace(",", "");
		row[1] = row[1].replace(" ", "");
		return row[1];
	}

	public ArrayList<CreditCard> jsonRead(String input) {
    // Setup Handlers
		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

    //Setup variables
    ArrayList<CreditCard> Cards = new ArrayList<CreditCard>();
		BufferedReader br = null;
		String line = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		try {
			br = new BufferedReader(new FileReader(input));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String cardNumber = "";
				Date expDate = new Date();
				String name = "";
				String strExpDate = "";

				if (line.contains("CardNumber")) {
					String cleanedString = cleaner(line);
					cardNumber = String.format("%.0f", Double.parseDouble(cleanedString));
					line = br.readLine();
				}
				if (line.contains("ExpirationDate")) {
					try {
						String cleanedString = cleaner(line);
						expDate = formatter.parse(cleanedString);
						strExpDate = formatter.format(expDate);
						line = br.readLine();
					}
					catch(Exception e) {
						System.out.println("Incorrect Date" + expDate);
						line = br.readLine();
					}

				}
				if (line.contains("NameOfCardholder")) {
					String cleanedString = cleaner(line);
					name = cleanedString;
				}

				if(cardNumber != "" && name != "") {
					Cards.add(MCH.checkCreditCardNo(cardNumber, expDate, name));
					//System.out.println("Number: "+ cardNumber + "| ExpDate: " + strExpDate + "| Name: " + name);
				}


			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

		return Cards;
  }

  public static boolean jsonWrite(ArrayList<CreditCard> Cards, String output) {
  		try {
  			FileWriter myWriter = new FileWriter(output);
  			myWriter.write("[");
  			myWriter.write('\n');
  			for (int i = 0; i < Cards.size(); i++) {
  				CreditCard cc = Cards.get(i);
  				myWriter.write("{");
  				myWriter.write('\n');
  				myWriter.write("\"CardNumber\": "+ cc.getCreditCardNo() + ",");
  				myWriter.write('\n');
  				myWriter.write("\"CardType\": " + "\"" + cc.getCardType() + "\"" + " ,");
  				myWriter.write('\n');

  				if(cc.getIsValid()) {
  					myWriter.write("\"Error\": \"None\"");
  					myWriter.write('\n');
  				}
  				else {
  					myWriter.write("\"Error\": \"InvalidCardNumber\"");
  					myWriter.write('\n');
  				}
  				if(i == Cards.size() - 1) {
  					myWriter.write("}");
  				}
  				else {
  					myWriter.write("},");
  				}
  				//myWriter.write("},");
  				myWriter.write('\n');
  				System.out.println("Number: "+ cc.getCreditCardNo() + "| Type: " + cc.getCardType()+ "| Status: " + cc.getIsValid());
  			}
  			myWriter.write("]");
  			myWriter.close();
  			System.out.println("Successfully wrote to the file.");
  		}
  		catch (IOException e) {
  			System.out.println("An error occurred.");
  			e.printStackTrace();
  		}


  		return true;
  	}

}
