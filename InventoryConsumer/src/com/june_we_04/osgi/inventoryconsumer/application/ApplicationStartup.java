package com.june_we_04.osgi.inventoryconsumer.application;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IApplicationService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import java.util.Scanner;

public final class ApplicationStartup {
	
	private IApplicationService applicationService;
	private IDisplayService displayService;
	private Scanner scanner;
	
	public ApplicationStartup() {
		applicationService = new ApplicationServiceImpl();
		displayService =  new DisplayServiceImpl();
		scanner = new Scanner(System.in); 
	}
	
	public void Start() {
		
		System.out.println("================================= Welcome to Inventory SAAS Application =================================");
		
		do {
			
			// auth part
			if(!applicationService.IsAuthenticated()) {
				displayService.Display("Select Action -> ");
				displayService.DisplayLineBreak();
				
				displayService.Display("1. Login to the application");
				displayService.Display("2. If your new to the system.");
				displayService.DisplayLineBreak();
				
				displayService.InLineDisplay("Enter Action ID : ");
				InMemoryDb.getInstance().SetActionID(scanner.nextInt());
				
			}
			
			switch (InMemoryDb.getInstance().GetActionID()) {
			
			case 99:
				continue;
			case 1:

				break;

			default:
				break;
			}
			
		} while (InMemoryDb.getInstance().GetActionID() != ApplicationConst.EXIT_CODE);
		
	}

}
