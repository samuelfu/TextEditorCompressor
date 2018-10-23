import java.util.ArrayList;

/**
 * Compressor, like Talker, is a class that helps the user edit their file.
 * Compressor specifically edits .cmp files. It inherits Processor due to their
 * many shared fields and methods. Compressor first determines the command and
 * does the next step accordingly.
 * 
 * Test: ">g" creates an empty file called Untitled.cmp Untitled.cmp 0 bytes
 * Test: largerThanBefore.cmp 246 bytes tests how a compressed file can be larger than a txt file
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Compressor extends Processor {
	private Boolean isEmpty = true;

	/**
	 * After the user enters input, this method acts depending on the input.
	 */
	public void followCommand() {
		char command = input.charAt(0);
		String text = "Untitled";

		if (input.length() > 1)
			text = input.substring(2, input.length());

		if (command == 'g') {
			fileName = text + ".cmp";
			getFileFromDirectory(fileName);
			if (lineList.size() > 0)
				isEmpty = false;
			recordDictionaryKeyAndValue();
			createCompressedList();
		} else if (command == 'p') {
			decompressList();
			printEntireFile();
		} else if (command == 'c') {
			decompressList();
			printCurrentLine();
		} else if (command == 'i')
			insertAfterLine(text);

		else if (command == 'd')
			if (counter > -1)
				deleteCurrentLine();
			else
				System.out.println("Out of lines to delete!");
		else if (command == 'r')
			replaceCurrentLine(text);

		else if (command == 't')
			goToTopLine();

		else if (command == 'v')
			goDownOneLine();

		else if (command == 's') {
			fileName = text + ".cmp";
			lineList = new ArrayList<String>();
			String key = "";

			for (String word : valueList) {
				key += word;
				key += " ";
			}
			lineList.add(key);
			lineList.addAll(compressedList);

			setFileToDirectory();
		} else if (command == 'q') {
			System.out.println("Editor stopped");
			System.exit(0);
		} else if (command == 'w') {
			replaceWord(text);
		}
	}

	/**
	 * Creates a list of compressed lines (without the key)
	 */
	private void createCompressedList() {
		compressedList = new ArrayList<String>();
		for (String line : lineList)
			compressedList.add(line);

		compressedList.remove(0);
	}

	/**
	 * Decompresses the compressed list by using the key and substituting all
	 * numbers to words
	 */
	private void decompressList() {
		decompressedList = new ArrayList<String>();

		for (String line : compressedList) {
			for (Integer i = 0; i < valueList.size(); i++)
				line = line.replace(i.toString(), valueList.get(i));

			decompressedList.add(line);
		}
	}

	/**
	 * Records a dictionary by taking in the first line of the compressed file. If
	 * the file is empty, then adds empty objects to the file to prevent errors.
	 */
	private void recordDictionaryKeyAndValue() {
		if (!isEmpty) {
			String key = lineList.get(0);
			String[] wordList = key.split(" ");
			valueList = new ArrayList<String>();

			for (int i = 0; i < wordList.length; i++) {
				valueList.add(wordList[i]);
			}
		} else {
			lineList.add(null);
			valueList.add(null);
		}
	}

	/**
	 * Prints every line in the decompressed list
	 */
	public void printEntireFile() {
		for (String line : decompressedList) {
			System.out.println(line);
		}
	}

	/**
	 * Print the current line
	 */
	public void printCurrentLine() {
		System.out.println(decompressedList.get(counter));
	}

	/**
	 * Inserts the user's text into compressedList at the current line after
	 * replacing text with numbers. Any new words are added to the dictionary. If
	 * the file is empty then there is precautionary measures taken to prevent
	 * errors.
	 * 
	 * @param text
	 *            is what the user wants to insert
	 */
	public void insertAfterLine(String text) {
		String[] textWordList = text.split(" ");
		String valueLine = lineList.get(0);
		String line = text;

		if (isEmpty) {
			lineList.set(0, text);
			valueList.remove(0);
		}

		for (int i = 0; i < textWordList.length; i++)
			if (isEmpty || valueLine.indexOf(textWordList[i]) == -1) { // if the word does not exist in the
																		// original dictionary
				valueList.add(textWordList[i]);
			}

		for (Integer i = 0; i < valueList.size(); i++) {

			line = line.replace(valueList.get(i), i.toString());
		}

		if (compressedList.size() == (counter))
			compressedList.add(line);
		else
			compressedList.add(counter + 1, line);

		isEmpty = false;
	}

	/**
	 * Deletes the current line. If there are no lines it marks isEmpty true.
	 */
	public void deleteCurrentLine() {
		compressedList.remove(counter);
		if (counter == compressedList.size())
			counter--;

		if (compressedList.size() == 0)
			isEmpty = true;
	}

	/**
	 * Replaces the current line with text provided by the user
	 * 
	 * @param text
	 *            is what the user wants to replace with
	 */
	public void replaceCurrentLine(String text) {
		compressedList.remove(counter);

		insertAfterLine(text);
	}

	/**
	 * Increases the counter, unless it is at the end of the file. Then it tells the
	 * user.
	 */
	public void goDownOneLine() {
		if (counter + 1 < decompressedList.size()) {
			counter++;
		} else
			System.out.println("You are at the end of the file");
	}

	/**
	 * Replaces one word with either another or blank space. If the new word does
	 * not exist it is added to the dictionary. The compressor's replaceWord is
	 * harder to code than that of the Talker's due to the interaction with the
	 * dictionary and compressed format in general. When the user
	 * replaces the word with nothing, there is extra space around where the word
	 * used to be.
	 * 
	 * @param text
	 *            is the original word plus the replacement word with space in
	 *            between, or it is only the original word
	 */
	public void replaceWord(String text) {
		String originalWord;
		String replacementWord;
		String[] wordList = text.split(" ");
		originalWord = wordList[0];
		if (wordList.length == 2)
			replacementWord = wordList[1];
		else
			replacementWord = "";

		for (int i = 0; i < compressedList.size(); i++) {
			String line = compressedList.get(i);
			int originalWordValue = valueList.indexOf(originalWord);
			int replacementWordValue = valueList.indexOf(replacementWord);
			if (replacementWordValue == -1) {
				valueList.add(replacementWord);
				replacementWordValue = valueList.size() - 1;
			}

			line = line.replaceAll(Integer.toString(originalWordValue), Integer.toString(replacementWordValue));
			compressedList.set(i, line);
		}
	}
}
