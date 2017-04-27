package com.java115.entity;

import java.util.Date;

public class Order {
	private int id;
	private String orderID;
	private Date order_date;
	private  Customer customer;
	private  Employees emp;
	
	public Order() {
		super();
	}

	public Order(int id, String orderID, Date order_date, Customer customer,
			Employees emp) {
		super();
		this.id = id;
		this.orderID = orderID;
		this.order_date = order_date;
		this.customer = customer;
		this.emp = emp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employees getEmp() {
		return emp;
	}

	public void setEmp(Employees emp) {
		this.emp = emp;
	}

	

	

	
	
}	
