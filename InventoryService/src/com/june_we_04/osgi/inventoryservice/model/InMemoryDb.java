package com.june_we_04.osgi.inventoryservice.model;

import java.util.ArrayList;
import java.util.Iterator;

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
    
    public ArrayList<Item> GetInventory(){
    	return  inventory;
    }
    
    public void ClearData() {
    	inventory.clear();
    }
    
    public void AddNewItem(Item item) {
    	inventory.add(item);
    }
    
    public void RemoveStore(int itemID) {
		Iterator<Item> itr = inventory.iterator();
        while (itr.hasNext()) {
        	Item obj = itr.next();
            if (obj.getItemID() == itemID) {
                itr.remove();
            }
        }
	}
    
}
