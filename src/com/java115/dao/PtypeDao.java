package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java115.entity.Product;
import com.java115.entity.Ptype;

public class PtypeDao extends BaseDao{
	
	//--------增加---------
	public int insert(Ptype ptype){
		int result = 0;
		String sql = "insert into ptypes(ptypename) values(?)";
		result = super.exeUpdate(sql,ptype.getPtypename());
		return result;
	}
	
	//-------修改-------
	public int update(Ptype ptype){
		int result = 0;
		String sql = "update ptypes set ptypename=? where ptypeid = ?";
		result = super.exeUpdate(sql,ptype.getPtypename(),ptype.getPtypeid());
		return result;
	}
	
	//----删除------
	public int delById(int id){
		int result = 0;
		String sql = "delete from ptypes where ptypeid = ?";
		result = super.exeUpdate(sql,id);
		return result;
	}
	//------查找（根据Id）---------
	public Ptype selPtypeById(int id){
		Ptype ptype = null;
		String sql = "select * from ptypes where ptypeid = ?";
		ResultSet rs = super.exeQuery(sql, id);
		try {
			if(rs.next()){
				ptype = new Ptype();
				ptype.setPtypeid(rs.getInt("ptypeid"));
				ptype.setPtypename(rs.getString("ptypename"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return ptype;
	}
	
	//-------查找（根据名字）----------
	public Ptype selPtypeByName(String name){
		Ptype ptype = null;
		String sql = "select * from ptypes where ptypename = ?";
		ResultSet rs = super.exeQuery(sql, name);
		try {
			if(rs.next()){
				ptype = new Ptype();
				ptype.setPtypeid(rs.getInt("ptypeid"));
				ptype.setPtypename(rs.getString("ptypename"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return ptype;
		
	}
	//------查找全部------
	public List<Ptype> selAllTypes(){
		List<Ptype> ptypes = new ArrayList<Ptype>();
		String sql = "select * from ptypes order by ptypeid desc";
		ResultSet rs = super.exeQuery(sql);
		try {
			while(rs.next()){
				Ptype ptype = new Ptype();
				ptype.setPtypeid(rs.getInt("ptypeid"));
				ptype.setPtypename(rs.getString("ptypename"));
				ptypes.add(ptype);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return ptypes;
	}
	
	
	public List<Ptype> searchTypes(){
		List<Ptype> ptypes = new ArrayList<Ptype>();
		String sql = "select * from ptypes where ptypename like ? order by ptypeid desc ";
		ResultSet rs = super.exeQuery(sql);
		try {
			while(rs.next()){
				Ptype ptype = new Ptype();
				ptype.setPtypeid(rs.getInt("ptypeid"));
				ptype.setPtypename(rs.getString("ptypename"));
				ptypes.add(ptype);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return ptypes;
	}

	public List<Ptype> searchPty(String name, int start, int pagesize) {
		List<Ptype> ptys = new ArrayList<Ptype>();
		String sql = "select * from ptypes where ptypename like ? order by ptypeid desc limit ?,?";
		

		ResultSet rs = super.exeQuery(sql, "%" + name + "%", start , pagesize);
		try {
			while (rs.next()) {
				
				Ptype pty = new Ptype();
				pty.setPtypeid(rs.getInt("ptypeid"));
				pty.setPtypename(rs.getString("ptypename"));
				

				ptys.add(pty);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return ptys;
	}

	public int selPtyCount(String name) {
		int count = 0;
		String sql = "select count(*) num from ptypes where ptypename like ?";
		
		
		ResultSet rs = super.exeQuery(sql,"%"+name+"%");
		try {
			if(rs.next()){
				count = rs.getInt("num");
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return count;
	}

	public int delPtypeById(int id) {
		int result = 0;
		String sql = "delete from ptypes where ptypeid = ?";
		result = super.exeUpdate(sql,id);
		return result;
	}

	public Ptype selPtyById(int ptyid) {
		Ptype pty = new Ptype();
		String sql = "select * from ptypes where ptypeid=?";
		
		
		ResultSet rs = super.exeQuery(sql,ptyid);
		try {
			if(rs.next()){
				pty.setPtypeid(rs.getInt("ptypeid"));
				pty.setPtypename(rs.getString("ptypename"));
				
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return pty;
	}

	public int edit(Ptype pty) {
		int result = 0;
		String sql = "update ptypes set ptypename=? where ptypeid=?";
		result = super.exeUpdate(sql,pty.getPtypename(),pty.getPtypeid());
		
		return result;
	}
	
}	