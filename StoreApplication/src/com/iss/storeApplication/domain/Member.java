package com.iss.storeApplication.domain;

public class Member {
 private String memberName;
 private String memberId;
 private Integer loyaltyPoints;
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
public Integer getLoyaltyPoints() {
	return loyaltyPoints;
}
public void setLoyaltyPoints(Integer loyaltyPoints) {
	this.loyaltyPoints = loyaltyPoints;
}
 
 
public String getCommaSeperatedValue() {
	// TODO Auto-generated method stub
	return memberId+","+memberName+","+loyaltyPoints;
}

 
 
}
