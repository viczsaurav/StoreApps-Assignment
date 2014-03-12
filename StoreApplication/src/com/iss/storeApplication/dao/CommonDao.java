package com.iss.storeApplication.dao;

import java.util.List;

public interface CommonDao<T> {

	public void save(T t);
	
	public List<T> retrieveAll();
}
