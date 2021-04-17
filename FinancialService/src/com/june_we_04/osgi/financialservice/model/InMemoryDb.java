package com.june_we_04.osgi.financialservice.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class InMemoryDb {
	
	// static variable single_instance of type Singleton
    private static InMemoryDb single_instance = null;
    
    private ArrayList<FinancialLog> financialInformation;
    
    private InMemoryDb() {
		super();
		financialInformation = new ArrayList<FinancialLog>();  
	}
    
    public static InMemoryDb getInstance()
    {
        if (single_instance == null)
            single_instance = new InMemoryDb();
  
        return single_instance;
    }
    
    public List<FinancialLog> GetFinancialInformation(int storeID){
    	return  financialInformation.stream()
    		    .filter(p -> p.getStoreID() == storeID).collect(Collectors.toList());
    }
    
    public boolean AddFinancialRecord(FinancialLog record) {
    	financialInformation.add(record);
    	
    	return true;
    }
    
    public void clearFinancialLog() {
    	financialInformation.clear();
    }

}
