package com.iss.storeApplication.domain;

/**
 * 
 * @author Milan
 *
 */

public class StoreKeeper {

	private String userName;
	private String password;

	public StoreKeeper() {
		
	}
	
	public StoreKeeper(String userName, String password) {

		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}

		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;

		StoreKeeper storekeeper = (StoreKeeper) obj;
		return storekeeper.getUserName().equals(this.getUserName()) && storekeeper.getPassword().equals(this.getPassword());
		
	
	}


	public String getCommaSeperatedValue() {
		return userName + "," + password;
	}
}
