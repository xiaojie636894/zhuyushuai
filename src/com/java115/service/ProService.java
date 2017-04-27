package com.java115.service;

import java.util.List;

import com.java115.dao.ProductDao;
import com.java115.entity.Product;

public class ProService {
	private ProductDao dao = new ProductDao();
	
	//--------����--------
	public int addPro(Product pro){
		return dao.insert(pro);
	}
	
	//--------�޸�--------
		public int editPro(Product pro){
			return dao.update(pro);
		}
	
	//--------���ң����ҳ��--------
	public List<Product> getProsByPage(int pageindex, int pagesize){
		return dao.selProsByPage(pageindex, pagesize);
	}
	
	//---------ɾ��----------
		public int delProductById(int id){
			int result = 0;
			result = dao.delById(id);
			return result;
		}
		
		//---------����ɾ��----------
		public int delManyPro(String[] ids) {
			String idstr = "";
			for(String str : ids){
				idstr= idstr + "," + str;
			}
			if(idstr.length()>0){
				idstr=idstr.substring(1);
			}
			int result =  dao.delByMany(idstr);
			return result;
		}
		
	//------��������------	
	public List<Product> getAllPros(){
		return dao.selAllPros();
	}
	
	//------�����ҳ��------
	public int getPageCount(String name,int pagesize){
		int rscount = dao.selProsCount(name);
		return (rscount+pagesize-1)/pagesize;
	}
	
	//------����ܼ�¼��------
		public int getRscount(String name){
			return  dao.selProsCount(name);
		}
	
	//---------����(���Id)----------
	public Product getProById(int id){
		return dao.selProById(id);
	}
	
	
	public List<Product> getProsByType(int typeid){
		return dao.selProsByType(typeid);
	}
	
	//-----����(ģ���ѯ)------
		public List<Product> search(String name,int pageindex,int pagesize){
			return dao.searchProsByPage(name, pageindex, pagesize);
		}
		
		
	public List<Product> searchjson(String name, int start, int pagesize) {
		return dao.searchPros(name, start, pagesize);
	}

	public Product getProductById(int id) {
		
			return dao.selProById(id);
		
	}
}
