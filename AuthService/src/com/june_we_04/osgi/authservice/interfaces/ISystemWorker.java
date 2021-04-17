package com.june_we_04.osgi.authservice.interfaces;

import com.june_we_04.osgi.authservice.model.ApplicationDelegate;

public interface ISystemWorker {

	/**
	 * Generate unique user ID
	 * @return
	 */
	public int GenerateUniqueUserID();
}
