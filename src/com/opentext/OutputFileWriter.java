/**
 * 
 */
package com.opentext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author SangameshVi
 *
 */
public class OutputFileWriter {

	/**
	 * 
	 * @param readFileInList
	 * @param fileWriteCounter
	 * @return
	 */
	public String writeToFile(List<String> readFileInList) {
		String userHome = System.getProperty(Constants.USER_HOME_KEY);
		String filePath = userHome + File.separator + Constants.OUTPUT_FILE_NAME + Constants.OUTPUT_FILE_EXTENSION;
		StringBuffer buffer = new StringBuffer();
		for (String content : readFileInList) {
			buffer.append(content);
			buffer.append(Constants.NEXT_LINE_CHAR);
		}

		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File(filePath)));
			bufferedWriter.write(buffer.toString());
		} catch (Exception expt) {
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return filePath;
	}

}
