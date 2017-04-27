package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java115.entity.Customer;
import com.java115.entity.Product;
import com.java115.entity.Ptype;

public class CustomerDao extends BaseDao{
	//-------����-------
		public int insert(Customer cus){
			int result = 0;
			String sql = "insert into product(customer_name) value(?)";
			result = super.exeUpdate(sql,cus.getCustomer_name());
			
			return result;
		}
		
		public Customer selCusById(int id){
			Customer cus = new Customer();
			String sql = "select * from customer where customerID=?";
			
			
			ResultSet rs = super.exeQuery(sql,id);
			try {
				if(rs.next()){
					cus.setCustomer_name(rs.getString("customer_name"));
					cus.setCustomerID(rs.getInt("customerID"));
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			finally{
				super.CloseAll();
			}
			return cus;
		}
		
		
		public List<Customer> selAllcust(){
			List<Customer> custs = new ArrayList<Customer>();
			String sql = "select * from customer order by customerID desc";

			ResultSet rs = super.exeQuery(sql);
			try {
				while(rs.next()){
					Customer cust = new Customer();
					cust.setCustomerID(rs.getInt("CustomerID"));
					cust.setCustomer_name(rs.getString("customer_name"));
					
					custs.add(cust);
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			finally{
				super.CloseAll();
			}
			return custs;
		}

		public List<Customer> selectAllCustomer() {
			String sql = "select * from customer order by customerID asc";
			ResultSet set = super.exeQuery(sql, null);
			List<Customer> list = new ArrayList<Customer>();
			try {
				while (set.next()) {
					int customerID = set.getInt("customerID");
					String customer_name = set.getString("customer_name");

					list.add(new Customer(customerID, customer_name));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				super.CloseAll();
			}
			return list;
		}
}
