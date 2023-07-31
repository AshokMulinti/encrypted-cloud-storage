package com.Veri.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.Veri.Implementation.Implementation;
import com.Veri.Interface.Interface;

import pcloud.PCloudAPI;
import pcloud.PCloudAPIDebug;




//import com.itextpdf.text.log.SysoCounter;

@WebServlet("/Download")
public class Download extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	public Download() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String values = request.getParameter("check");
		System.out.println(values);
		
		String filename = request.getParameter("Filename");
		System.out.println("File name -- "+filename);
		
		String filekey = request.getParameter("filekey");
		System.out.println("the filekey"+filekey);
		
		Interface ill = new Implementation();
		String filekey1 = ill.getpublickey(filename);
		System.out.println("fileKey1==="+filekey1);
		
//		
//		JOptionPane jpane = new JOptionPane("Enter the Secret key");
//		JDialog jdialouge = jpane.createDialog("Alert");
//		jdialouge.setAlwaysOnTop(true);
//		jdialouge.show();
//		
//		String typingPrivatekey = JOptionPane.showInputDialog("Enter the Secret Key");
		
		if(filekey1.equalsIgnoreCase(filekey)) {
			System.out.println("Matched");
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
				String filepath = "C:\\Users\\91630\\workspace\\VeriDedup\\WebContent\\LOCAL\\";
				String filename1 = request.getParameter("Filename");
				System.out.println("filename is comming----"+filename1);
				
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition","attachment; filename=\"" + filename1 + "\"");
					java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath+filename1);
					
					int i;
					while((i=fileInputStream.read())!= -1) {
						out.write(i);
						
					}
					fileInputStream.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
//			 try{
//	             File file=new File("E:\\Temp\\Wedding.zip");
//	             FileOutputStream ofile=new FileOutputStream(file);
//	             PCloudAPI conn=new PCloudAPI(true);
//	             Hashtable <String, Object> params=new Hashtable <String, Object> ();
//			     params.put("username", "cloudproject18032019@gmail.com");
//			     params.put("password", "18032019");
//	             params.put("fileids", values.replace('f', ' ').trim());
//	             System.out.println("Started");
//	             long startTime = System.currentTimeMillis();
//	             PCloudAPIDebug.print(conn.sendCommand("getzip", params));
//	             
//	             conn.readData(ofile);
//	             long stopTime = System.currentTimeMillis();
//	             long elapsedTime = stopTime - startTime;
//	             System.out.println("TIME ::: " + (float)(elapsedTime / 1000) + " sec");
//	             System.out.println("end");
//	             try {
//	     			ZipFile zipFile = new ZipFile("E:\\Temp\\Wedding.zip");
//	     			Enumeration<?> enu = zipFile.entries();
//	     			while (enu.hasMoreElements()) {
//	     				ZipEntry zipEntry = (ZipEntry) enu.nextElement();
//
//	     				String name = zipEntry.getName();
//	     				long size = zipEntry.getSize();
//	     				long compressedSize = zipEntry.getCompressedSize();
//	     				System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", name, size, compressedSize);
//
//	     				File file1 = new File("E:\\Temp\\"+name);
//	     				if (name.endsWith("/")) {
//	     					file1.mkdirs();
//	     					continue;
//	     				}
//
//	     				File parent = file1.getParentFile();
//	     				if (parent != null) {
//	     					parent.mkdirs();
//	     				}
//
//	     				InputStream is = zipFile.getInputStream(zipEntry);
//	     				FileOutputStream fos = new FileOutputStream(file1);
//	     				byte[] bytes = new byte[1024];
//	     				int length;
//	     				while ((length = is.read(bytes)) >= 0) {
//	     					fos.write(bytes, 0, length);
//	     				}
//	     				is.close();
//	     				fos.close();
//
//
//	     				
//	     				
//	     			}
//	     			zipFile.close();
//	     		} catch (IOException e) {
//	     			e.printStackTrace();
//	     		}
//	  
//	           }
//	           catch(Exception e){
//	             e.printStackTrace();
//	           }
		} else {
			System.out.println("Failed");
			RequestDispatcher dd = request.getRequestDispatcher("Downloads.jsp?success=0");
			dd.forward(request, response);
		}
	}

}
