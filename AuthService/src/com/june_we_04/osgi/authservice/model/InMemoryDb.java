package com.june_we_04.osgi.authservice.model;

import java.util.ArrayList;
import java.util.Iterator;

public class InMemoryDb {
	
	// static variable single_instance of type Singleton
    private static InMemoryDb single_instance = null;
	
	private ArrayList<User> users;
	
	
	private InMemoryDb() {
		super();
		users = new ArrayList<User>();  
	}
	
	public static InMemoryDb getInstance()
    {
        if (single_instance == null)
            single_instance = new InMemoryDb();
  
        return single_instance;
    }
	
	public ArrayList<User> GetUsers(){
		return users;
	}


	public void ClearData() {
		users.clear();
	}
	
	public void AddUser(User user) {
		users.add(user);
	}
	
	public void RemoveUser(int userId) {
		Iterator<User> itr = users.iterator();
        while (itr.hasNext()) {
        	User obj = itr.next();
            if (obj.getUserID() == userId) {
                itr.remove();
            }
        }
	}

}
