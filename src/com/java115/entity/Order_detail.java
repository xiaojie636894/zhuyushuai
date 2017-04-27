package com.java115.entity;

public class Order_detail {
	private int id;
	private String orderID;
	private Product product;
	private int quantity;
	
	public Order_detail() {
		super();
	}
	
	

	public Order_detail(String orderID, Product product, int quantity) {
		super();
		this.orderID = orderID;
		this.product = product;
		this.quantity = quantity;
	}



	public Order_detail(int id, String orderID, Product product, int quantity) {
		super();
		this.id = id;
		this.orderID = orderID;
		this.product = product;
		this.quantity = quantity;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	
	

	
	
	
	
}
