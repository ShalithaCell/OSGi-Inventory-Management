package com.june_we_04.osgi.storeservice.interfaces;

import java.util.Optional;

import com.june_we_04.osgi.storeservice.model.Store;

public interface IStoreService {
	
	/**
	 * Add new store
	 * @param store
	 * @return
	 */
	public Store RegisterNewStore(Store store);
	
	/**
	 * Get Store
	 * @param id
	 * @return
	 */
	public Optional<Store> GetStoreInformation(int id);
	
	/**
	 * Update Store
	 * @param store
	 */
	public void UpdateStoreInformation(Store store);
	
	/**
	 * Remove Store
	 * @param id
	 */
	public void RemoveStore(int id);

}
