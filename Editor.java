
/**
 * Editor class builds a "commandLine". The "commandLine" can be an instance of
 * either Talker or Compressor. Editor is used by Runner to execute the main
 * loop of code startEditor().
 * 
 * Test: talkerTrial.txt 65 bytes 
 * Test: compressorTrial.cmp 84 bytes 
 * In these tests the compressor managed to INCREASE the file size. If I had a longer
 * file with more repeating words then .txt is sure to be smaller.
 * 
 * @author Samuel Fu ssf2130 COMS1007
 *
 */
public class Editor {
	private Processor commandLine = new Compressor(); // Compressor() or Talker() depending on what file the user wants

	/**
	 * Displays a welcome message
	 */
	public void welcome() {
		System.out.println("Start editing!");
	}

	/**
	 * In a never ending loop (unless the user presses q), the command line prompts
	 * the user for actions and does them.
	 */
	public void startEditor() {

		while (true) {
			commandLine.prompt();
			commandLine.askInput();
			commandLine.followCommand();
		}

	}
}
