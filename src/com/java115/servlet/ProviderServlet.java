package com.java115.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.java115.entity.Providers;
import com.java115.service.ProviderService;
import com.java115.utils.DataTables;

public class ProviderServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ProviderService ps;
	private String[] emailType;

	public ProviderServlet() {
		ps = new ProviderService();		
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("op");
		if ("toadd".equals(action)) {
			toAdd(request, response);
		}
		if ("checkname".equals(action)) {
			checkName(request, response);
		}
		if ("doadd".equals(action)) {
			doAdd(request, response);
		}
		if ("dolist".equals(action)) {
			doList(request, response);
		}
		if ("dodel".equals(action)) {
			doDel(request, response);
		}
		if ("toedit".equals(action)) {
			toEdit(request, response);
		}
		if ("doedit".equals(action)) {
			doEdit(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// --娣诲姞--
	protected void toAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("emailType", emailType);
		request.getRequestDispatcher("/admin/providers/addProvider.jsp").forward(request, response);
	}
	
	protected void doAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("providername");
		String add = request.getParameter("provideradd");
		String tel = request.getParameter("providertel");
		String account = request.getParameter("account");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		Providers provider = new Providers(name,add,tel,account,email);
		int result = ps.addProvider(provider);
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.print(1);
		} else if (result == -1) {
			out.print(-1);
		} else {
			out.print(0);
		}
	}

	// --鏄剧ず鎵�鏈変緵搴斿晢锛堟ā绯婃悳绱€�佸垎椤碉級--
	protected void doList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("searchname");
		if(name == null){
			name = "";
		}
		int start = 1;
		String startstr = request.getParameter("start");
		if (startstr != null&& startstr != "") {			
			start = Integer.parseInt(startstr);
		}
		int length = 5;
		String lengthstr = request.getParameter("length");
		if (lengthstr != null&& lengthstr != "") {			
			length = Integer.parseInt(lengthstr);
		}
		int rscount = ps.getCountByName(name);
		List<Providers> lists = ps.searchProvByName(name, start, length);
		DataTables tables = new DataTables();
		tables.setData(lists);
		tables.setRecordsTotal(rscount);
		tables.setRecordsFiltered(rscount);
		String tablesjson = JSON.toJSON(tables).toString();
		response.getWriter().print(tablesjson);
		request.setAttribute("shname", name);
	}
	
	// --鏍￠獙渚涘簲鍟嗛噸鍚�--
	protected void checkName(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		boolean result = ps.getProviderByName(name);
		PrintWriter out = response.getWriter();
		String jsresult = JSON.toJSON(result).toString();
		out.print(jsresult);
	}

	// --鍒犻櫎--
	protected void doDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int result = ps.delProviderById(id);
		if (result > 0) {
			out.print(1);
		} else if (result == -1) {
			out.print(-1);
		} else {
			out.print(0);
		}
	}

	// --淇敼--
	protected void toEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Providers provider = ps.getProviderById(id);
		request.setAttribute("provider", provider);
		String[] email = provider.getEmail().split("@");
		request.setAttribute("email1", email[0]);
		request.setAttribute("email2", email[1]);
		request.setAttribute("emailType", emailType);
		request.getRequestDispatcher("/admin/providers/editProvider.jsp").forward(
				request, response);
	}

	protected void doEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("providerid"));
		String name = request.getParameter("providername");
		String add = request.getParameter("provideradd");
		String tel = request.getParameter("providertel");
		String account = request.getParameter("account");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		Providers provider = new Providers(id,name,add,tel,account,email);
		int result = ps.editProviderById(provider);
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.print(1);
		} else if (result == -1) {
			out.print(-1);
		} else {
			out.print(0);
		}
	}

	@Override
	public void init() throws ServletException {
		emailType = new String[]{"qq.com","yahoo.com","sina.com","163.com","126.com","vip.sina.com","sina.cn"};
	}

}
