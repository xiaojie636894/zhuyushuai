package com.java115.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.java115.entity.Providers;

public class ProviderDao extends BaseDao  {
	// --娣诲姞渚涘簲鍟�--
	public int insertProvider(Providers provider) {
		String sql = "insert into providers (provider_name,provider_add,provider_tel,account,email) values (?,?,?,?,?)";
		Object[] values = { provider.getProvider_name(),
				provider.getProvider_add(), provider.getProvider_tel(),
				provider.getAccount(), provider.getEmail() };
		return super.exeUpdate(sql, values);
	}

	// -- --鏍规嵁妯＄硦鍚嶇О锛屾煡璇rovider--
	public List<Providers> selectProvByName(String name, int start, int length) {
		String sql = "select * from providers where provider_name like ? order by providerID asc limit ?,?";
		Object[] values = { "%"+name+"%", start, length };
		ResultSet set = super.exeQuery(sql, values);
		List<Providers> list = new ArrayList<Providers>();
		try {
			while (set.next()) {
				int providerID = set.getInt("providerID");
				String category_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");
				
				list.add(new Providers(providerID, category_name,provider_add,provider_tel,account,email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return list;
	}
	
	// -- --鏌ヨ鎵�鏈塒rovider--
	public List<Providers> selectAllProvider() {
		String sql = "select * from providers order by providerID asc";
		ResultSet set = super.exeQuery(sql, null);
		List<Providers> list = new ArrayList<Providers>();
		try {
			while (set.next()) {
				int providerID = set.getInt("providerID");
				String category_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");
				
				list.add(new Providers(providerID, category_name,provider_add,provider_tel,account,email));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return list;
	}
	
	// --鏍规嵁妯＄硦鍚嶇О锛屾煡璇㈣褰曟暟--
	public int selCountByName(String name) {
		String sql = "select count(*) num from providers where provider_name like ?";
		Object[] values = { "%"+name+"%" };
		int count = 0;
		ResultSet set = super.exeQuery(sql, values);
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

	// --鏍规嵁鍚嶇О鏌ヨProvider--
	public Providers selProviderByName(String name) {
		String sql = "select * from providers where provider_name = ?";
		Object[] values = { name };
		ResultSet set = super.exeQuery(sql, values);
		Providers provider = null;
		try {
			if (set.next()) {
				int providerID = set.getInt("providerID");
				String category_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");

				provider = new Providers(providerID, category_name,provider_add,provider_tel,account,email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return provider;
	}

	// --鏍规嵁ID鏌ヨProvider--
	public Providers selProviderByID(int id) {
		String sql = "select * from providers where providerID = ?";
		Object[] values = { id };
		ResultSet set = super.exeQuery(sql, values);
		Providers provider = null;
		try {
			if (set.next()) {
				int providerID = set.getInt("providerID");
				String category_name = set.getString("provider_name");
				String provider_add = set.getString("provider_add");
				String provider_tel = set.getString("provider_tel");
				String account = set.getString("account");
				String email = set.getString("email");

				provider = new Providers(providerID, category_name,provider_add,provider_tel,account,email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.CloseAll();
		}
		return provider;
	}

	// --鍒犻櫎Provider--
	public int delProvider(int id) {
		String sql = "delete from providers where providerID = ?";
		Object[] values = { id };
		return super.exeUpdate(sql, values);
	}

	// --淇敼Provider--
	public int editProvider(Providers provider) {
		String sql = "update providers set provider_name = ?,provider_add = ? ,provider_tel = ?," +
				"account = ?,email = ? where providerID = ?";
		Object[] values = { provider.getProvider_name(),
				provider.getProvider_add(), provider.getProvider_tel(),
				provider.getAccount(), provider.getEmail(),provider.getProviderID()};
		return super.exeUpdate(sql, values);
	}

}
