import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;


// CSV Test File /Users/pranavlodha/Downloads/CMPE 202 Project/Sample Input/Sample.csv
// JSON Test File /Users/pranavlodha/Downloads/CMPE 202 Project/Sample Input/Sample.json
// XML Test File /Users/pranavlodha/Downloads/CMPE 202 Project/Sample Input/Sample.xml

//card number, exp date, name of cardholder
// return card number, card type, Error (None or invalid)


public class CreditCardIntro {

	public static void main(String[] args) {

		String input = "";
		String output = "";
		String checkInput = "";
		String checkOutput = "";

		if (args.length > 0) {
			input = args[0];
			output = args[1];
			if(input.contains(".") & output.contains(".")) {
				checkInput = input.substring(input.indexOf("."));
				checkOutput = output.substring(output.indexOf("."));
			}
			else {
				System.out.println("Please provide Input and output files with extensions");
				System.exit(0);
			}

			if(checkInput.equals(checkOutput)) {
				System.out.println("Program requirements met");
			}
			else {
				System.out.println("Please provide input and output files with same extensions");
				System.exit(0);
			}

		}
		else {
			System.out.println("Please provide arguments input and output");
			System.exit(0);
		}

		// Read user input for file
		/*
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter a file name: ");
		String input = scanner.nextLine();
		System.out.println("Your input: "+input);
		*/

		ArrayList<CreditCard> Cards = new ArrayList<CreditCard>();
		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

		boolean writerStatus = false;

		// CSV File
		if(input.contains(".csv")) {
			System.out.println("Its a CSV File");
			Cards = csv(input);
			writerStatus = writeCSV(Cards, MCH, output);
		}

		// JSON File
		else if(input.contains(".json")) {
			System.out.println("Its a JSON File");
			Cards = json(input);
			writerStatus = writeJSON(Cards, MCH, output);

		}

		// XML File
		else if(input.contains(".xml")) {
			System.out.println("Its a XML file");
			Cards = xml(input);
			writerStatus = writeXML(Cards, MCH, output);

		}
		else {
			System.out.println("Please supply a supported file type");
		}


	}

	public static String cleaner(String line) {
		String[] row = line.split(":");
		row[1] = row[1].replace("\"", "");
		row[1] = row[1].replace(",", "");
		row[1] = row[1].replace(" ", "");
		return row[1];
	}

	public static ArrayList<CreditCard> csv(String input) {
		// Create an ArrayList to store the cards from the csv file
		ArrayList<CreditCard> Cards = new ArrayList<CreditCard>();
		BufferedReader br = null;
		String line = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		try {
			br = new BufferedReader(new FileReader(input));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				// Split the row into each column
				String[] row = line.split(",");

				// Column 1 Card Number
				String cardNumber = String.format("%.0f", Double.parseDouble(row[0]));

				// Column 2 Expiration Date
				Date expDate = formatter.parse(row[1]);
				String strExpDate = formatter.format(expDate);

				// Column 3 Name
				String name = row[2];

				CreditCard cc = new CreditCard(cardNumber, expDate, name);
				Cards.add(cc);


				// Print Statement
				//System.out.println("Number: "+ cardNumber + "| ExpDate: " + strExpDate + "| Name: " + name);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}

		return Cards;

	}

	public static ArrayList<CreditCard>  json(String input) {
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
					String cleanedString = cleaner(line);
					expDate = formatter.parse(cleanedString);
					strExpDate = formatter.format(expDate);
					line = br.readLine();
				}
				if (line.contains("NameOfCardholder")) {
					String cleanedString = cleaner(line);
					name = cleanedString;
				}

				if(cardNumber != "" && name != "") {
					CreditCard cc = new CreditCard(cardNumber, expDate, name);
					Cards.add(cc);

					//System.out.println("Number: "+ cardNumber + "| ExpDate: " + strExpDate + "| Name: " + name);
				}


			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

		return Cards;

	}

	public static ArrayList<CreditCard>  xml(String input) {
		ArrayList<CreditCard> Cards = new ArrayList<CreditCard>();
		//BufferedReader br = null;
		//String line = "";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		try {

			File xmlFile = new File(input);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);

			doc.getDocumentElement();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("row");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					// Get Card Number
					String cardNumber = String.format("%.0f", Double.parseDouble(eElement.getElementsByTagName("CardNumber").item(0).getTextContent()));

					// Get Expiration Date
					Date expDate = formatter.parse(eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent());
					String strExpDate = formatter.format(expDate);

					// Get Name of Card Holder
					String name = eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();

					CreditCard cc = new CreditCard(cardNumber, expDate, name);
					Cards.add(cc);

					// Print Statement
					//System.out.println("Number: "+ cardNumber + "| ExpDate: " + strExpDate + "| Name: " + name);

				}
			}
		}

		catch (Exception e) {
			System.out.println(e);
		}

		return Cards;

	}

	public static boolean writeCSV(ArrayList<CreditCard> Cards, MasterCardHandler MCH, String output) {

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
					MCH.checkCreditCardNo(cc);
					sb.append(cc.getCreditCardNo());
					sb.append(',');
					sb.append(cc.getCardType());
					sb.append(',');
					if(cc.getIsValid()) {
						sb.append("None");
					}
					else {
						sb.append("Error");
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

	public static boolean writeJSON(ArrayList<CreditCard> Cards, MasterCardHandler MCH, String output) {
		try {
			FileWriter myWriter = new FileWriter(output);
			myWriter.write("[");
			myWriter.write('\n');
			for (int i = 0; i < Cards.size(); i++) {
				CreditCard cc = Cards.get(i);
				MCH.checkCreditCardNo(cc);
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
					myWriter.write("\"Error\": \"Error\"");
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

	public static boolean writeXML(ArrayList<CreditCard> Cards, MasterCardHandler MCH, String output) {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElementNS("", "root");
			doc.appendChild(rootElement);
			for (int i = 0; i < Cards.size(); i++) {
				Element rowElement = doc.createElement("row");
				CreditCard cc = Cards.get(i);
				MCH.checkCreditCardNo(cc);


				Element nodeCardNumber = doc.createElement("CardNumber");
				nodeCardNumber.appendChild(doc.createTextNode(cc.getCreditCardNo()));
				rowElement.appendChild(nodeCardNumber);

				Element nodeCardType = doc.createElement("CardType");
				nodeCardType.appendChild(doc.createTextNode(cc.getCardType()));
				rowElement.appendChild(nodeCardType);


				Element Error = doc.createElement("Error");
				if(cc.getIsValid()) {
					Error.appendChild(doc.createTextNode("None"));
					rowElement.appendChild(Error);
				}
				else {
					Error.appendChild(doc.createTextNode("Error"));
					rowElement.appendChild(Error);
				}

				rootElement.appendChild(rowElement);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);

			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(new File(output));

			transformer.transform(source, console);
			transformer.transform(source, file);
			System.out.println("DONE");


		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
