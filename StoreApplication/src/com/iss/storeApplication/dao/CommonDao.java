package com.iss.storeApplication.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao<T> {

	public boolean save(T t,boolean append);

	public List<T> retrieveAll();
	
	public  Map<String,T> getMap();

}
