package com.iss.storeApplication.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */

public class LoginServiceTest {
	private List<StoreKeeper> storeKeepers = new ArrayList<StoreKeeper>();

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		storeKeepers.add(new StoreKeeper("", ""));
		storeKeepers.add(new StoreKeeper("wrongUserName", ""));
		storeKeepers.add(new StoreKeeper("", "wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName", "wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("Three", "wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName", "Three"));
		// storeKeepers.add(new
		// StoreKeeper("correctUserName","correctPasswrod"));
		storeKeepers.add(new StoreKeeper("Three", "Three"));

		assertEquals(LoginService.validateUser(storeKeepers.get(0)),
				Constansts.MSG_USR_PWD_NULL);
		assertEquals(LoginService.validateUser(storeKeepers.get(1)),
				Constansts.MSG_PWD_NULL);
		assertEquals(LoginService.validateUser(storeKeepers.get(2)),
				Constansts.MSG_USR_NULL);
		assertEquals(LoginService.validateUser(storeKeepers.get(3)),
				Constansts.MSG_INVALID_USR);
		assertEquals(LoginService.validateUser(storeKeepers.get(4)),
				Constansts.LOGIN_INVALID_PASSWORD);
		assertEquals(LoginService.validateUser(storeKeepers.get(5)),
				Constansts.LOGIN_CORRECT_USERNAME);
		assertEquals(LoginService.validateUser(storeKeepers.get(6)),
				Constansts.LOGIN_SUCCESS_MESSAGE);

	}

}
