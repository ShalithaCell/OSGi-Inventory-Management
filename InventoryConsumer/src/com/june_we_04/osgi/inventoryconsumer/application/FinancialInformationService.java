package com.june_we_04.osgi.inventoryconsumer.application;

import java.util.List;
import java.util.Scanner;

import com.june_we_04.osgi.financialservice.model.FinancialLog;
import com.june_we_04.osgi.financialservice.model.TotalTransactionStatus;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IDisplayService;
import com.june_we_04.osgi.inventoryconsumer.interfaces.IFinancialInformationService;
import com.june_we_04.osgi.inventoryconsumer.model.ApplicationConst;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;
import com.june_we_04.osgi.inventoryservice.model.Item;

public class FinancialInformationService implements IFinancialInformationService{
	
	private IDisplayService displayService;
	private Scanner scanner;
	
	public FinancialInformationService() {
		displayService =  new DisplayServiceImpl();
		scanner = new Scanner(System.in);
	}

	@Override
	public void GetFinancialStatusOfInventory() {
		// get the inventory service
		var inventoryService = ServiceRegister.getInstance().GetInventoryService();
		
		// get the financial service
		var financialService = ServiceRegister.getInstance().GetFinancialService();
		financialService.CleanAllRecords();
		
		List<Item> inventory = inventoryService.GetInventory(InMemoryDb.getInstance().getStore().getStoreID());
		
		if(inventory.isEmpty()) {
			displayService.DisplayLineBreak();
			displayService.Display("Inventory Empty !");
			return;
		}
		
		for (Item item : inventory) {
			
			FinancialLog log = new FinancialLog(item.getItemID(), 
					item.getName(), item.getQty(),item.getSellPrice(),
					item.getBuyPrice(), item.getStoreID() );
			
			financialService.AddNewRecord(log);
		}
		
		TotalTransactionStatus status = financialService.GetFinancialStatus(InMemoryDb.getInstance().getStore().getStoreID());
		
		displayService.DisplayLineBreak();
		displayService.Display("----------Inventory Financial Status----------");
		
		displayService.Display("Net Profit : LKR"+ status.getNetProfit());
		displayService.Display("Total : LKR"+ status.getTotalTransactionAmount());
		
		
		InMemoryDb.getInstance().SetActionID(ApplicationConst.MENU_INVENTORY_MANAGEMENT);
	}

}
