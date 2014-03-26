package com.iss.storeApplication.dao;

import java.util.List;
import java.util.Map;

import com.iss.storeApplication.domain.StoreKeeper;

public interface CommonDao<T> {

	public boolean save(T t);

	public List<T> retrieveAll();
	
	public  Map<String,T> getMap();

}
