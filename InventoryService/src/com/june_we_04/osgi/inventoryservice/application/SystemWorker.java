package com.june_we_04.osgi.inventoryservice.application;

import com.june_we_04.osgi.inventoryservice.interfaces.ISystemWorker;
import com.june_we_04.osgi.inventoryservice.model.ApplicationDelegate;

public class SystemWorker implements ISystemWorker{

	@Override
	public int GenerateUniqueItemID() {
		int id = ApplicationDelegate.ItemID++;
		return id;
	}

}
