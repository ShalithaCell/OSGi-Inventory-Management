package com.june_we_04.osgi.inventoryconsumer.model;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.june_we_04.osgi.authservice.interfaces.IAuthService;
import com.june_we_04.osgi.financialservice.interfaces.IFinancialService;
import com.june_we_04.osgi.inventoryservice.interfaces.IItemService;
import com.june_we_04.osgi.storeservice.interfaces.IStoreService;

public class ServiceRegister {
	
	private BundleContext _context;
	
	private ServiceReference _authService;
	private ServiceReference _storeService;
	private ServiceReference _financialService;
	private ServiceReference _inventoryService;
	
	
	// static variable single_instance of type Singleton
    private static ServiceRegister single_instance = null;
	
    public static ServiceRegister getInstance()
    {
        if (single_instance == null)
            single_instance = new ServiceRegister();
  
        return single_instance;
    }
    
    public void RegisterServices(
    		BundleContext context) {
    	
    	_context = context;
    	_authService = context.getServiceReference(IAuthService.class.getName());
    	_storeService = context.getServiceReference(IStoreService.class.getName());
    	_financialService = context.getServiceReference(IFinancialService.class.getName());
    	_inventoryService  = context.getServiceReference(IItemService.class.getName());
		
    }
    
    public IAuthService GetAuthService() {
    	return (IAuthService) _context.getService(_authService);
    }
    
    public IStoreService GetStoreService() {
    	return (IStoreService) _context.getService(_storeService);
    }
    
    public IFinancialService GetFinancialService() {
    	return (IFinancialService) _context.getService(_financialService);
    }
    
    public IItemService GetInventoryService() {
    	return (IItemService) _context.getService(_inventoryService);
    }

}
