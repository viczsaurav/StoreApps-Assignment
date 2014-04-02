package com.iss.storeApplication.business;

import java.util.List;

import com.iss.storeApplication.dao.TransactionDao;
import com.iss.storeApplication.domain.Transaction;

/**
 * 
 * @author milan
 *
 */
public class TransactionService {

	private static TransactionDao transactionDao = new TransactionDao();

	/**
	 * Gets max transaction id from file
	 * @return
	 */
	public static Integer getMaxTransactionId() {
		return transactionDao.getMaxTransactionId();
	}

	/**
	 * Insert transaction to file
	 * @param transaction
	 * @return
	 */
	public static boolean save(Transaction transaction) {
		return transactionDao.save(transaction, true);
	}

	/**
	 * get List of transation from file
	 * @return
	 */
	public static List<Transaction> getTransactions() {

		return transactionDao.retrieveAll();
	}

	

}
