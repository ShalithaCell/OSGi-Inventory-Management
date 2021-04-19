package com.june_we_04.osgi.inventoryservice.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.june_we_04.osgi.inventoryservice.interfaces.IItemService;
import com.june_we_04.osgi.inventoryservice.interfaces.ISystemWorker;
import com.june_we_04.osgi.inventoryservice.model.InMemoryDb;
import com.june_we_04.osgi.inventoryservice.model.Item;

public class ItemServiceImpl implements IItemService{
	
	private ISystemWorker worker; 
	
	public ItemServiceImpl() {
		worker =  new SystemWorker();
	}

	@Override
	public Item AddNewItem(Item item) {
		
		// access the static database
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		item.setItemID(worker.GenerateUniqueItemID());
		
		dataCollection.AddNewItem(item);
		
		return item;
	}

	@Override
	public List<Item> GetInventory(int storeID) {
		
		// access the static database
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		return dataCollection.GetInventory().stream()
	    .filter(p -> p.getStoreID() == storeID).collect(Collectors.toList());
	}

	@Override
	public boolean UpdateItem(Item item) {

		Optional<Item> oldItem = GetItem(item.getItemID());
		
		if(!oldItem.isPresent()){
			return false;
		}
		
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		int index = dataCollection.GetInventory().indexOf(oldItem.get());
		
		dataCollection.GetInventory().get(index).setBuyPrice(item.getBuyPrice());
		dataCollection.GetInventory().get(index).setCategory(item.getCategory());
		dataCollection.GetInventory().get(index).setName(item.getName());
		dataCollection.GetInventory().get(index).setQty(item.getQty());
		dataCollection.GetInventory().get(index).setSellPrice(item.getSellPrice());
		
		return true;
		
	}

	@Override
	public boolean RemoveItem(int id) {
		Optional<Item> oldItem = GetItem(id);
		
		if(!oldItem.isPresent())
			return false;
		
		Item existsOne = oldItem.get();
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		int index = dataCollection.GetInventory().indexOf(existsOne);
		
		dataCollection.GetInventory().remove(index);
		
		return true;
	}

	@Override
	public Optional<Item> GetItem(int itemID) {
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		Optional<Item> item = dataCollection.GetInventory().stream()
				.filter(c -> c.getItemID() == itemID)
				.findAny();
		
		return item;
	}

}
