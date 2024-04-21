/**
 * 
 */
package com.opentext;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This class is the entry point for the JVM to start executing the application
 * 
 * @author SangameshVi
 *
 */
public class ExecutionHandler {

	/**
	 * This main method is the function that is being called by the JVM
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < Constants.INPUT_PARAMS) {
			System.out.println("Kindly mention the input file path");
			System.exit(0);
		} else if (args.length > Constants.INPUT_PARAMS) {
			System.out.println("Program accepts only one input parameter");
			System.exit(0);
		}

		String inputFilePath = args[0];
		List<String> readFileInList = null;
		try {
			readFileInList = new InputFileReader(inputFilePath).readInputFile();
		} catch (FileNotFoundException e) {
			System.out.println("The given input file doesn't exist. Kindly run the application with a valid file path.");
			System.exit(0);
		}

		System.out.println(
				"Kindly enter a valid command. Following are the supported commands\n1. list\n2. ins <row number> [content to insert]\n3. del <row number>\n4. save\n5. quit\n\n<> represents the required params\n[] represents optional params");

		if (null != readFileInList) {
			CommandsExecutor commandsExecutor = new CommandsExecutor(readFileInList);
			commandsExecutor.executeCommand();
		}
	}

}
