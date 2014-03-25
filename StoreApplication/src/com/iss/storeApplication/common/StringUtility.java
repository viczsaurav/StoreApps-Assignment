package com.iss.storeApplication.common;

public class StringUtility {

	public static boolean isEmpty(String s) {
		if (s == null)
			return true;
		return s.equals("");
	}
}
