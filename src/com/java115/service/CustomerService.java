package com.java115.service;

import java.util.List;

import com.java115.dao.CustomerDao;
import com.java115.entity.Customer;
import com.java115.entity.Product;

public class CustomerService {
	CustomerDao cd = new CustomerDao();
	
	
		public int addCus(Customer cus){
			return cd.insert(cus);
		}
		
			
		public List<Customer> getAllcust(){
			return cd.selAllcust();
		}


		public Customer getCustomerById(int id) {
			return cd.selCusById(id);
		}


		public List<Customer> getAllCustomer() {
			return cd.selectAllCustomer();

		}

		
}
