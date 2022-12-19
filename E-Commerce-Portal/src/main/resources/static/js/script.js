function validate() {

	var email = document.register.email.value;

	var phone = document.register.phoneNumber.value;
	
	var phonePattern=/^[0-9]{10}$/;
	var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	
	if (phonePattern.test(phone)==false) {

		console.log(phone);
		alert("Invalid Mobile No");
		
		return false;
	}
	
	if (emailPattern.test(email) == false) {
		alert("Not a valid email");
		return false;
	}

	return true;
}
function validateQuantity() {
	
	var qunatity = document.cart.quantity.value;
	
	if(qunatity > 0)
		return true;
	
	alert("Quantity should be greater than Zero");

	return false;
	
}