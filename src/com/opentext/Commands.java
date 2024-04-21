/**
 * 
 */
package com.opentext;

/**
 * This enum has the list of commands the are supported for users actions
 * 
 * @author SangameshVi
 *
 */
public enum Commands {

	LIST("LIST"), DELETE("DEL"), INSERT("INS"), SAVE("SAVE"), QUIT("QUIT");

	/**
	 * 
	 */
	public final String label;

	private Commands(String label) {
		this.label = label;
	}

	/**
	 * @return the label of the enum
	 */
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return this.label;
	}
}
