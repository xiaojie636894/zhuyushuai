package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java115.entity.Order_detail;
import com.java115.entity.Product;
import com.java115.service.ProService;

public class Order_detailDao extends BaseDao{
	public int seldetailCount(String oid){
		int count =0;
		String sql = "select count(*) num from order_detail where orderID=?";
		ResultSet set = super.exeQuery(sql, oid);
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
	
	public List<Order_detail> seldetailBystart(String oid,int start,int pagesize){
		List<Order_detail> list = new ArrayList<Order_detail>();
		String sql = "select * from order_detail where orderID=? limit?,?";
		ResultSet set = super.exeQuery(sql, oid,start,pagesize);
		ProService ps = new ProService();
		try {
			while(set.next()){
				Order_detail od = new Order_detail();
				od.setOrderID(set.getString("orderID"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.CloseAll();
		}
		return list;
	}
	
	
	public int deldetailByid(String oid){
		int result = 0;
		String sql = "delete from order_detail where orderID=?";
		super.exeUpdate(sql, oid);
		return result;
	}

	public int insert(String orderID, int proID, int num) {
		String sql = "insert into order_detail(orderID,pid,quantity) value(?,?,?)";
		
		return super.exeUpdate(sql,orderID,proID,num);
	}

	public List<Order_detail> selOrderDetail(String orderid) {
		String sql = "select * from order_detail where orderID = ? order by id";
		Object[] values = { orderid };
		ResultSet set = super.exeQuery(sql, values);
		ProService ps = new ProService();
		List<Order_detail> list = new ArrayList<Order_detail>();
		try {
			while (set.next()) {
				int id = set.getInt("id");
				String orderID = set.getString("orderID");
				int pid = set.getInt("pid");
				Product product = ps.getProductById(pid);
				int quantity = set.getInt("quantity");
				
				list.add(new Order_detail(id, orderID, product, quantity));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return list;
	}

	public int delOrderDetail(String orderid) {
		String sql = "delete from order_detail where orderID = ?";
		Object[] values = { orderid };
		return super.exeUpdate(sql, values);
	}

	public int delDetailById(int id) {
		String sql = "delete from order_detail where id = ?";
		Object[] values = { id };
		return super.exeUpdate(sql, values);
	}

	public int editOrderDetail(Order_detail orderdetail) {
		String sql = "update order_detail set quantity = ? where orderID = ? and pid = ?";
		Object[] values = { orderdetail.getQuantity(),orderdetail.getOrderID(),orderdetail.getProduct().getPid() };
		return super.exeUpdate(sql, values);
	}

	public int insterOrderDetail(Order_detail orderdetail) {
		String sql = "insert into order_detail (orderID,pid,quantity) values (?,?,?)";
		Object[] values = { orderdetail.getOrderID(),
				orderdetail.getProduct().getPid(),
				orderdetail.getQuantity() };
		return super.exeUpdate(sql, values);
	}

	
}
