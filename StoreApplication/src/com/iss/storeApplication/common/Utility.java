package com.iss.storeApplication.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Properties;

/**
 * 
 * @author milan
 * 
 */
public class Utility {

	private static Properties prop = new Properties();

	static {
		InputStream input;
		try {
			File file = new File(Constants.PROPERTY_FILE_DIR,
					Constants.PROPERTY_FILE_NAME);
			if (!file.exists()) {
				file.createNewFile();
			}
			input = new FileInputStream(file);
			prop.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getPropertyValue(String key) {
		return prop.get(key).toString();
	}

	public static NumberFormat getPercentageNumberFormat() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumIntegerDigits(2);
		nf.setMinimumIntegerDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf;
	}

	public static NumberFormat getDurationNumberFormat() {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumIntegerDigits(4);
		nf.setMinimumIntegerDigits(1);
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		return nf;
	}
	
	public static NumberFormat getProductBarcodeFormat() {
		NumberFormat pbf = NumberFormat.getNumberInstance();
		pbf.setMaximumIntegerDigits(4);
		pbf.setMinimumIntegerDigits(1);
		pbf.setMaximumFractionDigits(0);
		pbf.setMinimumFractionDigits(0);
		return pbf;
	}
	
	public static NumberFormat getProductPriceFormat() {
		NumberFormat ppf = NumberFormat.getNumberInstance();
		ppf.setMaximumIntegerDigits(6);
		ppf.setMinimumIntegerDigits(1);
		ppf.setMaximumFractionDigits(2);
		ppf.setMinimumFractionDigits(2);
		return ppf;
	}
	
	public static NumberFormat getProductNumberFormat() {
		NumberFormat pnf = NumberFormat.getNumberInstance();
		pnf.setMaximumIntegerDigits(4);
		pnf.setMinimumIntegerDigits(1);
		pnf.setMaximumFractionDigits(0);
		pnf.setMinimumFractionDigits(0);
		return pnf;
	}

	public static boolean clearFile(File file) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, false)));
			out.println();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (out != null)
				out.close();
		}

	}

}
