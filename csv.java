
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class csv {
	public ArrayList<CreditCard> csvRead(String input) {

		// Setup Handlers
		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

		// Create an ArrayList to store the cards from the csv file
		ArrayList<CreditCard> Cards = new ArrayList<CreditCard>();
		BufferedReader br = null;
		String line = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date expDate = new Date();
		String strExpDate = "";


		try {
			br = new BufferedReader(new FileReader(input));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				// Split the row into each column
				String[] row = line.split(",");

				// Column 1 Card Number
				String cardNumber = String.format("%.0f", Double.parseDouble(row[0]));

				// Column 2 Expiration Date
				try {
					expDate = formatter.parse(row[1]);
					strExpDate = formatter.format(expDate);
				}
				catch(Exception e) {
				}


				// Column 3 Name
				String name = row[2];

				Cards.add(MCH.checkCreditCardNo(cardNumber, expDate, name));


			}
		}
		catch (Exception e) {
			System.out.println(e);
		}

		return Cards;

	}

	public static boolean csvWrite(ArrayList<CreditCard> Cards, String output) {

		try (PrintWriter writer = new PrintWriter(new File(output))) {

		      StringBuilder sb = new StringBuilder();
		      sb.append("CardNumber");
		      sb.append(',');
		      sb.append("CardType");
		      sb.append(",");
		      sb.append("Error");
		      sb.append('\n');

		      for (int i = 0; i < Cards.size(); i++) {
					CreditCard cc = Cards.get(i);
					sb.append(cc.getCreditCardNo());
					sb.append(',');
					sb.append(cc.getCardType());
					sb.append(',');
					if(cc.getIsValid()) {
						sb.append("None");
					}
					else {
						sb.append("InvalidCardNumber");
					}
					sb.append('\n');
					System.out.println("Number: "+ cc.getCreditCardNo() + "| Type: " + cc.getCardType()+ "| Status: " + cc.getIsValid());
		      }

		      writer.write(sb.toString());

		      System.out.println("done!");

		    }
			catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
		    }

		return true;
	}



}
