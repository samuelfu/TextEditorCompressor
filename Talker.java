/**
 * Talker is the .txt file editor. It inherits Processor because it uses many of
 * Processor's methods and fields.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Talker extends Processor {
	/**
	 * Does what the user wants depending on their input
	 */
	public void followCommand() {
		char command = input.charAt(0);
		String text = "Untitled";

		if (input.length() > 1)
			text = input.substring(2, input.length());

		if (command == 'g') {
			fileName = text + ".txt";
			getFileFromDirectory(fileName);
		} else if (command == 'p')
			printEntireFile();

		else if (command == 'c')
			printCurrentLine();

		else if (command == 'i')
			insertAfterLine(text);

		else if (command == 'd') {
			if (counter > -1)
				deleteCurrentLine();
			else
				System.out.println("Out of lines to delete!");
		} else if (command == 'r')
			replaceCurrentLine(text);
		else if (command == 't')
			goToTopLine();
		else if (command == 'v')
			goDownOneLine();
		else if (command == 's') {
			fileName = text + ".txt";
			super.setFileToDirectory();
		} else if (command == 'q') {
			System.out.println("Editor stopped");
			System.exit(0);
		} else if (command == 'w') {
			replaceWord(text);
		}
	}

	/**
	 * Inserts the given text into the file at the current line
	 * 
	 * @param text
	 */
	public void insertAfterLine(String text) {
		if (lineList.size() == (counter))
			lineList.add(text);
		else
			lineList.add(counter + 1, text);
	}

	/**
	 * Deletes the current line.
	 */
	public void deleteCurrentLine() {
		lineList.remove(counter);
		if (counter > lineList.size() - 1)
			counter = lineList.size() - 1;
	}

	/**
	 * Replaces current line with some text
	 * 
	 * @param text
	 *            given by the user
	 */
	public void replaceCurrentLine(String text) {
		lineList.remove(counter);
		lineList.add(counter, text);
	}

	/**
	 * Prints current line
	 */
	public void printCurrentLine() {
		System.out.println(lineList.get(counter));

	}

	/**
	 * Takes in either an original word plus a replacement word with a space in
	 * between or just an original word. Replaces the original with either the
	 * replacement word or nothing. This is easier to program than that of the
	 * Compressor's as explained by JavaDoc in Compressor.java. When the user
	 * replaces the word with nothing, there is extra space around where the word
	 * used to be.
	 * 
	 * @param text
	 *            includes the original text and the replacement text separated by
	 *            space
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
		for (int i = 0; i < lineList.size(); i++) {
			String line = lineList.get(i);
			line = line.replaceAll(originalWord, replacementWord);
			lineList.set(i, line);
		}
	}

}