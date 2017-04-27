package com.java115.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java115.utils.DateUtils;

public class FileServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String action = request.getParameter("op");
			if("upload".equals(action)){
				upload(request,response);
			}else if("download".equals(action)){
				download(request,response);
			}
	}
	
	//-------�ļ��ϴ�--------
	protected void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File("E:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean result = upload.isMultipartContent(request);
		if(result){
			try {
				 List<FileItem> items = upload.parseRequest(request);
				 Map<String,String> map = new HashMap<String,String>();
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
							 String path = "WEB-INF/upload/"+realname;
								path = getServletContext().getRealPath(path);
								System.out.println("path:"+path);
								 outputStream = new FileOutputStream(path);
								 inputStream = item.getInputStream();
								byte[] datas = new byte[1024];
								int len = -1;
								while((len=inputStream.read(datas))>0){
									outputStream.write(datas,0,len);
								}
								
								map.put(item.getFieldName(), realname);
								System.out.println(item.getName()+"�ϴ��ɹ�!");
								
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
				 String filename = map.get("filename");
				 String myfile1 = map.get("myfile1");
				 String myfile2 = map.get("myfile2");
				 String filedesc = map.get("filedesc");
				 System.out.println("filename:"+filename);
				 System.out.println("myfile1:"+myfile1);
				 System.out.println("myfile2:"+myfile2);
				 System.out.println("filedesc:"+filedesc);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			doGet(request, response);
	}
	
	//-------�ļ�����--------
	protected void download(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//---------1���õ��ļ���·�����ж��ļ��Ƿ����----------
			String path = "WEB-INF/upload/2017042014212621269705.pdf";
			path = getServletContext().getRealPath(path);
			File file = new File(path);
			if(!file.exists()){
				response.getWriter().print("�ļ�������!");
				return;
			}
			//-----2���õ�������-------
			InputStream inputStream = new FileInputStream(file);
			int length = inputStream.available();
			if(length <= 0){
				response.getWriter().print("�ļ�����!");
				return;
			}
			response.reset();
			response.setContentLength(length);
			String filename = "����111111.pdf";
			filename = new String(filename.getBytes("utf-8"),"iso-8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+filename);
			//-------3���õ������---------
			OutputStream outputStream = response.getOutputStream();
			byte[] datas = new byte[1024];
			int len = -1;
			if((len=inputStream.read(datas))>0){
				outputStream.write(datas, 0, len);
			}
			outputStream.close();
			inputStream.close();
	}
}