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
import java.util.*;
import java.text.SimpleDateFormat;


public class xml {
	public ArrayList<CreditCard> xmlRead(String input) {

    // Setup Handlers
		VisaHandler VH = new VisaHandler();
		MasterCardHandler MCH = new MasterCardHandler();
		AmericanExpressHandler AEH = new AmericanExpressHandler();
		DiscoverHandler DH = new DiscoverHandler();

		MCH.setNextHandler(AEH);
		AEH.setNextHandler(DH);
		DH.setNextHandler(VH);

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
			Date expDate = new Date();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("row");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					// Get Card Number
					String cardNumber = String.format("%.0f", Double.parseDouble(eElement.getElementsByTagName("CardNumber").item(0).getTextContent()));

					try {
						expDate = formatter.parse(eElement.getElementsByTagName("ExpirationDate").item(0).getTextContent());
						String strExpDate = formatter.format(expDate);
					}
					catch(Exception e) {
						System.out.println("Incorrect Date" + expDate);
					}
					// Get Expiration Date


					// Get Name of Card Holder
					String name = eElement.getElementsByTagName("NameOfCardholder").item(0).getTextContent();

					Cards.add(MCH.checkCreditCardNo(cardNumber, expDate, name));

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

  public static boolean xmlWrite(ArrayList<CreditCard> Cards, String output) {

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
					Error.appendChild(doc.createTextNode("InvalidCardNumber"));
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
