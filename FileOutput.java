import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * FileOutput's sole goal is to write a file with the name and the content that
 * the user specified
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class FileOutput {
	/**
	 * The method saves the specified lines by parsing the ArrayList lineList and
	 * saving it with the name fileName.
	 * 
	 * @param fileName
	 * @param lineList
	 *            is the ArrayList of lines that will be written into the file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void saveFileToDirectory(String fileName, ArrayList<String> lineList)
			throws IOException, FileNotFoundException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"))) {
			for (String line : lineList)
				writer.write(line + "\n");
		}
	}
}