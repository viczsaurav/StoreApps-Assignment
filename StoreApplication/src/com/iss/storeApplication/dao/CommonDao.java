package com.iss.storeApplication.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.domain.*;

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
		//remove last delimintor
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	
	public static <T> List<T> retrieveAllRecordArray(String fileName,Class t) throws InstantiationException, IllegalAccessException
	{
		//Create Instances of all objects		
		List<String []> objStrList = new ArrayList<String[]>();
		String[] elements = null;		
		File file=new File(Constansts.DATA_FILE_DIR,fileName);	
		if(file.exists())
		{		
		Class c1= t;
		Object classobj = t;
		Field[] fields = t.getDeclaredFields();	
		List<T> objList = new ArrayList<T>();	
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
		    for(String line; (line = br.readLine()) != null; ) {
		    	classobj = c1.newInstance();
		        elements = line.split(",");
		        for(int i=0;i<elements.length;i++)
				{
				fields[i].setAccessible(true);
				fields[i].set(classobj,elements[i]);
				System.out.println(fields[i].get(classobj));
				}
		        objList.add((T)classobj);
		    }		
		return objList;		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
		}		
		return null;
}
	
	public static void main(String[] args)
	{
		CommonDao d1=new CommonDao();
		String[] strc1= null;
		List<Category> cat1=new ArrayList<Category>();
		try {
			cat1 = d1.retrieveAllRecordArray("Category.dat",Category.class);
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
