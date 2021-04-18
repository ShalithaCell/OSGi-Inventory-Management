package com.june_we_04.osgi.inventoryconsumer.application;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IApplicationService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IFinancialInformationService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IInventoryManagementService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IStoreManagementService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IUserManagementService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;


import java.util.Scanner;

public final class ApplicationStartup {
	
	private IApplicationService applicationService;
	private IDisplayService displayService;
	private IUserManagementService userManagementService;
	private IStoreManagementService storeManagementService;
	private IInventoryManagementService inventoryManagementService;
	private IFinancialInformationService financialInformationService;
	private Scanner scanner;
	
	public ApplicationStartup() {
		applicationService = new ApplicationServiceImpl();
		displayService =  new DisplayServiceImpl();
		userManagementService = new UserManagementServiceImpl();
		storeManagementService = new StoreManagementServiceImpl();
		inventoryManagementService = new InventoryManagementServiceImpl();
		financialInformationService = new FinancialInformationService();
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
			case ApplicationConst.START_WIZARD_CODE:
				displayService.Display("1. Login to the application");
				displayService.Display("2. If your new to the system.");
				displayService.DisplayLineBreak();
				
				displayService.InLineDisplay("Enter Action ID : ");
				InMemoryDb.getInstance().SetActionID(scanner.nextInt());
				break;
			case 99:
				continue;
			case 1:
				boolean statusAuth =  userManagementService.Login();
				if(statusAuth) {
					// show menu
					InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_WIZARD_CODE);
				}
				break;
				
			case 2:
				applicationService.StoreRegistration();
				
				if(!applicationService.IsAuthenticated()) {
					displayService.Display("Next create a user for store !!");
					displayService.DisplayLineBreak();
					
					// create user
					userManagementService.RegisterNewUserAccount();
					displayService.Display("Login to the system uing the credentials !");
					displayService.DisplayLineBreak();
					
					boolean status =  userManagementService.Login();
					if(status) {
						// show menu
						InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_WIZARD_CODE);
					}
				}else {
					// show menu
					InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_WIZARD_CODE);
				}
				break;
				
			case ApplicationConst.MENU_WIZARD_CODE:
				applicationService.ShowMenu();
				break;
				
			case  ApplicationConst.MENU_USER_MANAGEMENT:
				applicationService.UserManagement();
				break;
				
			case ApplicationConst.MENU_USER_MANAGEMENT_RESET_PASSWORD:
				userManagementService.ResetPassword();
				break;
				
			case ApplicationConst.MENU_USER_MANAGEMENT_CHANGE_USERNAME:
				userManagementService.ChangeUserName();
				break;
				
			case ApplicationConst.MENU_USER_MANAGEMENT_UNREGISTER:
				userManagementService.RemoveUser();
				break;
				
			case ApplicationConst.MENU_STORE_MANAGEMENT:
				applicationService.StoreManagement();
				break;
				
			case ApplicationConst.MENU_STORE_MANAGEMENT_UPDATE_STORE_NAME:
				boolean updateStatus = storeManagementService.UpdateStore();
				
				if(updateStatus) {
					displayService.Display("Succssfully Updated the Store.");
					displayService.DisplayLineBreak();
					
					InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
				}
				break;
				
			case ApplicationConst.MENU_STORE_MANAGEMENT_GET_STORE_DETAILS:
				storeManagementService.ShowStoreInformation();
				break;
				
			case ApplicationConst.MENU_STORE_MANAGEMENT_REMOVE_STORE:
				storeManagementService.RemoveStore();
				break;
				
			case ApplicationConst.MENU_INVENTORY_MANAGEMENT:
				applicationService.InventoryManagement();
				break;
				
			case ApplicationConst.MENU_INVENTORY_MANAGEMENT_NEW_ITEM:
				inventoryManagementService.AddNewItem();
				break;
				
			case ApplicationConst.MENU_INVENTORY_MANAGEMENT_UPDATE_ITEM:
				inventoryManagementService.UpdateItem();
				break;
				
			case ApplicationConst.MENU_INVENTORY_MANAGEMENT_REMOVE_ITEM:
				inventoryManagementService.RemoveItem();
				break;
				
			case ApplicationConst.MENU_INVENTORY_MANAGEMENT_GET_INVENTORY:
				inventoryManagementService.DisplayInventory();
				break;
				
			case ApplicationConst.MENU_INVENTORY_MANAGEMENT_GET_ITEM:
				inventoryManagementService.GetItemDetails();
				break;
				
			case ApplicationConst.MENU_FINANCIAL_INFORMATION:
				applicationService.FinantialManagement();
				break;
				
			case ApplicationConst.MENU_FINANCIAL_INFORMATION_SHOW_STATUS:
				financialInformationService.GetFinancialStatusOfInventory();
				break;
				
			default:
				displayService.Display("Invalid Action ID !!!!");
				displayService.DisplayLineBreak();
				
				InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_WIZARD_CODE);
				break;
			}
			
		} while (InMemoryDb.getInstance().GetActionID() != ApplicationConst.EXIT_CODE);
		
	}

}
