package com.Veri.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Veri.Database.DataBaseConnection;



/**
 * Servlet implementation class VerifyOTPServlet
 */
@WebServlet("/VerifyOTPServlet")
public class VerifyOTPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyOTPServlet() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String dataowner = request.getParameter("dataowner");
		
		System.out.println("dataowner:"+dataowner);
		
		String otp1 = request.getParameter("otp1");
		String otp2 = request.getParameter("otp2");
		String otp3 = request.getParameter("otp3");
		String otp4 = request.getParameter("otp4");
		String otp5 = request.getParameter("otp5");
		String otp6 = request.getParameter("otp6");
		
		String mainOTP = otp1+otp2+otp3+otp4+otp5+otp6;
		
		System.out.println("mainOTP:"+mainOTP);

		
		System.out.println("otp:"+otp1+otp2+otp3+otp4+otp5+otp6);
		
		String otp = null;
		
		
	  Connection con = DataBaseConnection.createConnection();
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM dataownerregister WHERE otp='"+mainOTP+"' and email='"+dataowner+"'");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				 
				String getOTP = rs.getString("otp");

				System.out.println("success"+getOTP);
				if(mainOTP.equals(getOTP)){
					System.out.println("success");
					RequestDispatcher ddd = request.getRequestDispatcher("UploadFiles.jsp");
					
					ddd.forward(request, response);
				}
				
			}
			
//			System.out.println("errors");
			RequestDispatcher dd = request.getRequestDispatcher("VerifyOTP_For_UploadFile.jsp?success=0");
			
			dd.forward(request, response);
			
			System.out.println("otp=====:"+otp);
			
			
		} catch (SQLException e) { 
			
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			e.getMessage();
			
		}
	}

}
