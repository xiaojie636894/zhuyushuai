package com.java115.service;

import java.util.List;

import com.java115.dao.ProductDao;
import com.java115.dao.ProviderDao;
import com.java115.entity.Providers;



public class ProviderService  {
	private ProviderDao pd = new ProviderDao();

	// --娣诲姞渚涘簲鍟�--
	public int addProvider(Providers provider) {
		int result = 0;
		Providers pr = pd.selProviderByName(provider.getProvider_name());
		if (pr == null) {
			result = pd.insertProvider(provider);
		} else {
			result = -1;
		}
		return result;
	}

	// --鏍规嵁妯＄硦鍚嶇О锛岃幏寰椾緵搴斿晢--
	public List<Providers> searchProvByName(String name, int start, int length) {
		return pd.selectProvByName(name, start, length);
	}
	
	// --鏄剧ず渚涘簲鍟嗗垪琛�--
	public List<Providers> getAllProvider() {
		return pd.selectAllProvider();
	}
	
	// --鏍规嵁妯＄硦鍚嶇О锛岃幏鍙栦緵搴斿晢璁板綍鏁�--
	public int getCountByName(String name) {
		return  pd.selCountByName(name);
	}
    
	// --鍒犻櫎--
	public int delProviderById(int id) {
		int result = 0;
		ProductDao ptd = new ProductDao();
		int count = ptd.selCountByProvId(id);
		if(count == 0){
			result = pd.delProvider(id);
		}else{
			result = -1;
		}		
		return result;
	}
    // --鏍规嵁ID鑾峰緱渚涘簲鍟�--
	public Providers getProviderById(int id) {
		return pd.selProviderByID(id);
	}
	
    // --鏍规嵁鍚嶇О鑾峰緱渚涘簲鍟�--
	public boolean getProviderByName(String name) {
		boolean result = false;
		Providers provider = pd.selProviderByName(name);
		if(provider != null){
			result = true;
		}
		return result;
	}
	
    // --淇敼--
	public int editProviderById(Providers provider) {
		int result = 0;
		Providers pr = pd.selProviderByName(provider.getProvider_name());
		if (pr == null) {
			result = pd.editProvider(provider);
		} else {
			if (pr.getProviderID() == provider.getProviderID()) {
				result = pd.editProvider(provider);
			} else {
				result = -1;
			}
		}
		return result;
	}
}
