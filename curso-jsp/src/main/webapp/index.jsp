<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 40%;
	right: 40%
}

h5 {
	position: absolute;
	top: 30%;
	left: 40%;
	right: 40%;
}

.msg {
	position: absolute;
	top: 60%;
	left: 40%;
	right: 40%;
	font-size: 15px;
	color: red;
}
</style>

</head>
<body>
	<h5>Bem vindo ao curso de JSP</h5>
	<form class="row g-3 needs-validation" novalidate action="ServletLogin"
		method="post">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<div class="col-md-6">
			<label class="form-label">Login</label> <input class="form-control"
				name="login" type="text" required>
			<div class="invalid-feedback">Login obrigatório</div>
		</div>
		<div class="col-md-6">
			<label class="form-label">Senha</label> <input class="form-control"
				name="senha" type="password" required>
			<div class="invalid-feedback">Senha obrigatória</div>
		</div>
		<div class="col-12">
			<input type="submit" value="Login" class="btn btn-primary">
		</div>
	</form>
	<h5 class="msg">${msg}</h5>

	<script type="text/javascript">
		//Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>