package com.june_we_04.osgi.financialservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.june_we_04.osgi.financialservice.application.FinancialServiceImpl;
import com.june_we_04.osgi.financialservice.interfaces.IFinancialService;

public class FinancialServiceActivator implements BundleActivator {

	private static BundleContext context;
	
	ServiceRegistration serviceRegister;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		FinancialServiceActivator.context = bundleContext;
		
		IFinancialService financialService = new FinancialServiceImpl();
		
		try {
			serviceRegister = context.registerService(IFinancialService.class.getName(), financialService, null);
		}catch(Exception ex) {
			System.out.println("Error FI");
			System.out.println(ex.getLocalizedMessage());
		}
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		FinancialServiceActivator.context = null;
		serviceRegister.unregister();
	}

}
