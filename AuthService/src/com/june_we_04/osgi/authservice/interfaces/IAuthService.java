package com.june_we_04.osgi.authservice.interfaces;

import java.util.Optional;

import com.june_we_04.osgi.authservice.model.User;

public interface IAuthService {
	
	/**
	 * Register new user
	 * @param user
	 * @return
	 */
	public User RegisterUser(User user);
	
	/**
	 * Check user is authenticated
	 * @param userID
	 * @param password
	 * @return
	 */
	public boolean Authenticate(int userID, String password);
	
	/**
	 * Get user via user name and password
	 * @param userID
	 * @param password
	 * @return
	 */
	public Optional<User> GetUser(int userID, String password);
	
	/**
	 * Set new password
	 * @param userID
	 * @param newPassword
	 */
	public void ResetPassword(int userID, String newPassword);
	
	/**
	 * Change username
	 * @param userID
	 * @param userName
	 */
	public void ChangeUserName(int userID, String userName);
	
	/**
	 * Remove particular user
	 * @param userID
	 */
	public void RemoveUser(int userID);
}
