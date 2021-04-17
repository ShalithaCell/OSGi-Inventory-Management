package com.june_we_04.osgi.storeservice.application;

import java.util.Optional;

import com.june_we_04.osgi.storeservice.interfaces.IStoreService;
import com.june_we_04.osgi.storeservice.interfaces.ISystemWorker;
import com.june_we_04.osgi.storeservice.model.InMemoryDb;
import com.june_we_04.osgi.storeservice.model.Store;

public class StoreServiceImpl implements IStoreService{
	
	private ISystemWorker worker; 
	
	public StoreServiceImpl() {
		worker = new SystemWorkerImpl();
	}

	@Override
	public Store RegisterNewStore(Store store) {
		// access the static database
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		store.setStoreID(worker.GenerateUniqueStoreID());
		
		dataCollection.AddStore(store);
		
		return store;
	}

	@Override
	public Optional<Store> GetStoreInformation(int id) {
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		Optional<Store> store = dataCollection.GetStores().stream()
				.filter(c -> c.getStoreID() == id)
				.findAny();
		
		return store;
	}

	@Override
	public void UpdateStoreInformation(Store store) {
		
		Optional<Store> oldStore = GetStoreInformation(store.getStoreID());
		
		if(!oldStore.isPresent())
			return;
		
		Store existsOne = oldStore.get();
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		int index = dataCollection.GetStores().indexOf(existsOne);
		
		 dataCollection.GetStores().get(index).setName(store.getName());
		
	}

	@Override
	public void RemoveStore(int id) {
		
		Optional<Store> oldStore = GetStoreInformation(id);
		
		if(!oldStore.isPresent())
			return;
		
		Store existsOne = oldStore.get();
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		int index = dataCollection.GetStores().indexOf(existsOne);
		
		dataCollection.GetStores().remove(index);
	}

}
