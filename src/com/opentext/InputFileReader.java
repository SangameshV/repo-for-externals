/**
 * 
 */
package com.opentext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * This class is responsible for reading the input file and storing the read
 * data to a collection
 * 
 * @author SangameshVi
 *
 */
public class InputFileReader {

	private String filePath;

	/**
	 * Constructor
	 * 
	 * @param inputFilePath Input File path
	 * @throws FileNotFoundException 
	 */
	public InputFileReader(final String inputFilePath) throws FileNotFoundException {
		File inputFile = new File(inputFilePath);
		if (!inputFile.exists()) {
			throw new FileNotFoundException("input file doesn't exist");
		}
		this.filePath = inputFilePath;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> readInputFile() {
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
