package com.java115.entity;

import java.util.List;

public class DataTables {
	private List data;
	private int recordsTotal;
	private int recordsFiltered;
	
	
	public List getData() {
		return data;
	}


	public void setData(List data) {
		this.data = data;
	}


	public int getRecordsTotal() {
		return recordsTotal;
	}


	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}


	public int getRecordsFiltered() {
		return recordsFiltered;
	}


	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}


	public DataTables(List data, int recordsTotal, int recordsFiltered) {
		super();
		this.data = data;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
	}


	public DataTables() {
		super();
	}
	
	
}
