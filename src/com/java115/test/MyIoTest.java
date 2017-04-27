package com.java115.test;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;

import com.java115.entity.Product;
import com.java115.entity.Ptype;

public class MyIoTest {
	public static void main(String[] args){
		testObjectInputStream();
	}
	
	
	public static void testObjectInputStream(){
		String path = "E:\\Java2\\stu.txt";
		
		File file = new File(path);
		
		if(!file.exists()){
			System.out.println("�ļ������ڣ�");
			return;
		}		
			
			ObjectInputStream inputStream;
			
				try {
					
					inputStream = new ObjectInputStream(new FileInputStream(file));
					
					Product pro = (Product)inputStream.readObject();
//					pro.show();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
					
				
				
			}
	
	
	//-----�����(�ֽ�)------
		public static void testObjectOutputStream(){
			String path = "E:\\Java2\\stu.txt";
			
			File file = new File(path);
			
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
				
				ObjectOutputStream outputStream;
				
					try {
						
						outputStream = new ObjectOutputStream(new FileOutputStream(file));
//						Product pro = new Product(1,"ϴ�»�",new Ptype(1,"���õ���"),2000,3000,new Date());
//						outputStream.writeObject(pro);
						System.out.println("�ɹ���");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
						
					
					
				}
				
					
					
				
			
			
			
		
	
	//-----�����(�ַ�)------
		public static void testFileWrite(){
			String path = "E:\\Java2\\1.txt";
			String newpath = "E:\\Java2\\3.txt";
			File file = new File(path);
			Reader reader = null;
			FileWriter write = null;
			if(file.exists() && file.isFile()){
				try {
					reader = new FileReader(file);
					char[] datas = new char[2];
					write = new FileWriter(newpath);
					int len = -1;
					while((len = reader.read(datas))>0){
						write.write(datas,0,len);
					}
					System.out.println("�ɹ���");
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally{
					if(write != null){
						
							try {
								write.close();
							} catch (IOException e) {
							}
						
					}if(reader != null){
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		}
		
	
	//-----������(�ַ�)------
	public static void testReader(){
		String path = "E:\\Java2\\1.txt";
		File file = new File(path);
		Reader reader = null;
		CharArrayWriter write = null;
		if(file.exists() && file.isFile()){
			try {
				reader = new FileReader(file);
				char[] datas = new char[2];
				write = new CharArrayWriter();
				int len = -1;
				while((len = reader.read(datas))>0){
					write.write(datas,0,len);
				}
				
				char[] array = write.toCharArray();
				String mag = new String(array);
				System.out.println(mag);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if(write != null){
					
						write.close();
					
				}if(reader != null){
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	//-----�����(�ֽ�)------
	public static void testFileOutputStream(){
		String path = "E:\\Java2\\1.txt";
		String newpath = "E:\\Java2\\2.txt";
		File file = new File(path);
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		if(file.exists() && file.isFile()){
			try {
				inputStream = new FileInputStream(file);
				byte[] datas = new byte[1024];
				outputStream = new FileOutputStream(newpath);
				int len = -1;
				while((len = inputStream.read(datas))>0){
					outputStream.write(datas,0,len);
				}
				
				System.out.println("�ɹ���");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if(outputStream != null){
					try {
						outputStream.close();
					} catch (IOException e) {
					}
				}if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
	
	//-----������(�ֽ�)------
	public static void testInputStream(){
		String path = "E:\\Java2\\1.txt";
		File file = new File(path);
		InputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		if(file.exists() && file.isFile()){
			try {
				inputStream = new FileInputStream(file);
				byte[] datas = new byte[1024];
				outputStream = new ByteArrayOutputStream();
				int len = -1;
				while((len = inputStream.read(datas))>0){
					outputStream.write(datas,0,len);
				}
				
				byte[] array = outputStream.toByteArray();
				String mag = new String(array);
				System.out.println(mag);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if(outputStream != null){
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		
	}
}
