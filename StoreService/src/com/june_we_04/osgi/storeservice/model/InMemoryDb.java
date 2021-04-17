package com.june_we_04.osgi.storeservice.model;

import java.util.ArrayList;
import java.util.Iterator;

public class InMemoryDb {
	
	// static variable single_instance of type Singleton
    private static InMemoryDb single_instance = null;
	
	private ArrayList<Store> stores;
	
	private InMemoryDb() {
		super();
		stores = new ArrayList<Store>();  
	}
	
	public static InMemoryDb getInstance()
    {
        if (single_instance == null)
            single_instance = new InMemoryDb();
  
        return single_instance;
    }
	
	public ArrayList<Store> GetStores(){
		return stores;
	}
	
	public void ClearData() {
		stores.clear();
	}
	
	public void AddStore(Store store) {
		stores.add(store);
	}
	
	public void RemoveStore(int storeID) {
		Iterator<Store> itr = stores.iterator();
        while (itr.hasNext()) {
        	Store obj = itr.next();
            if (obj.getStoreID() == storeID) {
                itr.remove();
            }
        }
	}

}
