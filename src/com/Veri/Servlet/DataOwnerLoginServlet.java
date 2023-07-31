package com.Veri.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Veri.Implementation.Implementation;
import com.Veri.Interface.Interface;



/**
 * Servlet implementation class DataOwnerLoginServlet
 */
@WebServlet("/DataOwnerLoginServlet")
public class DataOwnerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataOwnerLoginServlet() {
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
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Interface inter = new Implementation();
			int t = inter.DataOwnerLogin(email, password);
			
			if(t==1)
			{
				HttpSession session = request.getSession();
				session.setAttribute("DataOwner", email);
				response.sendRedirect("DataOwnerHome.jsp");
			}
			else
			{
				response.sendRedirect("error.jsp");
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

		
	}


