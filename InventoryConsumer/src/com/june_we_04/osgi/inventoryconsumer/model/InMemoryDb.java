package com.june_we_04.osgi.inventoryconsumer.model;

import java.util.ArrayList;

import com.june_we_04.osgi.authservice.model.User;

public class InMemoryDb {
	
	// static variable single_instance of type Singleton
    private static InMemoryDb single_instance = null;
    
    private int actionID;
    
    private User user;
    
    private InMemoryDb() {
		super();
		actionID = 0;  
	}
    
    public static InMemoryDb getInstance()
    {
        if (single_instance == null)
            single_instance = new InMemoryDb();
  
        return single_instance;
    }
    
    public void SetActionID(int id) {
    	actionID = id;
    }
    
    public int GetActionID(){
    	return actionID;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
