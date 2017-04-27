package com.java115.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.java115.entity.DataTables;
import com.java115.entity.Product;
import com.java115.entity.Ptype;
import com.java115.service.ProService;
import com.java115.service.PtypeService;
import com.java115.utils.DateUtils;

public class PtypeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String action = request.getParameter("op");
			if("list".equals(action)){
				list(request, response);
			}else if("doadd".equals(action)){
				doadd(request, response);
			}else if("del".equals(action)){
				del(request, response);
			}else if("toedit".equals(action)){
				toedit(request, response);
			}else if("doedit".equals(action)){
				doedit(request, response);
			}else if("checkname".equals(action)){
				checkName(request, response);
			}else if("select".equals(action)){
				select(request, response);
			}else if("manager".equals(action)){
				manager(request, response);
			}else if("toadd".equals(action)){
				toadd(request, response);
			}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
	
	
	
	protected void toadd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PtypeService typeservice = new PtypeService();
		List<Ptype> types = typeservice.getAllTypes();
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/ptypes/add.jsp").forward(request,
				response);
	}
	
	public void manager(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("proname");
		if(name==null){
			name = "";
		}
		String startstr = request.getParameter("start");
		int start = 1;
		if (startstr != null && startstr != "") {
			start = Integer.valueOf(startstr);
		}

		String lengthstr = request.getParameter("length");
		int length = 2;
		if (lengthstr != null && lengthstr != "") {
			length = Integer.valueOf(lengthstr);
		}

		PtypeService service = new PtypeService();
		List<Ptype> pty = service.searchjson(name,start, length);
		int rscount = service.getRscount(name);
		DataTables table = new DataTables();
		table.setRecordsFiltered(rscount);
		table.setRecordsTotal(rscount);
		table.setData(pty);
		String jsonstr = JSON.toJSON(table).toString();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(jsonstr);
	}
	
	
	protected void checkName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String name = request.getParameter("tname");
			PtypeService service = new PtypeService();
			Ptype ptype = service.selPtypeByName(name);
			PrintWriter out = response.getWriter();
			if(ptype!=null){
				out.print("true");
			}else{
				out.print("false");
			}
	}
	
	
	protected void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		PtypeService service = new PtypeService();
		int result = service.delPtyById(id);
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.print("yes");
		} else {
			out.print("no");
		}
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			PtypeService service = new PtypeService();
	    	List<Ptype> types = service.getAllTypes();
	    	request.setAttribute("ptypes",types);
	    	request.getRequestDispatcher("/admin/ptypes/list.jsp").forward(request, response);
	}
	
	
	protected void select(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			PtypeService service = new PtypeService();
	    	List<Ptype> types = service.getAllTypes();
	    	request.setAttribute("ptypes",types);
	    	request.getRequestDispatcher("/admin/ptypes/select.jsp").forward(request, response);
	}
	
	
	
	protected void toedit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	PtypeService service = new PtypeService();
	int ptyid = Integer.valueOf(request.getParameter("id"));
	Ptype pty = service.getPtyById(ptyid);
	
	
	request.setAttribute("ptypes", pty);
	
	request.getRequestDispatcher("/admin/ptypes/toedit.jsp").forward(request,response);
	}
	
	protected void doadd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String typename = request.getParameter("categoryname");
		Ptype type = new Ptype();
		type.setPtypename(typename);
		
		PtypeService service = new PtypeService();
		PrintWriter out = response.getWriter();
		int result = service.addPtype(type);
	    out.print(result);

	}
	
	
	protected void doedit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ptypename = request.getParameter("typename");
		int ptypeid = Integer.valueOf(request.getParameter("id"));
		Ptype pty = new Ptype();		
		pty.setPtypename(ptypename);
		pty.setPtypeid(ptypeid);
		PrintWriter out = response.getWriter();
		PtypeService service = new PtypeService();
		int result = service.editPty(pty);
		if (result== 1) {
			out.print("yes");
		} else {
			out.print("");
		}
}
}