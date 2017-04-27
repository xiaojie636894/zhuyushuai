package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java115.entity.Product;
import com.java115.entity.Ptype;

public class ProductDao extends BaseDao{
	//-------����-------
	public int insert(Product pro){
		int result = 0;
		String sql = "insert into product(pname,ptypeid,inprice,salprice,date) value(?,?,?,?,?)";
		result = super.exeUpdate(sql,pro.getPname(),pro.getPtype().getPtypeid(),pro.getInprice(),pro.getSalprice(),pro.getDate());
		
		return result;
	}
	
	//-------�޸�-------
		public int update(Product pro){
			int result = 0;
			String sql = "update product set image=?,pname=?,ptypeid=?,inprice=?,salprice=?,date=? where pid=?";
			result = super.exeUpdate(sql,pro.getImage(),pro.getPname(),pro.getPtype().getPtypeid(),pro.getInprice(),pro.getSalprice(),pro.getDate(),pro.getPid());
			
			return result;
		}
	
	//-------ɾ��---------
	public int delById(int id){
		int result = 0;
		String sql = "delete from product where pid = ?";
		result = super.exeUpdate(sql,id);
		return result;
	}
	
	//-------����ɾ��---------
	public int delByMany(String idstr){
		int result = 0;
		String sql = "delete from product where pid in ("+idstr+")";
		result = super.exeUpdate(sql);
		return result;
	}

		
		
	//-------����-------
	public List<Product> selAllPros(){
		List<Product> pros = new ArrayList<Product>();
		String sql = "select * from product order by pid desc";
		PtypeDao typedao = new PtypeDao();
		
		ResultSet rs = super.exeQuery(sql);
		try {
			while(rs.next()){
				Product pro = new Product();
				pro.setDate(rs.getDate("date"));
				pro.setInprice(rs.getDouble("inprice"));
				pro.setPid(rs.getInt("pid"));
				pro.setPname(rs.getString("pname"));
				pro.setSalprice(rs.getDouble("salprice"));
				int typeid = rs.getInt("ptypeid");
				Ptype type = typedao.selPtypeById(typeid);
				pro.setPtype(type);
				
				pros.add(pro);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return pros;
	}
	
	
	
	public List<Product> selProsByType(int pid){
		List<Product> pros = new ArrayList<Product>();
		String sql = "select * from product where ptypeid = ? order by pid desc";
		PtypeDao typedao = new PtypeDao();
		
		ResultSet rs = super.exeQuery(sql,pid);
		try {
			while(rs.next()){
				Product pro = new Product();
				pro.setDate(rs.getDate("date"));
				pro.setInprice(rs.getDouble("inprice"));
				pro.setPid(rs.getInt("pid"));
				pro.setPname(rs.getString("pname"));
				pro.setSalprice(rs.getDouble("salprice"));
				int typeid = rs.getInt("ptypeid");
				Ptype type = typedao.selPtypeById(typeid);
				pro.setPtype(type);
				
				pros.add(pro);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			super.CloseAll();
		}
		return pros;
	}
	
	
		public Product selProById(int id){
			Product pro = null;
			String sql = "select * from product where pid=?";
			PtypeDao typedao = new PtypeDao();
			
			ResultSet rs = super.exeQuery(sql,id);
			try {
				if(rs.next()){
					pro = new Product();
					pro.setDate(rs.getDate("date"));
					pro.setInprice(rs.getDouble("inprice"));
					pro.setPid(rs.getInt("pid"));
					pro.setPname(rs.getString("pname"));
					pro.setSalprice(rs.getDouble("salprice"));
					int typeid = rs.getInt("ptypeid");
					Ptype type = typedao.selPtypeById(typeid);
					pro.setPtype(type);
					
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			finally{
				super.CloseAll();
			}
			return pro;
		}
		
		
		//------����(ģ���ѯ)---------
	public List<Product> searchProsByPage(String proname, int pageindex,
			int pagesize) {
		List<Product> pros = new ArrayList<Product>();
		String sql = "select * from product where pname like ? order by pid desc limit ?,?";
		PtypeDao typedao = new PtypeDao();

		ResultSet rs = super.exeQuery(sql, "%" + proname + "%", (pageindex-1)*pagesize , pagesize);
		try {
			while (rs.next()) {
				Product pro = new Product();
				pro.setDate(rs.getDate("date"));
				pro.setInprice(rs.getDouble("inprice"));
				pro.setPid(rs.getInt("pid"));
				pro.setPname(rs.getString("pname"));
				pro.setSalprice(rs.getDouble("salprice"));
				int typeid = rs.getInt("ptypeid");
				Ptype type = typedao.selPtypeById(typeid);
				pro.setPtype(type);

				pros.add(pro);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return pros;
	}
	
	
	
	public List<Product> searchPros(String proname, int start,
			int pagesize) {
		
		List<Product> pros = new ArrayList<Product>();
		String sql = "select * from product where pname like ? order by pid desc limit ?,?";
		PtypeDao typedao = new PtypeDao();

		ResultSet rs = super.exeQuery(sql, "%" + proname + "%", start , pagesize);
		try {
			while (rs.next()) {
				
				Product pro = new Product();
				pro.setDate(rs.getDate("date"));
				pro.setImage(rs.getString("image"));
				pro.setInprice(rs.getDouble("inprice"));
				pro.setPid(rs.getInt("pid"));
				pro.setPname(rs.getString("pname"));
				pro.setSalprice(rs.getDouble("salprice"));
				int typeid = rs.getInt("ptypeid");
				Ptype type = typedao.selPtypeById(typeid);
				pro.setPtype(type);

				pros.add(pro);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return pros;
	}
	
	//------��ҳ---------
		public List<Product> selProsByPage(int pageindex,int pagesize){
			List<Product> pros = new ArrayList<Product>();
			String sql = "select * from product order by pid desc limit ?,?";
			PtypeDao typedao = new PtypeDao();
			
			ResultSet rs = super.exeQuery(sql,(pageindex-1)*pagesize,pagesize);
			try {
				while(rs.next()){
					Product pro = new Product();
					pro.setDate(rs.getDate("date"));
					pro.setInprice(rs.getDouble("inprice"));
					pro.setPid(rs.getInt("pid"));
					pro.setPname(rs.getString("pname"));
					pro.setSalprice(rs.getDouble("salprice"));
					int typeid = rs.getInt("ptypeid");
					Ptype type = typedao.selPtypeById(typeid);
					pro.setPtype(type);
					
					pros.add(pro);
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			finally{
				super.CloseAll();
			}
			return pros;
		}
		
		//------����ܼ�¼��---------
		public int selProsCount(String name){
			int count = 0;
			String sql = "select count(*) num from product where pname like ?";
			PtypeDao typedao = new PtypeDao();
			
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
		
		//------ɾ������ж��Ƿ�������---------
				public int selProsCountByType(int typeid){
					int count = 0;
					String sql = "select count(*) num from product where ptypeid = ?";
					PtypeDao typedao = new PtypeDao();
					
					ResultSet rs = super.exeQuery(sql,typeid);
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

				public int selCountByProvId(int provid) {
					String sql = "select count(*) num from product where providerID = ?";
					Object[] values = { provid };
					ResultSet set = super.exeQuery(sql, values);
					int count = 0;
					try {
						if (set.next()) {
							count = set.getInt("num");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						super.CloseAll();
					}
					return count;
				}
}
