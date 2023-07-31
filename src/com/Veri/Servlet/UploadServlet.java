package com.Veri.Servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.Veri.Implementation.Implementation;
import com.Veri.Interface.Interface;
import com.Veri.bean.UploadBean;
import com.Veri.crypto.AES;


import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import pcloud.PCloudAPI;
import pcloud.PCloudAPIDebug;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**																							
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		HttpSession session=request.getSession();
		String KCGLogin=session.getAttribute("DataOwner").toString();
		MultipartParser mp =new MultipartParser(request, 999999999);
		
		String ff = null;
		String filename = request.getParameter("filename");
		String key=request.getParameter("key");
		System.out.println("filename:"+filename);
		
		Part part = null;
		ArrayList paramValues = new ArrayList();
		
		FilePart filePart = null;
		ParamPart param = null;
		File file1 = null;
		String filepath1 = null;
		String filetype = null;
		String filepath2 = null;
		
		long size = 0;
		String path = getServletContext().getRealPath("");
		System.out.println("path-00000" +path);
		
		String editPath = path.substring(0, path.indexOf("."));
		System.out.println("editpath-11111" +editPath);
		
		String fullPath = editPath+"VeriDedup\\WebContent\\LOCAL\\";
		System.out.println("fullPath-22222" +fullPath);
		
		while((part=mp.readNextPart())!=null) {
			if(part.isFile()){
				filePart = (FilePart)part;
				filename = filePart.getFileName();
				System.out.println("filename-33333" +filename);
				fullPath = fullPath+filename;
				System.out.println("fullPath-44444" +fullPath);
				
				File file = new File(fullPath);
				size = filePart.writeTo(file);
				System.out.println("size-55555" +size);
				
				filetype = filePart.getContentType();
				System.out.println("filetype-66666" +filetype);
				
			} else if(part.isParam()) {
				param = (ParamPart) part;
				String tagName = param.getName();
				System.out.println("tagName-77777" +tagName);
				String tagValue = param.getStringValue();
				System.out.println("tagValue-88888" +tagValue);
				
				paramValues.add(tagName);
				paramValues.add(tagValue);
			}
		}
		String fileContent = "";
		String encrypt = null;
		String encontent = null;
		String decontent = null;
		
		if(filename.endsWith(".txt")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent+reading;
			System.out.println("fileContent-99999" +fileContent);
			
			try {
				encontent = AES.encrypt99(fileContent);
				System.out.println("encontent-1010101010" +encontent);
				filepath1 = editPath + "VeriDedup/WebContent/Decrypt/" + filename;
				file1 = new File(filepath1);
				file1.createNewFile();
					if(!file1.exists()) {
						file1.createNewFile();
					}
					FileWriter fw = new FileWriter(file1.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(encontent);
					bw.close();
					System.out.println("file-1111111111" +filepath1);
					
					
				decontent = AES.decrypt(encontent);
				System.out.println("decontent-1212121212" +decontent);
				filepath2 = editPath + "VeriDedup/WebContent/Decrypt/" + filename;
				File file2 = new File(filepath2);
				file2.createNewFile();
					if(!file2.exists()) {
						file2.createNewFile();
					}
					FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
					BufferedWriter bw1 = new BufferedWriter(fw1);
					bw1.write(decontent);
					bw1.close();
					System.out.println("file-2222222222" +filepath2);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else if(filename.endsWith(".docx")){
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
			
		} else if(filename.endsWith(".pdf")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if(filename.endsWith(".sql")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if(filename.endsWith(".mp4")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if(filename.endsWith(".img")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if(filename.endsWith(".png")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if(filename.endsWith(".jpg")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		} else if(filename.endsWith(".jpeg")) {
			FileInputStream fis = new FileInputStream(fullPath);
			byte[] b = new byte[fis.available()];
			fis.read(b);
			String reading = new String(b);
			fileContent = fileContent + reading;
            System.out.println("filecontent=" + fileContent);
           
            try {//try1 open
            	
                
    	encontent = AES.encrypt99(fileContent);
    	System.out.println("encontent===="+encontent);
        filepath1 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
        file1 = new File(filepath1);
        file1.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    				BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(encontent);// Write in file
                    bw.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath1);
       
          //file decrypted and store into filepath2
                  decontent= AES.decrypt(encontent);
    				System.out.println("decontent===="+decontent);
    		    filepath2 = editPath + "VeriDedup/WebContent/Decrypt/"+filename;
                    File file2 = new File(filepath2);
                    file2.createNewFile();
                    if (!file1.exists()) {file1.createNewFile();}// If file doesn't exists, then create it
                    FileWriter fw1 = new FileWriter(file2.getAbsoluteFile());
    				BufferedWriter bw1 = new BufferedWriter(fw1);
                    bw1.write(decontent);// Write in file
                    bw1.close();// Close connection
                    System.out.println("fileeeeeeeeeeeeeeeee" + filepath2);
       
                    
                    } catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		UploadBean bean = new UploadBean();
		bean.setDataowner(KCGLogin);
		bean.setFilename(filename);
		bean.setType(filetype);
		bean.setContent(fileContent);
		bean.setPath(fullPath);
	
		bean.setSize(String.valueOf(size));
		bean.setKey(paramValues.get(0).toString());
		bean.setEncrypt(encontent);
		bean.setDecrypt(fileContent);
		
		System.out.println("encrypted content" +decontent);
		
		Interface iii = new Implementation();
		int f = iii.fileUpload(bean);
		System.out.println("the value of f is111:" +f);
		if(f==1) {
			System.out.println("the value of f is222:" +f);
			if(ServletFileUpload.isMultipartContent(request)) {
				try{
					String ftype = null;
					File file = new File(fullPath);
					
					FileInputStream ifile = new FileInputStream(file);
					PCloudAPI conn = new PCloudAPI(true);
					 Hashtable <String, Object> params=new Hashtable <String, Object> ();
				      params.put("username", "cloudproject18032019@gmail.com");	
				      params.put("password", "18032019");
				      params.put("folderid", 0);
				
				      Object res=conn.sendCommand("listfolder", params);
				      if (PCloudAPI.resultGetLong(res, "result")!=0){
				        System.out.println("Error: "+PCloudAPI.resultGetString(res, "error"));
				        return;
				      }
				      Object[] contents=PCloudAPI.resultGetArray(PCloudAPI.resultGetHashtable(res, "metadata"), "contents");
				      for (int ii=0; ii<contents.length; ii++){
				        Object entry=contents[ii];
				        if (PCloudAPI.resultGetBoolean(entry, "isfolder"))
				        {
				           System.out.println("Folder: "+PCloudAPI.resultGetString(entry, "name"));
				            if(PCloudAPI.resultGetString(entry, "name").equals("MyCloud"))
				            {
				            	  System.out.println("Folder Id: "+PCloudAPI.resultGetString(entry, "id"));
				            	  String fid=PCloudAPI.resultGetString(entry, "id").replace('d',' ');
				            	 // fid.replace('d',' ');
				            	  ff=fid.trim();
				            	  System.out.println(ff);
				             	 
				                  
				            }
				        } 
				        
				        else
				           System.out.println("File: "+PCloudAPI.resultGetString(entry, "name")+" Size: "+PCloudAPI.resultGetLong(entry, "size"));
				      }  
		              params.put("folderid", ff);
				     // params.put("progresshash", "7606879663799546276");

				      params.put("filename", filename);
				      PCloudAPIDebug.print(conn.sendCommand("uploadfile", params, ifile));
					} catch (Exception ex) {
    					request.setAttribute("message", "File Upload Failed due to "
    							+ ex);
    				}
    	 
    			} else {
    				request.setAttribute("message",
    						"Sorry this Servlet only handles file upload request");
    			}
    	 
    			
    			
            	/////////////////////////
   response.sendRedirect("UploadFiles.jsp?success=1");
            	
            	
            }
            
            else
            	
            {
   response.sendRedirect("Error.jsp");
            	
            	
            }
	
	}

}
