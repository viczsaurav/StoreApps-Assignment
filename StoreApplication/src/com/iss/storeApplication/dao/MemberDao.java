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
import com.iss.storeApplication.domain.Member;

public class MemberDao implements CommonDao<Member> {

	private String fileName = Constants.FILENAME_MEMBER
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	/**
	 * Save member to file
	 */
	@Override
	public boolean save(Member m, boolean append) {
		// TODO Auto-generated method stub

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));
			out.println(m.getCommaSeperatedValue());
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
	public List<Member> retrieveAll() {
		// TODO Auto-generated method stub
		List<Member> Members = new ArrayList<Member>();

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
					Member m = new Member();
					m.setMemberName(rowValues[0]);
					m.setMemberId(rowValues[1]);
					m.setLoyaltyPoints(Integer.parseInt(rowValues[2]));
					Members.add(m);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}
		return Members;
	}

	@Override
	/**
	 * Used to return a Map of members with memberID  as key and member object as value
	 */
	public Map<String, Member> getMap() {
		// TODO Auto-generated method stub

		List<Member> members = retrieveAll();
		HashMap<String, Member> membersMap = new HashMap<String, Member>();

		for (Member m : members) {
			membersMap.put(m.getMemberId(), m);
		}

		return membersMap;
	}

	public Member getMember(String memberId) {
		Map<String, Member> membersMap = getMap();
		if (membersMap.containsKey(memberId)) {
			return membersMap.get(memberId);
		} else {
			return null;
		}
	}

	/*
	 * Clears file and saves list of discount object to file.
	 */
	public boolean saveAll(List<Member> members) {
		// if (discounts.size() == 0) {
		File file = new File(Constants.DATA_FILE_DIR, fileName);
		if (!Utility.clearFile(file))
			return false;

		// }
		for (Member d : members) {
			if (!save(d, true)) {
				return false;
			}
		}
		return true;
	}
}
