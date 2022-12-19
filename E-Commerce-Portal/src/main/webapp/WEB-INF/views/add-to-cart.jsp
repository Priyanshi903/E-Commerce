<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add | ${product.product_name}</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="icon" type="image/x-icon"
	href="http://cdn.onlinewebfonts.com/svg/img_174666.png">
<script type="text/javascript" src="js/script.js"></script>
<style>
a {
	text-decoration: none;
	color: white;
	cursor: pointer;
}

.hero {
	position: absolute;
	width: 100%;
	height: 100vh;
	opacity: 90%;
	top: 0;
	left: 0;
	z-index: -100;
}

.hero-wrap {
	height: 80vh;
	display: flex;
	justify-content: space-between;
	align-items: center;
	overflow: hidden;
	margin-top: 1rem;
	border-radius: 12px;
}

.hero-text {
	font-size: 4rem;
	font-weight: 900;
	color: white;
	text-transform: capitalize;
	text-align: center;
	font-family: 'Josefin Sans', sans-serif;
}

.overlay {
	opacity: 65%;
	background-color: black;
}

body {
	background-color: hsl(0, 20%, 0%);
}

.nav-wrap {
	height: 10vh;
	background-color: black;
	position: sticky;
	top: 0;
	left: 0;
	z-index: 100;
}

nav {
	height: 100%;
	/* background-color: blue; */
}

.nav {
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.navlinks {
	display: flex;
	align-items: center;
	padding: 0 1rem 0 0.7rem;
}

.login {
	background-color: tomato;
	border-radius: 4px;
}

.nav-right {
	display: flex;
}

.btn {
	all: unset;
	background-color: turquoise;
	color: black;
	padding: 0.23rem 1rem;
	border-radius: 4px;
	box-sizing: border-box;
}

.search-box {
	padding: 0.2rem 0.7rem;
	border-radius: 4px;
	border: none;
	outline: none;
	box-sizing: border-box;
}

.product-info {
	flex-grow: 1;
	height: 100%;
	background-color: white;
	display: flex;
	align-items: center;
	justify-content: center;
}

.card {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
	border: none;
}

.price {
	display: flex;
	align-items: flex-end;
	font-weight: 900;
	font-size: 1.4rem;
}

.card-body {
	flex: 0 0;
	position: relative;
}

.link {
	color: black;
	padding: 1rem;
	width: 100%;
}

.add-btn {
	all: unset;
	background-color: tomato;;
	color: white;
	width: 100%;
	text-align: center;
	padding: 0.5rem 0;
	border-radius: 4px;
	margin-top: 0.2rem;
}

.input {
	width: 100%;
	margin-bottom: 0.3rem;
	border: none;
	padding: 0.3rem 0.5rem;
	border-radius: 5px;
	border: 1px solid rgb(143, 134, 139);
	outline: none;
}

.wish {
	background-color: turquoise;
	color: white;
	width: 100%;
	text-align: center;
	padding: 0.5rem 0;
	border-radius: 4px;
	margin-top: 0.2rem;
}

.card {
	position: relative;
	height: 85vh;
}

.card-img-top {
	height: 60%;
	width: 100%;
	object-fit: cover;
}

.error {
	color: red;
}
.rating{
 display: flex;
 align-items: center;
 margin-bottom: 0;
}
/* .container{overflow: hidden;} */
</style>
</head>
<body>
	<div class="nav-wrap">
		<nav class="container nav">
			<a href="/" class="navlinks"> <span class="material-icons pe-2"
				style="color: white;">home</span> <span>Home</span>
			</a>
			<div class="nav-right">
				<div class="form">
					<form action="/search">
						<input type="text" name="product_name" class="search-box"
							placeholder="Search"> <input type="submit" value="Search"
							class="btn">
					</form>
				</div>
				<c:if test="${name == null }">
					<a href="/login" class="navlinks login"> <span
						class="material-icons pe-1" style="color: white;">person</span> <span>Login</span>
					</a>
					<a href="/signup" class="navlinks"> <span>Signup</span>
					</a>
				</c:if>
				<c:if test="${name != null }">
					<a href="/wishlist" class="navlinks"><span
						class="material-icons pe-2" style="color: white">favorite</span><span>Favourite</span>
					</a>
					<a href="/view-cart" class="navlinks"><span
						class="material-icons pe-2" style="color: white">shopping_cart</span><span>Cart</span>
					</a>
					<a href="/logout" class="navlinks login"> <span>Logout</span>
					</a>
				</c:if>

			</div>
		</nav>
	</div>
	<div class="container">

		<div class=" hero overlay"></div>
		<div class="hero-wrap">
			<img src="${product.product_image}" class="" alt="phone image">
			<div class="product-info">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h5 class="card-title">${product.product_name}</h5>
						<p class="rating">
							<span class="ps-2">${product.product_rating}</span><span
								class="material-icons" style="color: orange;"> star </span>
						</p>
						<c:if test="${outOfStock != null }">
							<p class="error">${outOfStock }</p>
						</c:if>
						<p class="card-text">
						<div class="price">
							<span class="material-icons"> currency_rupee </span>${product.product_price}</div>
						${product.product_description}
						</p>
						<form:form action="/add" method="POST"
							modelAttribute="cartRequestDTO"
							onsubmit="return validateQuantity()" name="cart">
							<form:input path="product_id" value="${product.product_id}"
								type="hidden" />
							<label>ZipCode:</label>
							<br>
							<form:input path="zipcode" class="input" />
							<br>
							<label>Quantity:</label>
							<br>
							<form:input path="quantity" class="input" name="quantity" />
							<br>
							<input type="submit" value="Add to cart" class="add-btn" />

						</form:form>
						<div class="wish">
							<a href="/add-to-wishlist?id=${product.product_id}" class="link">Add
								to wishlist</a>
						</div>
					</div>
				</div>
			</div>

		</div>
		<footer>
			<div class="footer-text p-3">&copy; Copyright Reserved</div>
		</footer>
</body>
</html>