package com.iss.storeApplication.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iss.storeApplication.domain.StoreKeeper;

public class LoginTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		storeKeepers.add(new StoreKeeper("",""));
		storeKeepers.add(new StoreKeeper("wrongUserName",""));
		storeKeepers.add(new StoreKeeper("","wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName","wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("correctUserName","wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("correctUserName","correctPasswrod"));
	}

	@After
	public void tearDown() throws Exception {
		storeKeepers.clear();
	}
	
	private List<StoreKeeper> storeKeepers=new ArrayList<StoreKeeper>();

	@Test
	public void test() {
		
		
		
	}

}
