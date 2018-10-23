import java.util.ArrayList;

/**
 * Convertor class provides API for converting .cmp files to .txt and vice
 * versa. ConverterTester tests this class. It inherits Processor because it
 * uses many of Processor's methods and fields.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Convertor extends Processor {
	/**
	 * Compresses and saves a .cmp file with the same name as the input file.
	 * 
	 * @param txtFile
	 *            is the name of the .txt file
	 */
	public void txtToCmp(String txtFile) {
		fileOutput = new FileOutput();

		getFileFromDirectory(txtFile + ".txt");
		createKeyLine();

		lineList = new ArrayList<String>();
		String valueString = "";
		for (String value : valueList) {
			valueString += value;
			valueString += " ";
		}

		lineList.add(valueString);
		lineList.addAll(compressedList);
		fileName = txtFile + ".cmp";
		setFileToDirectory();// do it with cmp
	}

	/**
	 * Decompresses and saves a .txt file with the same name as the input file.
	 * 
	 * @param cmpFile
	 *            is the name of the .cmp file
	 */
	public void cmpToTxt(String cmpFile) {
		fileOutput = new FileOutput();
		compressedList = new ArrayList<String>();
		getFileFromDirectory(cmpFile + ".cmp");

		String keyLine = lineList.get(0);
		String[] keyList = keyLine.split(" ");

		for (int i = 0; i < keyList.length; i++)
			valueList.add(keyList[i]);

		compressedList.addAll(lineList);
		compressedList.remove(0);

		decompressList();
		fileName = cmpFile + ".txt";
		setFileToDirectory();
	}

	/**
	 * Creates a dictionary for keys and values from a complete txt file
	 */
	private void createKeyLine() {
		String valueLine = "";
		for (String key : lineList) {
			String[] textWordList = key.split(" ");

			for (int i = 0; i < textWordList.length; i++)
				if (valueLine.indexOf(textWordList[i]) == -1) { // if the word does not exist in the dictionary
					valueLine += textWordList[i]; // add it to the dictionary
					valueLine += " ";
					valueList.add(textWordList[i]); // add it to the key list
				}

			for (Integer i = 0; i < valueList.size(); i++)
				key = key.replace(valueList.get(i), i.toString());

			compressedList.add(key);
		}

	}

	/**
	 * Decompresses a list of numbers with the dictionary
	 */
	private void decompressList() {
		decompressedList = new ArrayList<String>();

		for (String line : compressedList) {

			System.out.println(line);
			for (Integer i = 0; i < valueList.size(); i++)
				line = line.replace(i.toString(), valueList.get(i));
			System.out.println(line);
			decompressedList.add(line);
		}

		lineList = new ArrayList<String>();
		lineList.addAll(decompressedList);
	}

}
