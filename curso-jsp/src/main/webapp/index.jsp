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

<!--Bootsrap 4 CDN-->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<!--Fontawesome CDN-->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">

<!--Custom styles-->
<link rel="stylesheet" type="text/css" href="css/login.css">
<title>Curso JSP</title>

  <style type="text/css">


h5 {
	position: absolute;
	top: 30%;
	left: 40%;
	right: 40%;
	align-self: center;
} 

.msg {
	position: absolute;
	top: 60%;
	left: 40%;
	right: 40%;
	font-size: 15px;
	color: white;
}
</style>

</head>
<body>
	<!--  <h5>Bem vindo ao curso de JSP</h5>
	<form class="row g-3" action="ServletLogin" method="post">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
			<div class="col-md-6">
				<label class="form-label">Login</label>
				<input class="form-control" name="login" type="text">
			</div>
			<div class="col-md-6">
				<label class="form-label">Senha</label>
				<input class="form-control" name="senha" type="password">
			</div>
			<div class="col-12">
				<input type="submit" value="Login" class="btn btn-primary">
			</div>
	</form>
	<h5 class="msg">${msg}</h5>
	-->

	<div class="container">
		<div class="d-flex justify-content-center h-100">
			<div class="card">
				<div class="card-header">
					<h3>Sign In</h3>
					<div class="d-flex justify-content-end social_icon">
						<span><i class="fab fa-facebook-square"></i></span> <span><i
							class="fab fa-google-plus-square"></i></span> <span><i
							class="fab fa-twitter-square"></i></span>
					</div>
				</div>
				<div class="card-body">
					<form class="row g-3" action="ServletLogin" method="post">
						<input type="hidden" value="<%=request.getParameter("url")%>"
							name="url">
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" class="form-control" name="login"
								placeholder="Login">

						</div>
						<div class="input-group form-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" class="form-control" name="senha"
								placeholder="Senha">
						</div>
						<div class="form-group">
							<input type="submit" value="Login"
								class="btn float-right login_btn">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<h5 class="msg">${msg}</h5>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>