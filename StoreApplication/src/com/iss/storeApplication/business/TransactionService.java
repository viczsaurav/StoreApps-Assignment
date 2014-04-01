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
	
	private static TransactionDao transactionDao=new TransactionDao();

	public static Integer getMaxTransactionId()
	{
		return transactionDao.getMaxTransactionId();
	}
	
	public static boolean save(Transaction transaction)
	{
		return transactionDao.save(transaction, true);
	}

	public static List<Transaction> getTransactions() {
		// TODO Auto-generated method stub
		return transactionDao.retrieveAll();
	}
}
