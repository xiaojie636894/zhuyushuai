package com.java115.entity;

public class Customer {
	private String customer_name;
	private int customerID;
	
	public Customer() {
		super();
	}

	
	
	public Customer(int customerID) {
		super();
		this.customerID = customerID;
	}



	public Customer(int customerID, String customer_name) {
		super();
		this.customer_name = customer_name;
		this.customerID = customerID;
	}

	



	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	
	
	
	
}
