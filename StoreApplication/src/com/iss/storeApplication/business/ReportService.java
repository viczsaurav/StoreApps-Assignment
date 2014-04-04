package com.iss.storeApplication.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.component.DatePicker;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.dao.TransactionDao;
import com.iss.storeApplication.domain.Transaction;

public class ReportService {

	/**
	 * 
	 * @author mani
	 *
	 */
	
	private static TransactionDao transactionDao = new TransactionDao(); 
	
	public static boolean validateTimePeriod(DatePicker startDate,DatePicker endDate) {
		if (startDate.getDate().after(endDate.getDate())) {
			return false;
		} else {
			return true;
		}
	}
	

	public static List<Transaction> sortTransactions(DatePicker startDate,DatePicker endDate) {
		// TODO Auto-generated method stub
		
		List<Transaction> transactions = transactionDao.retrieveAll();
		List<Transaction> sortedTransactions = new ArrayList<Transaction>();
		
		for (Transaction t : transactions) {
			if (t.getDateOfPurchase().compareTo(startDate.getDate()) >= 0
					&& t.getDateOfPurchase().compareTo(endDate.getDate()) <= 0) {
				sortedTransactions.add(t);
				
			}
		}
		Collections.sort(sortedTransactions,new Comparator<Transaction>() {
			@Override
			public int compare(Transaction o1, Transaction o2) {
				// TODO Auto-generated method stub
				return o1.getProduct().getProductId().compareToIgnoreCase(o2.getProduct().getProductId());
			}
		});
		
		return sortedTransactions;
	}


	public static List<Transaction> getTodayTransactions() {
		// TODO Auto-generated method stub
		List<Transaction> transactions = transactionDao.retrieveAll();
		Date today = new Date();
		List<Transaction> todayTransactions = new ArrayList<Transaction>();
		for (Transaction t : transactions) {
			if (StringUtility.getStringFromDate(today).equals(StringUtility.getStringFromDate(t.getDateOfPurchase()))) {
				todayTransactions.add(t);			
			}
		}
		return todayTransactions;
	}
}
