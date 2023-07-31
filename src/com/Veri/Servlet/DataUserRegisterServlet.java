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
 * Servlet implementation class DataUserRegisterServlet
 */
@WebServlet("/DataUserRegisterServlet")
public class DataUserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataUserRegisterServlet() {
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
			Users datauserreg = new Users();
			datauserreg.setName(name);
			datauserreg.setEmail(email);
			datauserreg.setPassword(password);
			
			Interface inter = new Implementation();
			
			int t = inter.DataUserRegister(datauserreg);
			
			if(t != 0)
			{
				response.sendRedirect("DataUserRegister.jsp");
			}
			else
			{
				response.sendRedirect("error.jsp");
			}
			
		}
		
	}

}
