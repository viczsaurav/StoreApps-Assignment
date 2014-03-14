package com.iss.storeApplication.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import com.iss.storeApplication.common.Constansts;

public  class CommonDao {

	public static <T> boolean save(T Dao, String fileName) {
		
			try {
				File file=new File(Constansts.DATA_FILE_DIR,fileName);
				if(!file.exists())
				{
					file.createNewFile();
				}
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
			    out.println(CommonDao.objectToCsv(Dao));
			    out.close();
			}
			catch (IOException e) {
				System.err.println("File writing error while writing : "
						+ Dao.getClass() + " to path : " + fileName);
				return false;
			}			
			return true;
	}

	private static String objectToCsv(Object o) throws IOException {
		StringBuffer sb = new StringBuffer();
		Field[] fields = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				fields[i].setAccessible(true);
				Object fieldValue = fields[i].get(o);
				sb.append(fieldValue).append(Constansts.COMMA_DELIMITER);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		//remove last deliminator
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}

}
