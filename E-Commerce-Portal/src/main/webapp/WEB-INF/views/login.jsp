<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="icon" type="image/x-icon" href="http://cdn.onlinewebfonts.com/svg/img_174666.png">
<style>
.hero {
	position: absolute;
	width: 100%;
	height: 100vh;
	opacity: 90%;
	top: 0;
	left: 0;
	z-index: -100;
}

.overlay {
	opacity: 65%;
	background-color: black;
}

.form-box {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
}

form {
	padding: 3rem 2rem;
	border-radius: 4px;
	background: rgba(255, 255, 255, 0.25);
	backdrop-filter: blur(6px);
	-webkit-backdrop-filter: blur(6px);
	border-radius: 10px;
	border: 1px solid rgba(255, 255, 255, 0.18);
	color: white;
	width: 30vw;
}

h2 {
	margin: 0;
	padding: 0.5rem 1rem 0.5rem 0;
}

.login-btn {
	all: unset;
	text-align: center;
	width: 100%;
	padding: 0.5rem 0;
	background-color: tomato;
	margin: 0.4rem 0;
	cursor: pointer;
	border-radius: 4px;
}

.input {
	all: unset;
	margin-bottom: 0.5rem;
	font-size: 1rem;
	padding: 0.3rem;
	border-radius: 4px;
	background-color: white;
	color: black;
	width: 100%;
	box-sizing: border-box;
}

.error {
	width: 100%;
	color: white;
	padding: 0.1rem 0.4rem;
}

a {
	text-decoration: none;
	color: white;
	margin-top: 1rem;
}

a:hover {
	color: tomato
}
</style>
</head>
<body>
	<img src="../../imgaes/homeimage.jpg" class="hero" alt="" srcset="">
	<div class=" hero overlay"></div>
	<div class="form-box">
		<form:form action="/login" modelAttribute="userCerdentials"
			method="POST">
			<label><h2>Login</h2></label>
			<br>
			<label>Email:</label>
			<br>
			<form:input path="email" class="input" required="true" />
			<br>
			<label>Password:</label>
			<br>
			<form:password path="password" class="input" required="true" />
			<br>
			<c:if test="${error != null }">
				<label class="error">${error }</label>
			</c:if>
			<input type="submit" value="Login" class="login-btn" />
			<a href="/signup">Signup?</a>
		</form:form>
	</div>

</body>
</html>