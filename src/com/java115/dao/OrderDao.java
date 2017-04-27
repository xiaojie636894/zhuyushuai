package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java115.entity.Customer;
import com.java115.entity.Employees;
import com.java115.entity.Order;
import com.java115.service.CustomerService;
import com.java115.service.EmpService;
import com.java115.utils.DateUtils;



public class OrderDao extends BaseDao{
	//--------------
		public int insert(Order order){
			int result = 0;
			String sql = "insert into product(orderID,order_date,customerID,empID) value(?,?,?,?)";
			result = super.exeUpdate(sql,order.getOrderID(),order.getOrder_date(),order.getCustomer().getCustomerID(),order.getEmp().getEmpID());
			
			return result;
		}
		
		
		public int selordersCount(String orderID){
			int count = 0;
			String sql = "select count(*) num from orders where orderID=?";
			ResultSet set = super.exeQuery(sql,orderID);
			try {
				if(set.next()){
					count = set.getInt("num");
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				super.CloseAll();
			}
			
			return count;
		}
		
		

		public int add(String orderID, int empID, int customerID,Date order_date) {
			int result = 0;
			String sql = "insert into orders(orderID,order_date,customerID,empID) value(?,?,?,?)";
			result = super.exeUpdate(sql,orderID,order_date,customerID,empID);
			
			return result;
		}
		
		
		public int delordByid(String ordID){
			
			String sql = "delete from orders where orderID=?";
			
			return  super.exeUpdate(sql, ordID);
		}

		//---------------
				public int selProsCount(String ordID){
					int count = 0;
					String sql = "select count(*) num from orders where orderID like ?";
					
					ResultSet rs = super.exeQuery(sql,"%"+ordID+"%");
					try {
						if(rs.next()){
							count = rs.getInt("num");
							
						}
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
					
					return count;
				}
				
				
				public List<Order> searchOrders(String ordID, int start,int length) throws SQLException {
					
					List<Order> ors = new ArrayList<Order>();
					String sql = "select * from orders   order by orderID desc limit ?,?";
					CustomerDao cd=new CustomerDao();
					EmpDao empDao=new EmpDao();
					ResultSet rs = super.exeQuery(sql,start,length);
					try {
						while (rs.next()) {
							Customer customer = cd.selCusById(rs.getInt("customerID"));
							Order or = new Order();
							or.setCustomer(customer);
							Employees emp = empDao.selEmpById(rs.getInt("empID"));
							or.setEmp(emp);
							or.setOrder_date(rs.getDate("order_date"));
							or.setOrderID(rs.getString("orderID"));
							
							ors.add(or);

						}
					} catch (SQLException e) {

						e.printStackTrace();
					} finally {
						super.CloseAll();
					}
					return ors;
				}


				public Order selOrderByOrderId(String orderid) {
					String sql = "select * from orders where orderID = ? ";
		
					ResultSet set = super.exeQuery(sql, orderid);
					CustomerService cts = new CustomerService();
					EmpService es = new EmpService();
					Order order = new Order();
					try {
						if (set.next()) {
							int id = set.getInt("id");
							String orderID = set.getString("orderID");
							Date order_date = set.getDate("order_date");
							
							int customerID = set.getInt("customerID");
							Customer customer = cts.getCustomerById(customerID);
							int empid = set.getInt("empID");
							Employees employees = es.getEmployeesById(empid);

							order = new Order(id, orderID, order_date, customer, employees);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						super.CloseAll();
					}
					return order;
				}


				public int delOrder(String orderid) {
					String sql = "delete from orders where orderID = ?";
					Object[] values = { orderid };
					return super.exeUpdate(sql, values);
				}


				public int editOrder(Order order) {
					String sql = "update orders set order_date = ?,customerID = ? ,empID = ? where orderID = ?";
					Object[] values = { order.getOrder_date(),
							order.getCustomer().getCustomerID(),
							order.getEmp().getEmpID(), order.getOrderID() };
					return super.exeUpdate(sql, values);
				}


				
				}

