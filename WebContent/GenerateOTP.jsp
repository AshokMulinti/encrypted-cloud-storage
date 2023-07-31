<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

	String	email = request.getParameter("Gemail");
	

		Random r = new Random();
		String key="ABCDEF123456789";
		char c=key.charAt(r.nextInt(key.length()));
		char c1=key.charAt(r.nextInt(key.length()));
		char c2=key.charAt(r.nextInt(key.length()));
		char c3=key.charAt(r.nextInt(key.length()));
		char c4=key.charAt(r.nextInt(key.length()));
		char c5=key.charAt(r.nextInt(key.length()));
		String secret = c+""+c1+""+c2+""+c3+""+c4+""+c5;
		System.out.println("The Value of secret is " +secret);
		
		
		int result=0;
		

		


		try {
			Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
			PreparedStatement ps = con.prepareStatement("update dataownerregister set otp='"+secret+"' where email='"+email+"'");
			

			result = ps.executeUpdate();
			
			if(result !=0){
				
				response.sendRedirect("DataHolders.jsp?success=1");
			}
			
			System.out.println("ans::"+result);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("==========ERROR==========");
		}



%>

</body>
</html>