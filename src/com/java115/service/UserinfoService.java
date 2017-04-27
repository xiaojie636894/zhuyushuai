package com.java115.service;

import com.java115.dao.UserinfoDao;
import com.java115.entity.Userinfo;



public class UserinfoService {
	private UserinfoDao dao = new UserinfoDao();
	public boolean login(Userinfo user){
		boolean result = false;
		Userinfo realuser = dao.selUserByUsername(user.getUsername());
		if(realuser != null){
			if(realuser.getPassword().equals(user.getPassword())){
				return true;
			}
		}
		
		return result;
	}
	
	public Userinfo getUserByUsername(String username){
		return dao.selUserByUsername(username);
		
	}
}
