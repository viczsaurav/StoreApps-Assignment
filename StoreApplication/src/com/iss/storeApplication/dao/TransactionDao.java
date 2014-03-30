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
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.PublicCustomer;
import com.iss.storeApplication.domain.Transaction;

/**
 * 
 * @author milan
 *
 */
public class TransactionDao implements CommonDao<Transaction> {
	private String fileName = Constants.FILENAME_TRANSACTION
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	private static CustomerDao memberDao = new CustomerDao();
	private static ProductDao productDao = new ProductDao();

	/**
	 * save Transaction Object
	 */
	@Override
	public boolean save(Transaction transction, boolean append) {
		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, append)));

			String row = "";

			row = transction.getCommaSeperatedValue();

			out.println(row);
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}

	}

	@Override
	public List<Transaction> retrieveAll() {
		List<Transaction> transactions = new ArrayList<Transaction>();

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

					Transaction t = new Transaction();
					t.setTransactionId(rowValues[0]);
					t.setProduct(productDao.get(rowValues[1]));
					if (rowValues[2].equalsIgnoreCase(Constants.PUBLIC)) {
						t.setCustomer(new PublicCustomer());
					} else {
						t.setCustomer((Customer) memberDao
								.getCustomer(rowValues[2]));
					}

					t.setQtyPurchase(new Integer(rowValues[3]));
					t.setDateOfPurchase(StringUtility
							.getDateFromString(rowValues[4]));
					transactions.add(t);

				}
			}

			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}

		return transactions;
	}

	@Override
	public Map<String, Transaction> getMap() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
