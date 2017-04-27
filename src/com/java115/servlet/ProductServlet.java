package com.java115.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.java115.entity.DataTables;
import com.java115.entity.JsonResult;
import com.java115.entity.Product;
import com.java115.entity.Ptype;
import com.java115.service.ProService;
import com.java115.service.PtypeService;
import com.java115.utils.DateUtils;

public class ProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ProService pservice = null;
	private PtypeService service = null;
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("op");
		if ("toadd".equals(action)) {
			toadd(request, response);
		} else if ("doadd".equals(action)) {
			doadd(request, response);
		} else if ("del".equals(action)) {
			del(request, response);
		} else if ("toedit".equals(action)) {
			toedit(request, response);
		} else if ("doedit".equals(action)) {
			doedit(request, response);
		} else if ("getprosbytype".equals(action)) {
			getprosbytype(request, response);
		}  else if ("manydel".equals(action)) {
			manydel(request, response);
		}else if ("search".equals(action)) {
			search(request, response);
		}else if ("manager".equals(action)) {
			manager(request, response);
		}else if ("view".equals(action)) {
			view(request, response);
		}else if ("toupload".equals(action)) {
			toupload(request, response);
		}else if ("doupload".equals(action)) {
			upload(request, response);
		}
	}
	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	protected void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String,String> map = new HashMap<String,String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("E:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
	
		boolean result = upload.isMultipartContent(request);
		if(result){
			try {
				 List<FileItem> items = upload.parseRequest(request);
				 
				 for(FileItem item : items){
					 if(item.isFormField()){
						 String name = item.getFieldName();
						 String value = item.getString();
						 value = new String(value.getBytes("iso-8859-1"),"utf-8");
						 
						 map.put(name, value);
					 }else{
							 FileOutputStream outputStream = null;
							 InputStream inputStream = null;
						 try {
							 String name = item.getName();
							 String ext = name.substring(name.lastIndexOf("."));
							 String realname = DateUtils.getRandomFileName(ext);
							 String image = "image/"+realname;
							 image = getServletContext().getRealPath(image);
								System.out.println("path:"+image);
								 outputStream = new FileOutputStream(image);
								 inputStream = item.getInputStream();
								byte[] datas = new byte[1024];
								int len = -1;
								while((len=inputStream.read(datas))>0){
									outputStream.write(datas,0,len);
								}
								
								map.put(item.getFieldName(), realname);
								
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							if(outputStream!=null){
								outputStream.close();
							}if(inputStream!=null){
								inputStream.close();
							}
							item.delete(); 
						}
						 
						
					 }
				 }
				 
				 String myfile = map.get("myfile");
				 //将myfile 保存到数据库并关联pid
				 String pid = request.getParameter("proid");
				 ProService ps = new ProService();
				 Product product=new Product();
				 product = ps.getProById(Integer.parseInt(pid));
				 product.setImage(myfile);
				 int editPro = ps.editPro(product);
				 System.out.println(editPro);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void view(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProService service = new ProService();
		int proid = Integer.valueOf(request.getParameter("pid"));
		Product pro = service.getProById(proid);
		JsonResult json = new JsonResult();
		if(pro!=null){
			json.setTag(true);
			json.setData(pro);
		}else{
			json.setTag(false);
			json.setMessage("没有该产品数据!");
		}
		Gson gson = new Gson();
		response.getWriter().print(gson.toJson(json));
	}
	
	//------删除-------
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		ProService service = new ProService();
		int result = service.delProductById(id);
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.print("yes");
		} else {
			out.print("no");
		}
	}
	
	//------批量删除------
	public void manydel(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String[] ids = request.getParameterValues("ckbproid");
			ProService service = new ProService();
			
			int result = service.delManyPro(ids);
			PrintWriter out = response.getWriter();
			if (result > 0) {
				response.sendRedirect(request.getContextPath()
						+ "/admin/pros?op=search");
			} else {
				out.print("<script>alert('删除失败！');history.go(-1);</script>");
			}
		}

	public void toedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProService service = new ProService();
		int proid = Integer.valueOf(request.getParameter("id"));
		Product pro = service.getProById(proid);
		PtypeService typeservice = new PtypeService();
		List<Ptype> types = typeservice.getAllTypes();
		request.setAttribute("pro", pro);
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/pros/toedit.jsp").forward(request,
				response);
	}
	public void toupload(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProService service = new ProService();
		int proid = Integer.valueOf(request.getParameter("id"));
		Product pro = service.getProById(proid);
		PtypeService typeservice = new PtypeService();
		List<Ptype> types = typeservice.getAllTypes();
		request.setAttribute("pro", pro);
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/pros/toupload.jsp").forward(request,
				response);
	}
		
		
		
		
		
		

	
		
	//--------修改--------
	public void doedit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pname = request.getParameter("pname");
		int typeid = Integer.valueOf(request.getParameter("ptypes"));
		double inprice = Double.valueOf(request.getParameter("inprice"));
		double salprice = Double.valueOf(request.getParameter("salprice"));
		int proid = Integer.valueOf(request.getParameter("proid"));
		Date date = DateUtils.getDateByStr(request.getParameter("date"),
				"yyyy-MM-dd");

		Product pro = new Product();
		pro.setDate(date);
		pro.setInprice(inprice);
		pro.setSalprice(salprice);
		pro.setPname(pname);
		Ptype ptype = new Ptype();
		ptype.setPtypeid(typeid);
		pro.setPtype(ptype);
		pro.setPid(proid);

		PrintWriter out = response.getWriter();
		ProService service = new ProService();
		int result = service.editPro(pro);
		if (result == 1) {
			out.print("yes");
		} else {
			out.print("no");
		}
	}

	
	
	
	//--------搜索（模糊查询）----------
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("proname");
		if(name==null){
			name = "";
		}
		String pagestr = request.getParameter("page");
		int pageindex = 1;
		if (pagestr != null && pagestr != "") {
			pageindex = Integer.valueOf(pagestr);
		}

		String pagesizestr = request.getParameter("pagesize");
		int pagesize = 2;
		if (pagesizestr != null && pagesizestr != "") {
			pagesize = Integer.valueOf(pagesizestr);
		}

		ProService service = new ProService();
		List<Product> pros = service.search(name,pageindex, pagesize);
		int pagecount = service.getPageCount(name,pagesize);

		request.setAttribute("pageindex", pageindex);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("pros", pros);
		request.setAttribute("pname", name);
		int[] pagesizes = { 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		request.setAttribute("pagesizes", pagesizes);
		request.getRequestDispatcher("/admin/pros/list.jsp").forward(request,
				response);
	}
	
	//--------搜索（ajax--产品名称）----------
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

			ProService service = new ProService();
			List<Product> pros = service.searchjson(name,start, length);
			int rscount = service.getRscount(name);
			DataTables table = new DataTables();
			table.setRecordsFiltered(rscount);
			table.setRecordsTotal(rscount);
			table.setData(pros);
			String jsonstr = JSON.toJSON(table).toString();
			response.setCharacterEncoding("UTF-8");

			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(jsonstr);
		}
		
		
		
		
		
		//-------------通过类别获得产品----------------
	public void getprosbytype(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int typeid = Integer.valueOf(request.getParameter("typeid"));
		ProService service = new ProService();
		List<Product> pros = service.getProsByType(typeid);
		String jsonstr = JSON.toJSON(pros).toString();
		PrintWriter out = response.getWriter();
		out.println(jsonstr);
		
		
	}

	public void toadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PtypeService typeservice = new PtypeService();
		List<Ptype> types = typeservice.getAllTypes();
		request.setAttribute("types", types);
		request.getRequestDispatcher("/admin/pros/add.jsp").forward(request,
				response);
	}
	
	
	//------增加-------
	public void doadd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		String pname = request.getParameter("pname");
		int typeid = Integer.valueOf(request.getParameter("ptypes"));
		double inprice = Double.valueOf(request.getParameter("inprice"));
		double salprice = Double.valueOf(request.getParameter("salprice"));
		Date date = DateUtils.getDateByStr(request.getParameter("date"),
				"yyyy-MM-dd");

		Product pro = new Product();
		pro.setDate(date);
		pro.setInprice(inprice);
		pro.setSalprice(salprice);
		pro.setPname(pname);
		Ptype ptype = new Ptype();
		ptype.setPtypeid(typeid);
		pro.setPtype(ptype);

		ProService service = new ProService();
		PrintWriter out = response.getWriter();
		int result = service.addPro(pro);
		if (result == 1) {
			out.print("yes");
		} else {
			out.print("no");
		}
	}
}