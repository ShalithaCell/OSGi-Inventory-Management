package com.june_we_04.osgi.inventoryconsumer.interfaces;

public interface IUserManagementService {

	/**
	 * Create a new user account
	 */
	public void RegisterNewUserAccount();
	
	/**
	 * Login to the system
	 * @return
	 */
	public boolean Login();
	
	/**
	 * Reset the password
	 */
	public void ResetPassword();
	
	/**
	 * Change user's username
	 * @param username
	 */
	public void ChangeUserName();
	
	public void RemoveUser();
}
