package com.iss.storeApplication.dao;

import java.util.List;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Field;

import com.sun.org.apache.bcel.internal.generic.Type;

import sun.org.mozilla.javascript.internal.json.JsonParser;
import sun.reflect.FieldInfo;

public class CommonDao {

	public static <T, Z> boolean save(T Dao, Z filePath) {
		String fpath = filePath.toString();
			try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fpath, true)))) {
			    out.println(CommonDao.toCsv(Dao));
			    out.close();
			}
			catch (IOException e) {
				System.err.println("File writing error while writing : "
						+ Dao.getClass() + " to path : " + fpath);
				return false;
			}			
			return true;
	}

	private static String toCsv(Object o) throws IOException {
		StringBuffer sb = new StringBuffer();
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				String FieldKey = fields[i].getName();
				Object Fieldvalue;
				Fieldvalue = fields[i].get(o);
				sb.append(Fieldvalue).append(',');
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}

}
