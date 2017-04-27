package com.java115.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.java115.entity.Userinfo;



	public class UserinfoDao extends BaseDao{
		//-------Ôö¼Ó-----
		public int insertUserinfo(Userinfo user){
			int result = 0;
			String sql = "insert into userinfo(userid,username,password)";
			Object[] values = {user.getUserid(),user.getUsername(),user.getPassword()};
			result = super.exeUpdate(sql, values);
			return result;
		}
		
		//---------²éÑ¯-----------
		public Userinfo selUserByUsername(String username){
			Userinfo user = null;
			String sql = "select * from userinfo where username = ?";
			Object[] values = {username};
			ResultSet rs = super.exeQuery(sql, values);
			try {
				if(rs.next()){
					user = new Userinfo();
					user.setPassword(rs.getString("password"));
					user.setUsername(rs.getString("username"));
					user.setRealname(rs.getString("realname"));
					user.setUserid(rs.getInt("userid"));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				CloseAll();
			}
			return user;
		}
		
		
		
	}

