package com.java115.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.java115.entity.Customer;
import com.java115.entity.DataTables;
import com.java115.entity.Employees;
import com.java115.entity.Order;
import com.java115.entity.Order_detail;
import com.java115.entity.Product;
import com.java115.service.CustomerService;
import com.java115.service.EmpService;
import com.java115.service.OrderService;
import com.java115.service.Order_detailService;
import com.java115.utils.DateUtils;

public class OrderServlet extends HttpServlet{
	private static final long serialVersionUID = 5372115159728998121L;
	private OrderService os;

	public OrderServlet() {
		os = new OrderService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("op");
		if("dolist".equals(action)){
			dolist(request,response);
		}else if("toorder".equals(action)){
			toorder(request,response);
		}else if("editorder".equals(action)){
			editorder(request,response);
		}else if("doedit".equals(action)){
			doEdit(request,response);
		}else if("orderDetail".equals(action)){
			orderDetail(request,response);
		}else if("delOrder".equals(action)){
			delOrder(request,response);
		}else if("list".equals(action)){
			try {
				list(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void delOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String orderid = request.getParameter("orderid");
		
		int result = os.delOrderByOrderId(orderid);
		if (result > 0) {
			out.print("yes");
		} else {
			out.print("no");
		}
	}
	

	
	protected void orderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		Order order = os.getOrderByOrderId(orderid);
		List<Order_detail> orderdetails = os.getOrderDetail(orderid);
		System.out.println(order.getOrder_date());
		request.setAttribute("order", order);
		request.setAttribute("orderdetails", orderdetails);
		request.getRequestDispatcher("/admin/orders/orderDetail.jsp").forward(
				request, response);
	}	
	
	protected void toorder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CustomerService cus = new CustomerService();
		List<Customer> customer = cus.getAllcust();
		EmpService es = new EmpService();
		List<Employees> emps = es.getAllemp();
		String orderID = DateUtils.getRandomOrderID();
		request.setAttribute("customer",customer);
		request.setAttribute("emps", emps);
		request.setAttribute("orderID", orderID);
		request.getRequestDispatcher("/admin/orders/order.jsp").forward(request, response);
		
	}
	
	
	protected void editorder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("orderid");
		CustomerService cts = new CustomerService();
		List<Customer> customer = cts.getAllCustomer();
		EmpService es = new EmpService();
		List<Employees> employees = es.getAllEmployees();
		request.setAttribute("customer", customer);
		request.setAttribute("employees", employees);
		Order order = os.getOrderByOrderId(orderid);
		List<Order_detail> orderdetails = os.getOrderDetail(orderid);
		request.setAttribute("order", order);
		request.setAttribute("orderdetails", orderdetails);
		request.getRequestDispatcher("/admin/orders/editOrder.jsp").forward(request, response);
	}
	
	
	protected void doEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderID = request.getParameter("orderid");
		
		Customer customer = new Customer(Integer.valueOf(request.getParameter("customerid")));
		Employees employees = new Employees(Integer.valueOf(request.getParameter("empid")));
		Date order_date = DateUtils.getDateByStr(request.getParameter("orderdate"), "yyyy-MM-dd");
		Order order = new Order(Integer.parseInt(request.getParameter("id")),orderID, order_date, customer, employees);
		String[] productids = request.getParameterValues("productid");
		String[] quantitys = request.getParameterValues("quantity");
		List<Order_detail> orderdetails = new ArrayList<Order_detail>();
		for (int i = 0; i < quantitys.length; i++) {
			
			int productid = Integer.parseInt(productids[i]);
			Product product = new Product();
			product.setPid(productid);
			int quantity = Integer.parseInt(quantitys[i]);

			orderdetails.add(new Order_detail(orderID, product, quantity));

		}
		int result = os.editOrderByOrderId(order,orderdetails);
		PrintWriter out = response.getWriter();
		if (result > 0) {
			out.print("yes");
		} else {
			out.print("no");
		}

	}
	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		
		String ordID = request.getParameter("ordID");
		if(ordID==null){
			ordID="";
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

		OrderService os = new OrderService();
		int pagecount = os.getRscount(ordID);
		List<Order> list = os.getorderBystart(ordID,start,length);
		DataTables tables = new DataTables();
		tables.setRecordsFiltered(pagecount);
		tables.setRecordsTotal(pagecount);
		tables.setData(list);
		String jsonstr = JSON.toJSON(tables).toString();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(jsonstr);
		
	}
	
			
			
	
	protected void dolist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderID = request.getParameter("orderID");
		int customerID = Integer.valueOf(request.getParameter("cust"));
		int empID = Integer.valueOf(request.getParameter("emp"));
		Date date = DateUtils.getDateByStr(request.getParameter("orderdate"),"yyyy-MM-dd");
		String[] proids = request.getParameterValues("proids");
		String[] nums = request.getParameterValues("pronums");
		OrderService os = new OrderService();
		Order_detailService ods = new Order_detailService();
		os.add(orderID,empID,customerID,date);
		System.out.println("订单号："+orderID);
		System.out.println("客户："+customerID);
		System.out.println("员工："+empID);
		System.out.println("订单日期："+request.getParameter("orderdate"));
		for(int i=0;i<proids.length;i++){
			int proID=Integer.valueOf(proids[i]);
			int num = Integer.valueOf(nums[i]);
			System.out.println("产品："+proID);
			System.out.println("数量："+num);
			ods.add(orderID,proID,num);
		}
		response.sendRedirect(request.getContextPath()+"/admin/orders/list.jsp");
		
	}
}
