package com.june_we_04.osgi.inventoryconsumer.application;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IInventoryManagementService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;
import com.june_we_04.osgi.inventoryservice.model.Item;

public class InventoryManagementServiceImpl implements IInventoryManagementService{
	
	private IDisplayService displayService;
	private Scanner scanner;
	
	public InventoryManagementServiceImpl() {
		displayService =  new DisplayServiceImpl();
		scanner = new Scanner(System.in);
	}

	@Override
	public boolean AddNewItem() {
		
		boolean status = false;
		
		displayService.Display("Add new Item");
		displayService.DisplayLineBreak();
		
		do {
			
			displayService.InLineDisplay("Item name : ");
			String name = scanner.next();
			
			if(name.chars().count() <= 0) {
				displayService.Display("Invalid Item name");
				displayService.DisplayLineBreak();
				continue;
			}
			
			displayService.InLineDisplay("Item category : ");
			String category = scanner.next();
			
			if(category.chars().count() <= 0) {
				displayService.Display("Invalid category name");
				displayService.DisplayLineBreak();
				continue;
			}
			
			displayService.InLineDisplay("QTY : ");
			int qty = scanner.nextInt();
			
			displayService.InLineDisplay("Buy Price : ");
			double buyingPrice = scanner.nextDouble();
			
			displayService.InLineDisplay("Sell Price : ");
			double sellPrice = scanner.nextDouble();
			
			// get the inventory service
			var inventoryService = ServiceRegister.getInstance().GetInventoryService();
			
			Item item = new Item();
			item.setName(name);
			item.setCategory(category);
			item.setQty(qty);
			item.setBuyPrice(buyingPrice);
			item.setSellPrice(sellPrice);
			item.setStoreID(InMemoryDb.getInstance().getStore().getStoreID());
			
			inventoryService.AddNewItem(item);
			
			displayService.Display("Item Added Successfully.");
			displayService.DisplayLineBreak();
			
			displayService.Display("Are you want to add another item ?");
			displayService.DisplayLineBreak();
			
			displayService.Display("1. Yes");
			displayService.Display("2. No");
			displayService.DisplayLineBreak();
			
			displayService.InLineDisplay("Enter Action ID : ");
			int action = scanner.nextInt();
			
			if(action == 1) {
				continue;
			}else {
				status = true;
				InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			}
			
		} while (!status);
		
		
		return false;
	}

	@Override
	public boolean UpdateItem() {
		boolean status = false;
		
		do {
			displayService.InLineDisplay("Enter Item code (300 : back to menu ): ");
			int itemCode = scanner.nextInt();
			
			if(itemCode == 300) {
				InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
				return false;
			}
			
			// get the inventory service
			var inventoryService = ServiceRegister.getInstance().GetInventoryService();
			Optional<Item> item = inventoryService.GetItem(itemCode);
			
			if(!item.isPresent()) {
				displayService.Display("Invalid Item Code");
				displayService.DisplayLineBreak();
				continue;
			}
			
			Item itemObj = item.get();
			
			displayService.InLineDisplay("Item name : ");
			String name = scanner.next();
			
			if(name.chars().count() <= 0) {
				displayService.Display("Invalid Item name");
				displayService.DisplayLineBreak();
				continue;
			}
			
			displayService.InLineDisplay("Item category : ");
			String category = scanner.next();
			
			if(category.chars().count() <= 0) {
				displayService.Display("Invalid category name");
				displayService.DisplayLineBreak();
				continue;
			}
			
			displayService.InLineDisplay("QTY : ");
			int qty = scanner.nextInt();
			
			displayService.InLineDisplay("Buy Price : ");
			double buyingPrice = scanner.nextDouble();
			
			displayService.InLineDisplay("Sell Price : ");
			double sellPrice = scanner.nextDouble();
			
			itemObj.setName(name);
			itemObj.setCategory(category);
			itemObj.setQty(qty);
			itemObj.setBuyPrice(buyingPrice);
			itemObj.setSellPrice(sellPrice);
			
			inventoryService.UpdateItem(itemObj);
			
			displayService.Display("Item Updated Successfully.");
			displayService.DisplayLineBreak();
			
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			
			status = true;
			
		}while(!status);
		
		return false;
	}

	@Override
	public boolean RemoveItem() {
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter password (for verification): ");
		String password = scanner.next();
		
		// get the user service
		var authService = ServiceRegister.getInstance().GetAuthService();
		
		boolean success = authService.Authenticate(InMemoryDb.getInstance().getUser().getUserID(), password);
		
		if(!success) {
			displayService.Display("Password is Incorrect !");
			displayService.DisplayLineBreak();
			
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			return false;
		}
		
		displayService.InLineDisplay("Enter Item code (300 : back to menu ): ");
		int itemCode = scanner.nextInt();
		
		if(itemCode == 300) {
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			return false;
		}
		
		// get the inventory service
		var inventoryService = ServiceRegister.getInstance().GetInventoryService();
		Optional<Item> item = inventoryService.GetItem(itemCode);
		
		if(!item.isPresent()) {
			displayService.Display("Invalid Item Code");
			displayService.DisplayLineBreak();
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			return false;
		}
		
		displayService.Display("Are you sure want to remove Item '" + item.get().getName() + "' ?");
		displayService.Display("1. Yes");
		displayService.Display("2. No");
		displayService.DisplayLineBreak();
		
		displayService.InLineDisplay("Enter Action ID : ");
		int action = scanner.nextInt();
		
		if(action != 1) {
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			return false;
		}
		
		inventoryService.RemoveItem(itemCode);
		
		displayService.Display("Item removed successfully !");
		displayService.DisplayLineBreak();
		InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
		
		return true;
	}

	@Override
	public void DisplayInventory() {
		
		// get the inventory service
		var inventoryService = ServiceRegister.getInstance().GetInventoryService();
		
		List<Item> inventory = inventoryService.GetInventory(InMemoryDb.getInstance().getStore().getStoreID());
		
		displayService.Display("------------ Inventory ------------");
		displayService.DisplayLineBreak();
		
		displayService.Display("Irtem Code \t Item Name \t QTY \t Buy Price \t Sell Price");
		for (Item item : inventory) {
			displayService.Display(item.getItemID() + " \t\t"+ item.getName() + "\t\t" + item.getQty() + "\t\t" + item.getBuyPrice() + "\t\t" + item.getSellPrice());
		}
		displayService.DisplayLineBreak();
		InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
	}

	@Override
	public void GetItemDetails() {
		displayService.InLineDisplay("Enter Item code (300 : back to menu ): ");
		int itemCode = scanner.nextInt();
		
		if(itemCode == 300) {
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			return;
		}
		
		// get the inventory service
		var inventoryService = ServiceRegister.getInstance().GetInventoryService();
		Optional<Item> item = inventoryService.GetItem(itemCode);
		
		if(!item.isPresent()) {
			displayService.Display("Invalid Item Code");
			displayService.DisplayLineBreak();
			InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
			return;
		}
		
		Item itemObj = item.get();
		displayService.Display("Item Code       : " + itemObj.getItemID());
		displayService.Display("Item Name       : " + itemObj.getName());
		displayService.Display("Item QTY        : " + itemObj.getQty());
		displayService.Display("Item Category   : " + itemObj.getCategory());
		displayService.Display("Item Buy Price  : " + itemObj.getBuyPrice());
		displayService.Display("Item Sell Price : " + itemObj.getSellPrice());
		displayService.DisplayLineBreak();
		
		InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
		
	}

}
