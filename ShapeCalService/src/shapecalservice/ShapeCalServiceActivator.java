package shapecalservice;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class ShapeCalServiceActivator implements BundleActivator {

	ServiceRegistration calculatorRegistration;


	public void start(BundleContext context) throws Exception {
		
		System.out.println("Calculator for shape service start!");
		
		ShapeCalService cal = new ShapeCalServiceImpl();
				
		
		calculatorRegistration = context.registerService(ShapeCalService.class.getName(), cal, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		
		System.out.println("Calculator for shape service stop!");
		calculatorRegistration.unregister();

}

}