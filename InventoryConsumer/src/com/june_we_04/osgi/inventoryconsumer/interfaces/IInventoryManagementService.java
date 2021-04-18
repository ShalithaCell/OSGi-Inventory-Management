package com.june_we_04.osgi.inventoryconsumer.interfaces;

public interface IInventoryManagementService {
	
	/**
	 * Add new item
	 * @return
	 */
	public boolean AddNewItem();
	
	/**
	 * Update item details
	 * @return
	 */
	public boolean UpdateItem();
	
	/**
	 * Remove Item from the inventory
	 * @return
	 */
	public boolean RemoveItem();
	
	/**
	 * List down all items in the inventory
	 */
	public void DisplayInventory();
	
	/**
	 * Get item details
	 */
	public void GetItemDetails();

}
