package com.java115.entity;

public class Employees {
	private int empID;
	private String emp_name;
	
	public Employees() {
		super();
	}

	
	public Employees(int empID) {
		super();
		this.empID = empID;
	}


	public Employees(int empID, String emp_name) {
		super();
		this.empID = empID;
		this.emp_name = emp_name;
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
	
}
