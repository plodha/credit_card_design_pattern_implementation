
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// Get input and output file from arguments
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

		// Create handlers for each of the credit cards to confirm type
		ArrayList<CreditCard> Cards = new ArrayList<CreditCard>();
		boolean writerStatus;

		// CSV File
		if(input.contains(".csv")) {
			System.out.println("Its a CSV File");
			csv CSV = new csv();
			Cards = CSV.csvRead(input);
			writerStatus = CSV.csvWrite(Cards, output);
		}

		// Json File
		else if(input.contains(".json")) {
			System.out.println("Its a JSON File");
			json JSON = new json();
			Cards = JSON.jsonRead(input);
			writerStatus = JSON.jsonWrite(Cards, output);

		}


		// XML File
		else if(input.contains(".xml")) {
			System.out.println("Its a XML file");
			xml XML = new xml();
			Cards = XML.xmlRead(input);
			writerStatus = XML.xmlWrite(Cards, output);

		}

		else {
			System.out.println("Please supply a supported file type");
			System.exit(0);
		}


	}

}
