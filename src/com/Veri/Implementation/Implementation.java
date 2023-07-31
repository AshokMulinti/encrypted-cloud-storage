package com.Veri.Implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Veri.Database.DataBaseConnection;
import com.Veri.Interface.Interface;
import com.Veri.bean.UploadBean;
import com.Veri.bean.Users;


public class Implementation implements Interface {
	
	Connection con;

	@Override
	public int DataOwnerRegister(Users Dataownerregister) {
		

		int result = 0;
		
		String otp = "AutoUpdate";
		
		try {

			con = DataBaseConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO dataownerregister VALUES (?,?,?,?,?)");
			ps.setInt(1, Dataownerregister.getId());
			ps.setString(2, Dataownerregister.getName());
			ps.setString(3, Dataownerregister.getEmail());
			ps.setString(4, Dataownerregister.getPassword());
			ps.setString(5, otp);


			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
			
	}

	@Override
	public int DataOwnerLogin(String email, String password) {
		
		int result = 0;
		con=DataBaseConnection.createConnection();

		try {

			PreparedStatement ps = con.prepareStatement("SELECT * FROM dataownerregister WHERE email='"+email+"' and password='"+password+"'");
			ResultSet rs = ps.executeQuery();

			System.out.println("email"+email);
			System.out.println("Password"+password);
			while(rs.next()){
				String emailch = rs.getString("email");     
				String passwordch = rs.getString("password");


				if(email.equals(emailch)&&password.equals(passwordch)){
					result = 1;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	
	}

	@Override
	public int DataUserRegister(Users dataUserRegister) {
		int result = 0;
		
		try {

			con = DataBaseConnection.createConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO datauserregister VALUES (?,?,?,?)");
			ps.setInt(1, dataUserRegister.getId());
			ps.setString(2, dataUserRegister.getName());
			ps.setString(3, dataUserRegister.getEmail());
			ps.setString(4, dataUserRegister.getPassword());
			


			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int DataUserLogin(String email, String password) {
		
		int result = 0;
		con=DataBaseConnection.createConnection();

		try {

			PreparedStatement ps = con.prepareStatement("SELECT * FROM datauserregister WHERE email='"+email+"' and password='"+password+"'");
			ResultSet rs = ps.executeQuery();

			System.out.println("email"+email);
			System.out.println("Password"+password);
			while(rs.next()){
				String emailch = rs.getString("email");     
				String passwordch = rs.getString("password");


				if(email.equals(emailch)&&password.equals(passwordch)){
					result = 1;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int fileUpload(UploadBean upload) {
		int ans=0;
						
		try {
			con = DataBaseConnection.createConnection();
			PreparedStatement ptmt = con.prepareStatement("INSERT INTO uploadfile VALUES(?,?,?,?,?,?,?,?,?,?)");
			ptmt.setString(1, upload.getDataowner());
			ptmt.setString(2, upload.getFilename());
			ptmt.setString(3, upload.getType());
			ptmt.setString(4, upload.getPath());
			ptmt.setString(5, upload.getContent());
			ptmt.setString(6, upload.getSize());
			ptmt.setString(7, upload.getKey());
			ptmt.setString(8, upload.getEncrypt());
			ptmt.setString(9, upload.getDecrypt());
			ptmt.setString(10, "waiting");
			ans = ptmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	@Override
	public String getpublickey(String filename) {
		int i = 0;
		String publickey = null;
		try{
			con = DataBaseConnection.createConnection();
			PreparedStatement pt = con.prepareStatement("SELECT `key` FROM uploadfile where filename = '"+filename+"'");
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				publickey = rs.getString(1);
				System.out.println("key is :" +publickey);
			}
			System.out.println("xc"+i);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return publickey;
	}

}
