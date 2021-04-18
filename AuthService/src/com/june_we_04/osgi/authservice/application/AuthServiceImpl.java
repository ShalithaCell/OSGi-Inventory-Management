package com.june_we_04.osgi.authservice.application;

import java.util.Optional;

import com.june_we_04.osgi.authservice.interfaces.IAuthService;
import com.june_we_04.osgi.authservice.interfaces.ISystemWorker;
import com.june_we_04.osgi.authservice.model.InMemoryDb;
import com.june_we_04.osgi.authservice.model.User;

public class AuthServiceImpl implements IAuthService{
	
	private ISystemWorker worker;
	
	public AuthServiceImpl() {
		worker = new SystemWorkerImpl();
	}

	@Override
	public User RegisterUser(User user) {
		
		// access the static database
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		// add user ID
		user.setUserID(worker.GenerateUniqueUserID());
		
		// add new user
		dataCollection.AddUser(user);
		
		return user;
	}

	@Override
	public boolean Authenticate(int userID, String password) {
		
		Optional<User> user = GetUser(userID, password);
		
		if(user.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Optional<User> GetUser(int userID, String password) {
		
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		Optional<User> user = dataCollection.GetUsers().stream()
				.filter(c -> c.getUserID() == userID && c.getPassword().equals(password))
				.findAny();
		
		return user;
	}

	@Override
	public void ResetPassword(int userID, String newPassword) {
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		Optional<User> user = dataCollection.GetUsers().stream()
				.filter(c -> c.getUserID() == userID)
				.findAny();
		
		
		if(!user.isPresent())
			return;
		
		User userObj = user.get();
		
		int index = dataCollection.GetUsers().indexOf(userObj);
		
		dataCollection.GetUsers().get(index).setPassword(newPassword);
	}

	@Override
	public void ChangeUserName(int userID, String userName) {
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		
		Optional<User> user = dataCollection.GetUsers().stream()
				.filter(c -> c.getUserID() == userID)
				.findAny();
		
		
		if(!user.isPresent())
			return;
		
		User userObj = user.get();
		
		int index = dataCollection.GetUsers().indexOf(userObj);
		
		dataCollection.GetUsers().get(index).setName(userName);
	}

	@Override
	public void RemoveUser(int userID) {
		InMemoryDb dataCollection = InMemoryDb.getInstance();
		dataCollection.RemoveUser(userID);
	}

}
