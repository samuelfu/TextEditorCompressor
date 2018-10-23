/**
 * The main class that executes the program. Creates an instance of Editor to
 * start the prompt. The Editor is called, which calls a Processor, which
 * proceeds to use Compressor/Talker to edit files (.cmp/.txt) through
 * FileInput/FileOutput. Convertor is separate from the other .java files as it
 * is only used by ConvertorTester.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Runner {
	/**
	 * Starts the prompt
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Editor editor = new Editor();
		editor.welcome();
		editor.startEditor();
	}
}
