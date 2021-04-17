package com.june_we_04.osgi.authservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.june_we_04.osgi.authservice.application.AuthServiceImpl;
import com.june_we_04.osgi.authservice.interfaces.IAuthService;

public class Activator implements BundleActivator {

	private static BundleContext context;
	
	ServiceRegistration serviceRegister;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		
		IAuthService authService = new AuthServiceImpl();
		
		serviceRegister = context.registerService(IAuthService.class.getName(), authService, null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
		
		serviceRegister.unregister();
	}

}
