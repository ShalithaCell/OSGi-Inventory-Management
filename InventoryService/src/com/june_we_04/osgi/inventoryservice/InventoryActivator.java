package com.june_we_04.osgi.inventoryservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class InventoryActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		InventoryActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		InventoryActivator.context = null;
	}

}
