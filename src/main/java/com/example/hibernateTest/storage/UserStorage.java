package com.example.hibernateTest.storage;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {
	private static UserStorage instance;
	private Set<String>users;
	
	private UserStorage() {
		users=new HashSet<String>();
	}
	public static synchronized UserStorage getInstance() {
		if(instance==null) {
			instance=new UserStorage();
		}
		return instance;
	}
	
	public Set<String>getUsers(){
		return users;
	}
	
	public void clearUser() {
		users.clear();
	}
	
	public void setUser(String name) throws Exception {
		if(users.contains(name)) {
			throw new Exception("user already exists with username "+name);
		}
		users.add(name);
	}
}
