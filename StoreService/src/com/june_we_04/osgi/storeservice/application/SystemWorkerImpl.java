package com.june_we_04.osgi.storeservice.application;

import com.june_we_04.osgi.storeservice.interfaces.ISystemWorker;
import com.june_we_04.osgi.storeservice.model.ApplicationDelegate;

public class SystemWorkerImpl implements ISystemWorker{
	

	@Override
	public int GenerateUniqueStoreID() {
		int id = ApplicationDelegate.StoreID++;
		return id;
	}

}
