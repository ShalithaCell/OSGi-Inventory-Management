package com.june_we_04.osgi.inventoryconsumer.application;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;

public class DisplayServiceImpl implements IDisplayService{

	@Override
	public void Display(String text) {
		System.out.println(text);
	}

	@Override
	public void DisplayLineBreak() {
		System.out.println();
	}

	@Override
	public void InLineDisplay(String text) {
		System.out.print(text);
	}

}
