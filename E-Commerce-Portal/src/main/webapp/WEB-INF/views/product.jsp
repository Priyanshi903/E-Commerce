<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of ${product.product_name}</title>
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
	display: flex;
	justify-content: left;
	margin-top: 1rem;
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

form {
	padding-right: 1rem;
	height: 100%;
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
	padding: 0.2rem 0.5rem;
	border-radius: 4px;
	border: none;
	outline: none;
	box-sizing: border-box;
}

.price {
	display: flex;
	align-items: flex-end;
}

.card {
	position: relative;
	min-height: 90vh;
	transition: 500ms;
	margin-bottom: 2rem;
}

.card-img-top {
	height: 70%;
	width: 100%;
	object-fit: cover;
}

.card:hover {
	transform: scale(1.02);
}

.product {
	color: black;
}
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
			<c:if test="${productList.isEmpty()}">
				<h2 style="color: white">Product not found</h2>
			</c:if>
			<div class="d-flex flex-wrap justify-content-evenly">
			<c:forEach var="product" items="${productList}">
				<a href="/add-to-cart?id=${product.product_id}" class="product">
					<div class="card" style="width: 18rem;">
						<img src="${product.product_image}" class="card-img-top"
							alt="phone image">
						<div class="card-body">
							<h5 class="card-title">${product.product_name}</h5>
							<div class="price">
								<span class="material-icons"> currency_rupee </span>${product.product_price}</div>
							${product.product_description}
							<div class="price"> ${product.product_rating }<span
								class="material-icons" style="color:orange;"> star </span></div>
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
		</div>
</body>
</html>