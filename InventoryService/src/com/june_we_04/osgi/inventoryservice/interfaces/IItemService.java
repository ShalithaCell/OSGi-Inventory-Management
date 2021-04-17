package com.june_we_04.osgi.inventoryservice.interfaces;

import java.util.List;
import java.util.Optional;

import com.june_we_04.osgi.inventoryservice.model.Item;

public interface IItemService {
	
	/**
	 * Add new item
	 * @param item
	 * @return
	 */
	public Item AddNewItem(Item item);
	
	/**
	 * Get all items
	 * @param storeID
	 * @return
	 */
	public List<Item> GetInventory(int storeID);
	
	/**
	 * Get specific Item
	 * @param itemID
	 * @return
	 */
	public Optional<Item> GetItem(int itemID);
	
	/**
	 * Update specific item
	 * @param item
	 * @return
	 */
	public boolean UpdateItem(Item item);
	
	/**
	 * Remove item form inventory
	 * @param id
	 * @return
	 */
	public boolean RemoveItem(int id);

}
