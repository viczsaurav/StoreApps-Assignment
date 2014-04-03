package com.iss.storeApplication.dao;

import java.util.Collection;
import java.util.Map;

public interface CommonDao<T> {

	public boolean save(T t,boolean append);

	public Collection<T> retrieveAll();
	
	public  Map<String,T> getMap();

}
