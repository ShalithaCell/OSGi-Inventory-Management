package com.june_we_04.osgi.inventoryconsumer.application;

import java.util.Optional;
import java.util.Scanner;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IStoreManagementService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;
import com.june_we_04.osgi.storeservice.model.Store;

public class StoreManagementServiceImpl implements IStoreManagementService{
	
	private IDisplayService displayService;
	private Scanner scanner;
	
	public StoreManagementServiceImpl() {
		displayService =  new DisplayServiceImpl();
		scanner = new Scanner(System.in);
	}

	@Override
	public boolean UpdateStore() {
		boolean status = false;
		
		do {
			displayService.InLineDisplay("Enter new store name (200 : back to menu ): ");
			String name = scanner.next();
			
			if(name == "99") {
				InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
				return status;
			}
			
			if(name.chars().count() <= 2) {
				displayService.Display("Enter valid store name.(please enter more than 2 characters)");
				displayService.DisplayLineBreak();
				continue;
			}
			
			// get the user service
			var storeService = ServiceRegister.getInstance().GetStoreService();
			
			Store store = new Store();
			store.setName(name);
			store.setStoreID(InMemoryDb.getInstance().getStore().getStoreID());
			
			storeService.UpdateStoreInformation(store);
			
			status = true;
		} while (!status);
		
		return status;
	}

	@Override
	public void ShowStoreInformation() {
		// get the user service
		var storeService = ServiceRegister.getInstance().GetStoreService();
		
		Optional<Store> store = storeService.GetStoreInformation(InMemoryDb.getInstance().getStore().getStoreID());
		
		if(store.isPresent()) {
			displayService.DisplayLineBreak();
			displayService.Display("Store ID   : "+ store.get().getStoreID());
			displayService.Display("Store Name : "+ store.get().getName());
			displayService.DisplayLineBreak();
		}
		
		InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
	}

	@Override
	public void RemoveStore() {
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter password (for verification): ");
		String password = scanner.next();
		
		// get the user service
		var authService = ServiceRegister.getInstance().GetAuthService();
		
		boolean success = authService.Authenticate(InMemoryDb.getInstance().getUser().getUserID(), password);
		
		if(!success) {
			displayService.Display("Password is Incorrect !");
			displayService.DisplayLineBreak();
			
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
			return;
		}
		
		displayService.Display("Are you sure want to remove store '" + InMemoryDb.getInstance().getStore().getName() + "' ?");
		displayService.Display("1. Yes");
		displayService.Display("2. No");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		int action = scanner.nextInt();
		
		if(action != 1) {
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_STORE_MANAGEMENT);
			return;
		}
		
		var storeService = ServiceRegister.getInstance().GetStoreService();
		
		storeService.RemoveStore(InMemoryDb.getInstance().getStore().getStoreID());
		authService.RemoveUser(InMemoryDb.getInstance().getUser().getUserID());
		
		displayService.DisplayLineBreak();
		displayService.Display("Store remove successfully !");
		displayService.Display("Store assosiated users also deleted !");
		displayService.DisplayLineBreak();
		
		InMemoryDb.getInstance().setUser(null);
		InMemoryDb.getInstance().setStore(null);
		InMemoryDb.getInstance().SetActionID(ApplicationConst.START_WIZARD_CODE);
	}

}
