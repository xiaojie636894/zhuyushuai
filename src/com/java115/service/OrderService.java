package com.java115.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.java115.dao.OrderDao;
import com.java115.dao.Order_detailDao;
import com.java115.entity.Order;
import com.java115.entity.Order_detail;

public class OrderService {
	
	OrderDao od = new OrderDao();
	Order_detailDao odd = new Order_detailDao();
	
		public int add(String orderID, int empID, int customerID, Date order_date) {
			
			return od.add(orderID,empID,customerID,order_date);
			
		}
		
public int add(String orderID, int empID, int customerID, Date order_date,String[] proids,String[] nums) {
			
			int result = od.add(orderID,empID,customerID,order_date);
			for(int i=0;i<proids.length;i++){
				int proID=Integer.valueOf(proids[i]);
				int num = Integer.valueOf(nums[i]);
				System.out.println("产品："+proID);
				System.out.println("数量："+num);
				
			}
			return result;
		}

public int getorderCount(String orderID){
	return od.selordersCount(orderID);
}


public int getRscount(String ordID){
	return  od.selProsCount(ordID);
  	}
		
public List<Order> getorderBystart(String ordID, int start, int length) throws SQLException {
			return od.searchOrders(ordID, start, length);
	}
 


public int delorderByid(String orderID){
	odd.deldetailByid(orderID);
	return od.delordByid(orderID);
}

public Order getOrderByOrderId(String orderid) {
	return od.selOrderByOrderId(orderid);
}

public List<Order_detail> getOrderDetail(String orderid) {
	return odd.selOrderDetail(orderid);
}

public int delOrderByOrderId(String orderid) {
	int resultod = 0;
	int resultodd = odd.delOrderDetail(orderid);	
	if (resultodd > 0) {
		resultod = od.delOrder(orderid);
	}
	
	return resultod;
}

public int editOrderByOrderId(Order order, List<Order_detail> orderdetails) {
	int result = 0;
	int resulto = od.editOrder(order);
	int resultod = 0;
	List<Order_detail> details = odd.selOrderDetail(order.getOrderID());
	for (int i=0;i<details.size();i++) {
		int f = -1;
		for (int j=0;j<orderdetails.size();j++) {
			if (details.get(i).getProduct().getPid() == orderdetails.get(j).getProduct().getPid()) {
				f = 1;
				break;
			}else {
				continue;
			}
		}			
		if (f == -1) {
			resultod = odd.delDetailById(details.get(i).getId());
		}				
	}
	for (Order_detail orderdetail : orderdetails) {
		int t = -1;
		for (Order_detail detail : details) {
			if (orderdetail.getProduct().getPid() == detail.getProduct().getPid()) {
				resultod = odd.editOrderDetail(orderdetail);
				t = 1;
				break;
			}else{
				continue;
			}
		}			
		if (t == -1) {
			resultod = odd.insterOrderDetail(orderdetail);
		}					
	}		
	if (resulto > 0&& resultod > 0) {
		result = 1;
	} else {
		result = -1;
	}
	return result;
}
}  



	

		

