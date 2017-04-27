package com.java115.entity;

import java.io.Serializable;

public class Ptype implements Serializable{
	private int ptypeid;
	private String ptypename;
	
	public Ptype(int ptypeid, String ptypename) {
		super();
		this.ptypeid = ptypeid;
		this.ptypename = ptypename;
	}

	public Ptype() {
		super();
	}

	public int getPtypeid() {
		return ptypeid;
	}

	public void setPtypeid(int ptypeid) {
		this.ptypeid = ptypeid;
	}

	public String getPtypename() {
		return ptypename;
	}

	public void setPtypename(String ptypename) {
		this.ptypename = ptypename;
	}

	@Override
	public String toString() {
		return "Ptype [ptypeid=" + ptypeid + ", ptypename=" + ptypename + "]";
	}
	
	
}
