package com.Veri.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Veri.Implementation.Implementation;
import com.Veri.Interface.Interface;
import com.Veri.bean.Users;

/**
 * Servlet implementation class DataOwnerRegister
 */
@WebServlet("/DataOwnerRegisterServlet")
public class DataOwnerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataOwnerRegisterServlet() {
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
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(password.equals(confirmPassword))
		{  
			Users dataOwnerReg = new Users();
			
			dataOwnerReg.setName(name);
			dataOwnerReg.setEmail(email);
			dataOwnerReg.setPassword(password);
			
			Interface inter = new Implementation();
			int t = inter.DataOwnerRegister(dataOwnerReg);
			
			if(t != 0)
			{
				response.sendRedirect("DataOwnerRegister.jsp");
			}
			else
			{
				response.sendRedirect("error.jsp");
			}
		}
		
	}

}
