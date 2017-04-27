package com.java115.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private String clsame = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/business2";
	private String uid = "root";
	private String pwd = "";
	private Connection conn = null;
	private PreparedStatement pstms = null;
	private ResultSet  rs = null;
	
	public BaseDao(){
		try {
			Class.forName(clsame);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 通用的   增   删   改
	 * 
	 * @param sql   要执行的insert  update delete
	 * @param values   ？对于的值，如果没有？传递null
	 * @return   返回影响行数
	 */
	public int exeUpdate(String sql,Object ... values){
		int result = 0;
		try {
			conn = DriverManager.getConnection(url,uid,pwd);
			pstms = conn.prepareStatement(sql);
			if(values != null){
				for(int i=0;i<values.length;i++){
					pstms.setObject(i+1,values[i]);
				}
			}
			result = pstms.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			CloseAll();
		}
		
		return result;
	}
	/**
	 * 
	 * 查的通用方法
	 * @param sql  执行select sql语句
	 * @param values  ？对于的值，如果没有？传递null
	 * @return   返回结果集，注意：调用者用完之后通过CloseAll释放资源
	 */
	public ResultSet exeQuery(String sql,Object ... values){
		
		try {
			conn = DriverManager.getConnection(url,uid,pwd);
			pstms = conn.prepareStatement(sql);
			if(values != null){
				for(int i=0;i<values.length;i++){
					pstms.setObject(i+1,values[i]);
				}
			}
			rs = pstms.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			
		}
		
		return rs;
	}
	
	public void CloseAll(){
		if(pstms != null){
			try {
				pstms.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
