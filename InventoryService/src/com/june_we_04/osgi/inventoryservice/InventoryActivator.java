package com.june_we_04.osgi.inventoryservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.june_we_04.osgi.inventoryservice.application.ItemServiceImpl;
import com.june_we_04.osgi.inventoryservice.interfaces.IItemService;

public class InventoryActivator implements BundleActivator {

	private static BundleContext context;
	
	ServiceRegistration serviceRegister;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		InventoryActivator.context = bundleContext;
		
		IItemService itemService = new ItemServiceImpl();
		
		try {
			serviceRegister = context.registerService(IItemService.class.getName(), itemService, null);
		}catch(Exception ex) {
			System.out.println("Error Inventory Service");
			System.out.println(ex.getLocalizedMessage());
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		InventoryActivator.context = null;
		
		serviceRegister.unregister();
	}

}
