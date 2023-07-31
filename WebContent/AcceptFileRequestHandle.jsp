<%@page import="com.mysql.cj.x.protobuf.MysqlxConnection.Close"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
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


System.out.println(filename);

String filekey="";
String status="";
String acceptby="";
String decrypt="";


try
{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
    Statement st=con.createStatement();
    ResultSet rs=st.executeQuery("select * from uploadfile where filename='"+filename+"'");

   while(rs.next())
    {
  			filekey=rs.getString("key");
  			decrypt = rs.getString("decrypt");
  		
  		
  	}
   }
catch(Exception e){
    out.print(e.getMessage());
}

int result = 0;

String AA = "AA";

try
{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
    Statement st=con.createStatement();
	PreparedStatement ps=con.prepareStatement("update filerequest set filekey='"+filekey+"',acceptby='"+AA+"',encrypt='"+decrypt+"',status='Allowed' where filename='"+filename+"'");

	result=ps.executeUpdate();
	
	if(result != 0){
		
		System.out.println("Value of Result ::"+result);
		response.sendRedirect("ViewUploadedFilesByAA.jsp?success=1");
	}

  
   }
catch(Exception e){
    out.print(e.getMessage());
}





%>

</body>
</html>