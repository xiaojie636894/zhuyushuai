package com.java115.service;

import java.util.List;

import com.java115.dao.ProductDao;
import com.java115.dao.PtypeDao;
import com.java115.entity.Ptype;

public class PtypeService {
	private PtypeDao dao= new PtypeDao();
	//------����--------
	public int addPtype(Ptype type){
		int result = 0;
		Ptype realtype = dao.selPtypeByName(type.getPtypename());
		if(realtype == null){
			result = dao.insert(type);
		}else{
			result = -1;
		}
		return result;
	}
	
	//-------�޸�-----------
	public int editPtype(Ptype type){
		int result = 0;
		Ptype realtype = dao.selPtypeByName(type.getPtypename());
		if(realtype == null){
			result = dao.update(type);
		}else{
			if(type.getPtypeid()==realtype.getPtypeid()){
				result = dao.update(type);
			}else{
			result = -1;
			}
		}
		return result;
	}
	
	//---------ɾ��----------
	public int delPtypeById(int id){
		int result = 0;
		ProductDao pdao = new ProductDao();
		int count = pdao.selProsCountByType(id);
		if(count == 0){
			result = dao.delById(id);
		}else{
			result = -1;
		}
		
		return result;
	}
	
	
	
	public List<Ptype> getAllTypes(){
		return dao.selAllTypes();
	}
	
	
	public List<Ptype> getTypes(){
		return dao.searchTypes();
	}
	
	
	//-----���ң����Id��------
	public Ptype selPtypeById(int id){
		return dao.selPtypeById(id);
	}
	
	//-----���ң�������֣�------
		public Ptype selPtypeByName(String name){
			return dao.selPtypeByName(name);
		}

		public List<Ptype> searchjson(String name, int start, int pagesize) {
			
			
			return dao.searchPty(name, start, pagesize);
		}

		public int getRscount(String name) {
			return  dao.selPtyCount(name);
		}

		

		public int delPtyById(int id) {
			
			return  dao.delPtypeById(id);
			
		}

		public Ptype getPtyById(int ptyid) {
			return dao.selPtyById(ptyid);
		}

		
		

		public int editPty(Ptype pty) {
			return dao.edit(pty);
		}

		
}
