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
}
