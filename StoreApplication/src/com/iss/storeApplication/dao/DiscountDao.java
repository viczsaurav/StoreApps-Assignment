package com.iss.storeApplication.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.PermanentDiscount;
import com.iss.storeApplication.domain.SeasonalDiscount;
import com.iss.storeApplication.enums.DiscountApplicable;

/**
 * 
 * @author milan
 *
 */
public class DiscountDao implements CommonDao<Discount> {

	private String fileName = Constants.FILENAME_DISCOUNT
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;
	
	/**
	 * save Discount Object
	 */
	@Override
	public boolean save(Discount discount, boolean append) {
		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, append)));

			String row = "";
			if (discount instanceof PermanentDiscount) {
				row = ((PermanentDiscount) discount).getCommaSeperatedValue();
			} else {
				row = ((SeasonalDiscount) discount).getCommaSeperatedValue();
			}
			out.println(row);
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}

	}
	
	/**
	 * Retrive all discounts from file
	 */
	@Override
	public List<Discount> retrieveAll() {
		List<Discount> discounts = new ArrayList<Discount>();

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String row;
			while ((row = br.readLine()) != null) {
				if (!StringUtility.isEmpty(row)) {

					String[] rowValues = row.split(",");

					if (rowValues[2].equals(Constants.ALWAYS))// Permanent
																// Discount
					{
						PermanentDiscount pd = new PermanentDiscount();
						pd.setDiscountCode(rowValues[0]);
						pd.setDescription(rowValues[1]);
						pd.setStartDate(rowValues[2]);
						pd.setDuration(rowValues[3]);
						pd.setDiscount(new Double(rowValues[4]));
						pd.setMemberApplicable(DiscountApplicable
								.fromString(rowValues[5]));

						discounts.add(pd);
					} else {
						SeasonalDiscount sd = new SeasonalDiscount();
						sd.setDiscountCode(rowValues[0]);
						sd.setDescription(rowValues[1]);
						sd.setStartDate(StringUtility
								.getDateFromString(rowValues[2]));
						sd.setDuration(new Integer(rowValues[3]));
						sd.setDiscount(new Double(rowValues[4]));
						sd.setMemberApplicable(DiscountApplicable
								.fromString(rowValues[5]));
						discounts.add(sd);
					}
				}

			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}

		return discounts;
	}
	
	/**
	 * Get Discount code, Discount Map 
	 */
	@Override
	public Map<String, Discount> getMap() {
		// TODO Auto-generated method stub
		List<Discount> discounts = retrieveAll();
		HashMap<String, Discount> map = new HashMap<String, Discount>();

		for (Discount d : discounts) {
			map.put(d.getDiscountCode(), d);
		}

		return map;
	}

	/*
	 * Clears file and saves list of discount object to file.
	 */
	public boolean saveAll(List<Discount> discounts) {
		//if (discounts.size() == 0) {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if(!Utility.clearFile(file))
				return false;

		//}
		for (Discount d : discounts) {
			if (!save(d, true)) {
				return false;
			}
		}
		return true;
	}
}
