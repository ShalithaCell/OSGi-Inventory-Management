package com.june_we_04.osgi.inventoryconsumer.application;

import com.june_we_04.osgi.inventoryconsumer.interfaces.IApplicationService;
import com.june_we_04.osgi.inventoryconsumer.model.InMemoryDb;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;

public class ApplicationServiceImpl implements IApplicationService{

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

}
