package com.june_we_04.osgi.inventoryservice.model;

import java.util.ArrayList;

public class InMemoryDb {

	// static variable single_instance of type Singleton
    private static InMemoryDb single_instance = null;
    
    private ArrayList<Item> inventory;
    
    private InMemoryDb() {
		super();
		inventory = new ArrayList<Item>();  
	}
    
    public static InMemoryDb getInstance()
    {
        if (single_instance == null)
            single_instance = new InMemoryDb();
  
        return single_instance;
    }
    
}
