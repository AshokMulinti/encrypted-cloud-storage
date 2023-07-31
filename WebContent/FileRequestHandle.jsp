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

String requestby = request.getParameter("frequest");

System.out.println("filename::"+filename);

String filesize= "";
String key = "";
String encrypt = "";

try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from uploadfile where filename = '"+filename+"'");
	while(rs.next()) {
		
		filesize = rs.getString("size");
		key = rs.getString("key");
		encrypt = rs.getString("encrypt");
		
	}
}catch(Exception e) {
	
}

int ans=0;
String filekey = "autoupdate";
String acceptby = "autoupdate";
try {
	Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/veridedup","root","root");
	PreparedStatement ps = con.prepareStatement("INSERT INTO filerequest(filename,filesize,requestby,filekey,status,acceptby,encrypt) VALUES(?,?,?,?,?,?,?)");
	
	ps.setString(1, filename);
	ps.setString(2, filesize);
	ps.setString(3, requestby);
	ps.setString(4, filekey);
	ps.setString(5, "Waiting");
	ps.setString(6, acceptby);

	ps.setString(7, encrypt);
	ans = ps.executeUpdate();
} catch(Exception e) {
	e.printStackTrace();
}
if(ans==1) {
	response.sendRedirect("SendRequest.jsp?success=1");
} else {
	response.sendRedirect("Error.jsp");
}




%>

</body>
</html>