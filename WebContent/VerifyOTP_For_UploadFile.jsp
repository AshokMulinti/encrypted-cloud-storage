<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>

<style>
body {
	  background: linear-gradient(to right, #667db6, #0082c8, #0082c8, #667db6); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}

.height-100 {
	height: 100vh
}

.card {
	width: 400px;
	border: none;
	height: 300px;
	box-shadow: 0px 5px 20px 0px #d2dae3;
	z-index: 1;
	display: flex;
	justify-content: center;
	align-items: center
}

.card h6 {
	color: red;
	font-size: 20px
}

.inputs input {
	width: 40px;
	height: 40px
}

input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	-moz-appearance: none; 
	appearance: none;
	margin: 0
}

.card-2 {
	background-color: #fff;
	padding: 10px;
	width: 350px;
	height: 100px;
	bottom: -50px;
	left: 20px;
	position: absolute;
	border-radius: 5px
}

.card-2 .content {
	margin-top: 50px
}

.card-2 .content a {
	color: red
}

.form-control:focus {
	box-shadow: none;
	border: 2px solid red
}

.validate {
	border-radius: 20px;
	height: 40px;
	background-color: red;
	border: 1px solid red;
	width: 140px
}
</style>

<body>

<%

String DataOwner=session.getAttribute("DataOwner").toString();
session.setAttribute("DataOwner", DataOwner);
%>

	<div
		class="container height-100 d-flex justify-content-center align-items-center">
		<div class="position-relative">
			<c:if   test="${param.success==0}">
		<div class="alert alert-danger alert-dismissible fade show"
			role="alert">
			<strong>OOPS!</strong> OTP is Not Validate !! Check OTP <a
				href="Home.jsp"></a>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close">X</button>
		</div>
	</c:if> 
		<form action="VerifyOTPServlet" method="post">
		<input type="hidden" name="dataowner" value=<%=DataOwner%>>
			<div class="card p-2 text-center">
				<h6>
					Please enter the one time password <br> to verify your account
					for UploadFile
				</h6>
				<div>

					<span>A code has been sent to</span> <small>If CSP Has been
						Accept your Credientials</small>
						
				</div> 
				<div id="otp"
					class="inputs d-flex flex-row justify-content-center mt-2">
					<input class="m-2 text-center form-control rounded" type="text"
						id="first" maxlength="1" name="otp1" /> 
						<input
						class="m-2 text-center form-control rounded" type="text"
						id="second" maxlength="1" name="otp2" /> 
						<input
						class="m-2 text-center form-control rounded" type="text"
						id="third" maxlength="1" name="otp3" />
						 <input
						class="m-2 text-center form-control rounded" type="text"
						id="fourth" maxlength="1" name="otp4" /> 
						<input
						class="m-2 text-center form-control rounded" type="text"
						id="fifth" maxlength="1" name="otp5" />
						 <input
						class="m-2 text-center form-control rounded" type="text"
						id="sixth" maxlength="1" name="otp6" />
				</div>
				<div class="mt-4">
					<button class="btn btn-danger px-4 validate" >Validate</button>
				</div>
			</div>
			</form>
			<div class="card-2">
				<div
					class="content d-flex justify-content-center align-items-center">
					<span>Didn't get the code</span> <a href="#"
						class="text-decoration-none ms-3"></a>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function(event) {

			function OTPInput() {
				const inputs = document.querySelectorAll('#otp > *[id]');
				for (let i = 0; i < inputs.length; i++) {
					inputs[i].addEventListener('keydown',
							function(event) {
								if (event.key === "Backspace") {
									inputs[i].value = '';
									if (i !== 0)
										inputs[i - 1].focus();
								} else {
									if (i === inputs.length - 1
											&& inputs[i].value !== '') {
										return true;
									} else if (event.keyCode > 47
											&& event.keyCode < 58) {
										inputs[i].value = event.key;
										if (i !== inputs.length - 1)
											inputs[i + 1].focus();
										event.preventDefault();
									} else if (event.keyCode > 64
											&& event.keyCode < 91) {
										inputs[i].value = String
												.fromCharCode(event.keyCode);
										if (i !== inputs.length - 1)
											inputs[i + 1].focus();
										event.preventDefault();
									}
								}
							});
				}
			}
			OTPInput();

		});
		
		if(test=${param.success==0}){
			
			console.log("Wrong OTP");
			document.body.style.background = "red";
			swal("Oops!", "Check Your OTP!", "error");
			
			
		}
		
		
		
	</script>

</body>
</html>