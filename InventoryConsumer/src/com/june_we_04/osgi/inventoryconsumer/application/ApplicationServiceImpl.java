package com.june_we_04.osgi.inventoryconsumer.application;

import java.util.Scanner;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IApplicationService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;
import com.june_we_04.osgi.storeservice.model.Store;

public class ApplicationServiceImpl implements IApplicationService{
	
	private IDisplayService displayService;
	private Scanner scanner;
	
	public ApplicationServiceImpl() {
		displayService =  new DisplayServiceImpl();
		scanner = new Scanner(System.in); 
	}

	@Override
	public boolean IsAuthenticated() {

		// check user object is null or not
		// if user object is null, not authenticated.
		if(InMemoryDb.getInstance().getUser() == null) {
			return false;
		}
		
		// get the user service
		var authService = ServiceRegister.getInstance().GetAuthService();
		
		// check that user is authenticated.
		return authService.Authenticate(InMemoryDb.getInstance().getUser().getUserID(), InMemoryDb.getInstance().getUser().getPassword());
	}

	@Override
	public void StoreRegistration() {
		
		boolean success = false;
		
		do {
			displayService.DisplayLineBreak();
			displayService.InLineDisplay("Enter your store name : ");
			
			
			String storeName = scanner.next();
			
			if(storeName.isBlank()) {
				displayService.Display("Please Enter valid Store name");
				displayService.DisplayLineBreak();
				
				continue;
			}
			
			if(storeName.chars().count() <= 2) {
				displayService.Display("Please Enter valid Store name(Minimum character is 3)");
				displayService.DisplayLineBreak();
				
				continue;
			}
			
			// get the user service
			var storeService = ServiceRegister.getInstance().GetStoreService();
			
			// create store Object
			Store store = new Store();
			store.setName(storeName);
			
			store = storeService.RegisterNewStore(store);
			
			displayService.DisplayLineBreak();
			displayService.Display("Store Registered sucessfully.");
			displayService.Display("Store ID   :  = " + store.getStoreID());
			displayService.Display("Store Name :  = " + store.getName());
			displayService.DisplayLineBreak();
			
			
			// save to memory db
			InMemoryDb.getInstance().setStore(store);
			success = true;
		
		} while (!success);
		
		
		
		
	}

	@Override
	public void ShowMenu() {
		displayService.Display("============== Main  Menu ================");
		displayService.DisplayLineBreak();
		displayService.Display(ApplicationConst.MENU_USER_MANAGEMENT + ". User Management");
		displayService.Display(ApplicationConst.MENU_STORE_MANAGEMENT + ". Store Management");
		displayService.Display(ApplicationConst.MENU_INVENTORY_MANAGEMENT + ". Inventory Management");
		displayService.Display(ApplicationConst.MENU_FINANCIAL_INFORMATION + ". Financial Information");
		displayService.Display(ApplicationConst.EXIT_CODE + ". Exit");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		InMemoryDb.getInstance().SetActionID(scanner.nextInt());
	}

	@Override
	public void UserManagement() {
		displayService.Display("-------------- User Management --------------");
		displayService.Display(ApplicationConst.MENU_USER_MANAGEMENT_RESET_PASSWORD + ". Reset Password");
		displayService.Display(ApplicationConst.MENU_USER_MANAGEMENT_CHANGE_USERNAME + ". Change User name");
		displayService.Display(ApplicationConst.MENU_USER_MANAGEMENT_USER_INFO + ". Display User Information");
		displayService.Display(ApplicationConst.MENU_USER_MANAGEMENT_UNREGISTER + ". Remove My User Account");
		displayService.Display(ApplicationConst.MENU_WIZARD_CODE + ". Return to Main Menu");
		displayService.Display(ApplicationConst.EXIT_CODE + ". Exit");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		InMemoryDb.getInstance().SetActionID(scanner.nextInt());
	}

	@Override
	public void StoreManagement() {
		displayService.Display("-------------- Store Management --------------");
		displayService.Display(ApplicationConst.MENU_STORE_MANAGEMENT_UPDATE_STORE_NAME + ". Update Store");
		displayService.Display(ApplicationConst.MENU_STORE_MANAGEMENT_GET_STORE_DETAILS + ". Display Store Details");
		displayService.Display(ApplicationConst.MENU_STORE_MANAGEMENT_REMOVE_STORE + ". Remove Store");
		displayService.Display(ApplicationConst.MENU_WIZARD_CODE + ". Return to Main Menu");
		displayService.Display(ApplicationConst.EXIT_CODE + ". Exit");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		InMemoryDb.getInstance().SetActionID(scanner.nextInt());
	}

	@Override
	public void InventoryManagement() {
		
		displayService.Display("-------------- Inventory Management --------------");
		displayService.Display(ApplicationConst.MENU_INVENTORY_MANAGEMENT_NEW_ITEM + ". Add New Item");
		displayService.Display(ApplicationConst.MENU_INVENTORY_MANAGEMENT_UPDATE_ITEM + ". Update Item");
		displayService.Display(ApplicationConst.MENU_INVENTORY_MANAGEMENT_REMOVE_ITEM + ". Remove Item");
		displayService.Display(ApplicationConst.MENU_INVENTORY_MANAGEMENT_GET_INVENTORY + ". Show Inventory");
		displayService.Display(ApplicationConst.MENU_INVENTORY_MANAGEMENT_GET_ITEM + ". Show Item Details");
		displayService.Display(ApplicationConst.MENU_WIZARD_CODE + ". Return to Main Menu");
		displayService.Display(ApplicationConst.EXIT_CODE + ". Exit");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		InMemoryDb.getInstance().SetActionID(scanner.nextInt());
	}

	@Override
	public void FinantialManagement() {
		
		displayService.Display("-------------- Financial Management --------------");
		displayService.Display(ApplicationConst.MENU_FINANCIAL_INFORMATION_SHOW_STATUS + ". Show inventory Transaction Status");
		displayService.Display(ApplicationConst.MENU_WIZARD_CODE + ". Return to Main Menu");
		displayService.Display(ApplicationConst.EXIT_CODE + ". Exit");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		InMemoryDb.getInstance().SetActionID(scanner.nextInt());
	}

}
