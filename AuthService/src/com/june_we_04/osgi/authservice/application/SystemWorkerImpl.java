package com.june_we_04.osgi.authservice.application;

import com.june_we_04.osgi.authservice.interfaces.ISystemWorker;
import com.june_we_04.osgi.authservice.model.ApplicationDelegate;

public class SystemWorkerImpl implements ISystemWorker{

	@Override
	public int GenerateUniqueUserID() {
		int id = ApplicationDelegate.UserID++;
		return id;
	}

}
