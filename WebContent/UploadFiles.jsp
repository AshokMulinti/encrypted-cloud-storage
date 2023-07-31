<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<title>Insert title here</title>

<style>

@import url(https://fonts.googleapis.com/css?family=Open+Sans:700,300);

body{

/*  background: linear-gradient(to right, #000046, #1cb5e0);  W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  background: linear-gradient(to right, #667db6, #0082c8, #0082c8, #667db6); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
margin: 0;
padding: 0;
}

.frame {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 350px;
	height: 397px;
margin-top: -240px;
	margin-left: -200px;
	border-radius: 2px;
	box-shadow: 4px 8px 16px 0 rgba(0, 0, 0, 0.1);
	overflow: hidden;
	 background: linear-gradient(to right, #000046, #1cb5e0);
 	color: #333;
	font-family: "Open Sans", Helvetica, sans-serif;
	border-radius: 10px;
  transition: width 2s, height 2s, transform 2s;
  
	
}

.frame:HOVER{

  background: linear-gradient(to right, #56ccf2, #2f80ed); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	width: 400px;
	height: 400px;
	
	  
}


.center {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 300px;
	height: 260px;
	border-radius: 3px;
	box-shadow: 8px 10px 15px 0 rgba(0, 0, 0, 0.2);
	background: #fff;
	display: flex;
	align-items: center;
	justify-content: space-evenly;
	flex-direction: column;
	
}

.title {
	width: 100%;
	height: 50px;
	border-bottom: 1px solid #999;
	text-align: center;
}

h1 {
	font-size: 16px;
	font-weight: 300;
	color: #666;
}

.dropzone {
	width: 100px;
	height: 80px;
	border: 1px dashed #999;
	border-radius: 3px;
	text-align: center;
}

.upload-icon {
	margin: 25px 2px 2px 2px;
}

.upload-input {
	position: relative;
	top: -62px;
	left: 0;
	width: 100%;
	height: 100%;
	opacity: 0;
}

.btn {
	display: block;
	width: 140px;
	height: 40px;
  	background: linear-gradient(to right, #000046, #1cb5e0); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	color: #fff;
	border-radius: 3px;
	border: 0;
	box-shadow: 0 3px 0 0 #2f80ed;
	transition: all 0.3s ease-in-out;
	font-size: 14px;
}

.btn:hover {
  background: linear-gradient(to right, #56ccf2, #2f80ed); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  	box-shadow: 0 3px 0 0 #1cb5e0;
  	cursor: pointer;
}

.zain{

width: 100vw;
height: 100vh;
}

</style>

</head>
<body>

<%

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

%>



<img class="zain" alt="" src="./assets/imgs/integritybg3.jpg">

<c:if   test="${param.success==1}">

	
	</c:if>


<form action="UploadServlet" method="post" enctype="multipart/form-data">

<div class="frame">
<a class="btn" href="DataOwnerHome.jsp">BACK</a>
	<div class="center">
		<div class="title">
			<h1>Drop file to upload</h1>
		</div>

		<div class="dropzone">
			<img src="http://100dayscss.com/codepen/upload.svg" class="upload-icon" />
			 <input type="hidden" name="<%=secret%>" value="<%=secret%>" readonly>
			<input type="file" class="upload-input" name="filename" />
		</div>

		<button type="submit" class="btn" name="uploadbutton">Upload file</button>

	</div>
	
</div>
</form>
<!-- original pen: https://codepen.io/roydigerhund/pen/ZQdbeN  -->

<!-- NO JS ADDED YET -->

<script>

if(test=${param.success==1}){
			
			console.log("File Uploaded");
			swal("Success!", "File Uploaded!", "success");
			
			
		}

</script>

</body>
</html>