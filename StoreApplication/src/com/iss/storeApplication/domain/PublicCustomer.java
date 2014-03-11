package com.iss.storeApplication.domain;

public class PublicCustomer extends Customer{

		// not null and unique
		private String memberId="PUBLIC";

		public String getMemberId() {
			return memberId;
		}

		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		
}
