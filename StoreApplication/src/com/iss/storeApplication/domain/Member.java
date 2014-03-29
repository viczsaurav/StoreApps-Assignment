package com.iss.storeApplication.domain;

public class Member {
 private String memberName;
 private String memberId;
 private int loyaltyPoints;
public String getMemberName() {
	return memberName;
}
public void setMemberName(String memberName) {
	this.memberName = memberName;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public int getLoyaltyPoints() {
	return loyaltyPoints;
}
public void setLoyaltyPoints(int loyaltyPoints) {
	this.loyaltyPoints = loyaltyPoints;
}
 
 
public String getCommaSeperatedValue() {
	// TODO Auto-generated method stub
	return memberId+","+memberName+","+loyaltyPoints;
}

 
 
}
