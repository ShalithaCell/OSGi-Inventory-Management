package com.june_we_04.osgi.inventoryconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.june_we_04.osgi.inventoryconsumer.application.ApplicationStartup;
import com.june_we_04.osgi.inventoryconsumer.model.ServiceRegister;

public class InventoryConsumerActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		InventoryConsumerActivator.context = bundleContext;
		
		try {
			ServiceRegister servicveRegister = ServiceRegister.getInstance(); 
			
			servicveRegister.RegisterServices(bundleContext);
			
			ApplicationStartup startup = new ApplicationStartup();
			startup.Start();
		}catch (Exception e) {
			System.out.println("Error InventoryConsumer");
			System.out.println(e.getLocalizedMessage());
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		InventoryConsumerActivator.context = null;
	}

}
