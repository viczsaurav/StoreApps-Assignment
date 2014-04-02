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
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.MemberCustomer;

public class CustomerDao implements CommonDao<Customer> {

	private String fileName = Constants.FILENAME_MEMBER
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	/**
	 * Save member to file
	 */
	@Override
	public boolean save(Customer m, boolean append) {
		// TODO Auto-generated method stub

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));
			out.println(((MemberCustomer)m).getCommaSeperatedValue());
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}

	}

	/**
	 * List of all members
	 */

	@Override
	public List<Customer> retrieveAll() {
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<Customer>();

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
					MemberCustomer m = new MemberCustomer();
					m.setMemberName(rowValues[0]);
					m.setMemberId(rowValues[1]);
					m.setLoyality(Integer.parseInt(rowValues[2]));
					customers.add(m);
					
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}
		return customers;
	}

	@Override
	/**
	 * Used to return a Map of members with memberID  as key and member object as value
	 */
	public Map<String, Customer> getMap() {
		// TODO Auto-generated method stub

		List<Customer> members = retrieveAll();
		HashMap<String, Customer> membersMap = new HashMap<String, Customer>();

		for (Customer m : members) {
			membersMap.put(((MemberCustomer)m).getMemberId(), m);
		}

		return membersMap;
	}

	public Customer getCustomer(String memberId) {
		Map<String, Customer> membersMap = getMap();
		if (membersMap.containsKey(memberId)) {
			return membersMap.get(memberId);
		} else {
			return null;
		}
	}

	/*
	 * Clears file and saves list of discount object to file.
	 */
	public boolean saveAll(List<Customer> members) {
		// if (discounts.size() == 0) {
		File file = new File(Constants.DATA_FILE_DIR, fileName);
		if (!Utility.clearFile(file))
			return false;

		// }
		for (Customer d : members) {
			if (!save(d, true)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean edit(Customer member)
	{
		Map<String,Customer> map=getMap();
		if(map.containsKey(((MemberCustomer)member).getMemberId()))
		{
			map.put(((MemberCustomer)member).getMemberId(), member);
			saveAll(new ArrayList<Customer>(map.values()));
			return true;
		}
		else
		{
			return false;
		}
		
	}
}
