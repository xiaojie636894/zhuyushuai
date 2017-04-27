package com.java115.service;

import java.util.List;

import com.java115.dao.Order_detailDao;
import com.java115.entity.Order_detail;

public class Order_detailService {

	Order_detailDao odd = new Order_detailDao();
	
	public int getdetailCount(String oid){
		return odd.seldetailCount(oid);
	}
	
	public List<Order_detail> getdetailBystart(String oid,int start,int pagesize){
		return odd.seldetailBystart(oid, start, pagesize);
	}
	
	public int deldetailByid(String oid){
		return odd.deldetailByid(oid);
	}

	public int add(String orderID, int proID, int num) {
		
		return odd.insert(orderID,proID,num);
	}
}
