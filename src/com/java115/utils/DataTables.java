package com.java115.utils;

import java.util.List;

public class DataTables {
	private int recordsTotal;
	private int recordsFiltered;
	private List data;

	public DataTables() {
		super();
	}

	public DataTables(int recordsTotal, int recordsFiltered, List data) {
		super();
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
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

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

}
