package com.june_we_04.osgi.inventoryconsumer.interfaces;

public interface IStoreManagementService {
	
	/**
	 * Update store information
	 */
	public boolean UpdateStore();
	
	/**
	 * Show all store information
	 */
	public void ShowStoreInformation();
	
	/**
	 * Remove store from the db
	 */
	public void RemoveStore();

}
