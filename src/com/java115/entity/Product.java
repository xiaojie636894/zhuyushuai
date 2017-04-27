package com.java115.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Product implements Serializable{
	private int pid;
	private String pname;
	private Ptype ptype;
	private double inprice;
	private double salprice;
	@JSONField(format = "yyyy-MM-dd")
	private Date date;
	private String image;
	
	
	public Product() {
		super();
	}


	public Product(int pid, String pname, Ptype ptype, double inprice,
			double salprice, Date date, String image) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.ptype = ptype;
		this.inprice = inprice;
		this.salprice = salprice;
		this.date = date;
		this.image = image;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public Ptype getPtype() {
		return ptype;
	}


	public void setPtype(Ptype ptype) {
		this.ptype = ptype;
	}


	public double getInprice() {
		return inprice;
	}


	public void setInprice(double inprice) {
		this.inprice = inprice;
	}


	public double getSalprice() {
		return salprice;
	}


	public void setSalprice(double salprice) {
		this.salprice = salprice;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}





	
	
	
}
