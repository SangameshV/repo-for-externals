/**
 * 
 */
package com.opentext;

import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for executing the user commands on the file content
 * 
 * @author SangameshVi
 *
 */
public class CommandsExecutor {

	private List<String> readFileInList;

	/**
	 * Constructor
	 * 
	 * @param fileData The input file data is read and stored to a list each line
	 *                 being each entry in it
	 */
	public CommandsExecutor(final List<String> fileData) {
		this.readFileInList = fileData;
	}

	/**
	 * This method takes care to execute the user command
	 */
	public void executeCommand() {
		String command = Constants.EMPTY;
		while (!command.equalsIgnoreCase(Commands.QUIT.label)) {
			command = new Scanner(System.in).nextLine();
			if (command.equalsIgnoreCase(Commands.LIST.label)) {
				String output = readInputFileContent();
				System.out.println(output);
				continue;
			} else if (command.equalsIgnoreCase(Commands.QUIT.label)) {
				System.out.println("Shutting down the application. Thank you!");
				System.exit(0);
			} else if (command.toUpperCase().startsWith(Commands.DELETE.label)) {
				String[] splits = command.split(Constants.SPACE);
				try {
					int rowNumber = Integer.parseInt(splits[1]);
					if (rowNumber > readFileInList.size() || rowNumber < 1) {
						System.out.println("Row " + rowNumber + " doesn't exist. Failed to delete.");
					} else {
						readFileInList.remove(rowNumber - 1);
						System.out.println("Deleted the row " + rowNumber);
					}
				} catch (NumberFormatException expt) {
					System.out.println("Row Number must be an interger number");
				}
				continue;
			} else if (command.toUpperCase().startsWith(Commands.INSERT.label)) {
				String[] splits = command.split(Constants.SPACE);
				int rowNumber = 0;
				String content = Constants.EMPTY;
				try {
					if (splits.length == Constants.DEF_INPUT_PARAMS_FOR_INSERT_CMD) {
						rowNumber = Integer.parseInt(splits[1]);
						content = Constants.EMPTY;
					} else if (splits.length >= Constants.INPUT_PARAMS_FOR_INSERT_CMD) {
						rowNumber = Integer.parseInt(splits[1]);
						content = command.replace(splits[0], Constants.EMPTY).replace(splits[1], Constants.EMPTY)
								.trim();
					} else {
						System.out.println(
								"Insert command must have a row number and/or content to insert (seperated by a space)");
						continue;
					}
				} catch (NumberFormatException expt) {
					System.out.println("Row Number must be an interger number");
				}

				if (rowNumber < 1) {
					System.out.println("Insert is not possible below Row#1.");
					continue;
				}

				int existingContentLines = readFileInList.size();
				/**
				 * If 0 < rowNumber > rows_in_file, add an element to the list and adjust the row numbers of subsequent rows<br>
				 * 
				 */
				if (rowNumber <= existingContentLines) {
					readFileInList.add(rowNumber - 1, content.trim());
				} else {
					int additionalItems = rowNumber - existingContentLines;
					while (additionalItems != 0) {
						if (additionalItems == 1) {
							readFileInList.add(rowNumber - 1, content.trim());
						} else {
							readFileInList.add(Constants.EMPTY);
						}
						additionalItems--;
					}
				}
				System.out.println("Inserted at line#" + rowNumber);
				continue;
			} else if (command.equalsIgnoreCase(Commands.SAVE.label)) {
				OutputFileWriter outputFileWriter = new OutputFileWriter();
				String filePath = outputFileWriter.writeToFile(readFileInList);
				System.out.println("File saved at " + filePath);
				continue;
			} else {
				System.out.println(
						"Kindly enter a valid command. Following are the supported commands\n1. list\n2. ins <row number> [content to insert]\n3. del <row number>\n4. save\n5. quit\n\n<> represents the required params\n[] represents optional params");
				continue;
			}
		}

	}

	private String readInputFileContent() {
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		for (String lineData : readFileInList) {
			stringBuffer.append(++count);
			stringBuffer.append(Constants.COLON);
			stringBuffer.append(lineData);
			stringBuffer.append(Constants.NEXT_LINE_CHAR);
		}
		return stringBuffer.toString();
	}
}
