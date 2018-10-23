import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Processor provides the methods and fields that Compressor, Convertor and
 * Talker all share. ArrayLists are used because there is a lot of inserting and
 * deleting involved in this assignment, which means that arrays will not be
 * good. LinkedLists are also not as good because of their weakness in
 * accessing. Therefore I chose ArrayLists.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Processor {
	protected String input;
	protected String fileName;
	protected int counter = 0;
	protected FileInput fileInput = new FileInput();
	protected FileOutput fileOutput = new FileOutput();
	protected ArrayList<String> lineList = new ArrayList<String>();
	protected ArrayList<String> valueList = new ArrayList<String>();
	protected ArrayList<String> compressedList = new ArrayList<String>();
	protected ArrayList<String> decompressedList = new ArrayList<String>();

	/**
	 * Displays ">"
	 */
	public void prompt() {
		System.out.print(">");
	}

	/**
	 * Asks user for input and checks its validity
	 */
	public void askInput() {
		Scanner scanner = new Scanner(System.in);
		checkValidity(scanner);
	}

	public void followCommand() {
	}

	/**
	 * Prints the file by line
	 */
	public void printEntireFile() {
		for (String line : lineList) {
			System.out.println(line);
		}
	}

	/**
	 * Sets the counter to 0
	 */
	public void goToTopLine() {
		counter = 0;
	}

	/**
	 * Checks if the first letter is a legal action. If not, re-prompt.
	 * 
	 * @param scanner
	 */
	private void checkValidity(Scanner scanner) {
		Boolean isValid = false;
		while (!isValid) {
			input = scanner.nextLine();
			char command = input.charAt(0);

			char space = 0;
			if (input.length() < 2)

				space = ' ';

			else
				space = input.charAt(1);

			if ((command == 'g' || command == 'p' || command == 'c' || command == 'i' || command == 'd'
					|| command == 'r' || command == 't' || command == 'v' || command == 's' || command == 'q'
					|| command == 'w') && (space == ' '))
				isValid = true;
			else {
				System.out.println("Invalid input! Try again.");
				System.out.print(">");
			}
		}
	}

	/**
	 * Takes in an ArrayList from fileInput and saves it to lineList
	 * 
	 * @param text
	 *            is the name of the file
	 */
	public void getFileFromDirectory(String text) {

		try {
			lineList = fileInput.accessFile(text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Increases the counter to move down the file, unless the counter is already at
	 * the end of the file. Then it warns the user.
	 */
	public void goDownOneLine() {
		if (lineList.size() - 1 > counter)
			counter++;
		if (lineList.size() - 1 == counter)
			System.out.println("You are at the end of the file.");
	}

	/**
	 * Saves the file to a name called fileName with the content "lineList".
	 */
	public void setFileToDirectory() {
		try {
			fileOutput.saveFileToDirectory(fileName, lineList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(fileName + " written. Goodbye!");
	}

}
