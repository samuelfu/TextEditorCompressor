import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileInput is a class built to access and return what is inside a specific
 * file
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class FileInput {
	/**
	 * accessFile attempts to open a file specified by the uesr. If it fails, a new
	 * file will be written with the same name. An empty ArrayList will be returned.
	 * If it succeeds, the contents are parsed into an ArrayList and returned.
	 * 
	 * @param text
	 *            is name of file
	 * @return an ArrayList of Strings
	 * @throws IOException
	 */
	public ArrayList<String> accessFile(String text) throws IOException {
		ArrayList<String> lineList = new ArrayList<String>();
		try {
			Scanner fileInput = new Scanner(new File(text));
			while (fileInput.hasNextLine()) {
				lineList.add(fileInput.nextLine());
			}
		} catch (FileNotFoundException e) {
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(text), "utf-8"))) {
				writer.write("");
			}

			System.out.println("File not found, " + text + " created.");
			return lineList;
		}
		return lineList;

	}

}