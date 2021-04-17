package com.june_we_04.osgi.inventoryconsumer.interfaces;

public interface IDisplayService {

	/**
	 * Display in console
	 * @param text
	 */
	public void Display(String text);
	
	/**
	 * Display in-line text
	 * @param text
	 */
	public void InLineDisplay(String text);
	
	/**
	 * Add line break
	 */
	public void DisplayLineBreak();
}
