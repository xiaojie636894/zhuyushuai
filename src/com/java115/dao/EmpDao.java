package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java115.entity.Customer;
import com.java115.entity.Employees;
import com.java115.entity.Product;
import com.java115.entity.Ptype;

public class EmpDao extends BaseDao{
	public List<Employees> selAllemp(){
		List<Employees> emps = new ArrayList<Employees>();
		String sql = "select * from employees order by empID desc";
		
		
		ResultSet rs = super.exeQuery(sql);
		try {
			while(rs.next()){
				Employees emp = new Employees();
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmpID(rs.getInt("empID"));				
				emps.add(emp);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return emps;
	}
	
	public Employees selEmpById(int id){
		Employees emp = new Employees();
		String sql = "select * from employees where empID=?";
		
		
		ResultSet rs = super.exeQuery(sql,id);
		try {
			if(rs.next()){
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmpID(rs.getInt("empID"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return emp;
	}

	public List<Employees> selectAllEmployees() {
		String sql = "select * from employees order by empID asc";
		ResultSet set = super.exeQuery(sql, null);
		List<Employees> list = new ArrayList<Employees>();
		try {
			while (set.next()) {
				int empID = set.getInt("empID");
				String emp_name = set.getString("emp_name");

				list.add(new Employees(empID, emp_name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return list;
	}
}

