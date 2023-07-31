<%@page import="com.Veri.crypto.AES"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

String filename = request.getParameter("ffilename");

String encrypt = request.getParameter("Encrypt");

System.out.println("encrypt::"+encrypt);
System.out.println("filename::"+filename);

AES aes = new AES();

String reencrypt = aes.encrypt99(encrypt);

System.out.print("encrypt:"+reencrypt);

int result = 0;

try
{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
    Statement st=con.createStatement();
	PreparedStatement ps=con.prepareStatement("update uploadfile set Reencrypt='"+reencrypt+"' where filename='"+filename+"' ");

	result=ps.executeUpdate();
	
	if(result != 0){
		
		System.out.println("Value of Result ::"+result);
		response.sendRedirect("PRE.jsp?success=1");
	}

  
   }
catch(Exception e){
    out.print(e.getMessage());
}


%>

</body>
</html>