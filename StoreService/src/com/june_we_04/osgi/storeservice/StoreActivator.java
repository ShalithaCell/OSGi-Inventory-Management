package com.june_we_04.osgi.storeservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class StoreActivator implements BundleActivator {

	private static BundleContext context;
	
	ServiceRegistration serviceRegister;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		StoreActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		StoreActivator.context = null;
	}

}
