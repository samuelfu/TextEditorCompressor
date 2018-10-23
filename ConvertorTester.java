import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class aims to test the Convertor class. In main is a test case where a
 * .txt file is written and converted to a .cmp file, while a .cmp file is
 * written and converted to a .txt file. The client should look at the file
 * created in their directory to check the success of the conversion.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class ConvertorTester {
	private Convertor convertor = new Convertor();
	private FileOutput fileOutput = new FileOutput();
	private ArrayList<String> lineList = new ArrayList<String>();
	private String fileName = "";

	/**
	 * Makes two converstion tests
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ConvertorTester convertorTester = new ConvertorTester();
		convertorTester.txtToCmpTester();
		convertorTester.cmpToTxtTester();

	}

	/**
	 * Creates a file txtToCmpTest.txt with the following lines and converts the
	 * file to .cmp. Check directory for the new file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void txtToCmpTester() throws FileNotFoundException, IOException {
		fileName = "txtToCmpTest";
		lineList = new ArrayList<String>();

		lineList.add("my name is Samuel Fu");
		lineList.add("hello Samuel Fu");
		lineList.add("hello world");

		fileOutput.saveFileToDirectory(fileName + ".txt", lineList);
		convertor.txtToCmp(fileName); // check .cmp file for success

	}

	/**
	 * Creates a file cmpToTxt.cmp with the following lines and converts the file to
	 * .txt. Check directory for the new file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void cmpToTxtTester() throws FileNotFoundException, IOException {
		fileName = "cmpToTxtTest";

		lineList = new ArrayList<String>();

		lineList.add("my name is Samuel Fu hello world");
		lineList.add("0 1 2 3 4");
		lineList.add("5 3 4");
		lineList.add("5 6");

		fileOutput.saveFileToDirectory(fileName + ".cmp", lineList);
		convertor.cmpToTxt(fileName); // check .txt file for success

	}
}
