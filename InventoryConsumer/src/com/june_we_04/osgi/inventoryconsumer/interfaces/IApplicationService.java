package com.june_we_04.osgi.inventoryconsumer.interfaces;

public interface IApplicationService {
	
	/**
	 * Check that user is logged in or not
	 * @return
	 */
	public boolean IsAuthenticated();
	
	/**
	 * Handle register store
	 */
	public void StoreRegistration();
	
	/**
	 * Show application main menu
	 */
	public void ShowMenu();
	
	/**
	 * USer management function
	 */
	public void UserManagement();
	
	/**
	 * Store Management function
	 */
	public void StoreManagement();
	
	/**
	 * Inventory Management function
	 */
	public void InventoryManagement();
	
	/**
	 * Financial information function
	 */
	public void FinantialManagement();
	
	
}
