package com.iss.storeApplication.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.PermanentDiscount;
import com.iss.storeApplication.domain.SeasonalDiscount;

/**
 * 
 * @author milan
 *
 */
public class DiscountDao implements CommonDao<Discount>{

	private String fileName = Constants.FILENAME_DISCOUNT
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	
	@Override
	public boolean save(Discount discount) {
		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));
			
			String row="";
			if(discount instanceof PermanentDiscount)
			{
				row=((PermanentDiscount)discount).getCommaSeperatedValue();
			}
			else
			{
				row=((SeasonalDiscount)discount).getCommaSeperatedValue();
			}
			out.println(row);
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}
		
	}

	@Override
	public List<Discount> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Discount> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
