package com.java115.service;

import java.util.List;

import com.java115.dao.EmpDao;
import com.java115.entity.Customer;
import com.java115.entity.Employees;

public class EmpService {
	EmpDao ed = new EmpDao();
	
		
			public List<Employees> getAllemp(){
				return ed.selAllemp();
			}

			public Employees getEmployeesById(int id) {
				return ed.selEmpById(id);
			}

			public List<Employees> getAllEmployees() {
				return ed.selectAllEmployees();
			}
}
