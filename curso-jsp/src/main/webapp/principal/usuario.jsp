<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>
			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="navbar-main-menu.jsp"></jsp:include>
					<div class="pcoded-content">
						<jsp:include page="page-header.jsp"></jsp:include>
						<div class="pcoded-inner-content">
							<div class="main-body">
								<div class="page-wrapper">
									<div class="page-body">
										<!-- Ultimo elemento antes do corpo -->
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cadastro de usuário</h4>
														<form class="form-material" enctype="multipart/form-data" method="post"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="idUsuario" id="idUsuario"
																	class="form-control" value="${modelLogin.id}">
																<span class="form-bar"></span> <label
																	class="float-label">ID</label>
															</div>
															<div class="form-group form-default form-static-label input-group mb-4">
																<div class="input-group-prepend">
																<c:if test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
																	<img alt="Imagem Usuário" id="fotoembase64" src="${modelLogin.fotoUser}" width="70px">
																</c:if>
																<c:if test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
																	<img alt="Imagem Usuário" id="fotoembase64" src="assets\images\avatar-@user.png" width="70px">
																</c:if>
																</div>
																<br>
																<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizaImg('fotoembase64','fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px">
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.email}">
																<span class="form-bar"></span> <label
																	class="float-label">Email:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<select class="form-control" aria-label="Default select example" name="perfil">
																  <option disabled="disabled">Selecione o perfil</option>
																  
																  <option value="ADMIN" <%
																  ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																  
																  if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
																 	out.print(" ");
																 	out.print("selected=\"selected\"");
																 	out.print(" ");
																  }
																	  %>>Admin</option>
																  
																  <option value="SECRETARIO" <%

																	modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																  
																  	if(modelLogin != null && modelLogin.getPerfil().equals("SECRETARIO")){
																 	out.print(" ");
																 	out.print("selected=\"selected\"");
																 	out.print(" ");
																  }
																	  %>>Secretário (a)</option>
																  
																  <option value="AUXILIAR" <%

																	modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																  
																 	if(modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
																 	out.print(" ");
																 	out.print("selected=\"selected\"");
																 	out.print(" ");
																  }
																	  %>>Auxiliar</option>
																</select>
																<span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.senha}">
																<span class="form-bar"></span> <label
																	class="float-label">Senha:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" value="MASCULINO" <%
																modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																
																if(modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
																 	out.print(" ");
																 	out.print("checked=\"checked\"");
																 	out.print(" ");
																  }
																
																%>> Masculino     </>
																<br>
																<input type="radio" name="sexo" value="FEMININO" <%
																modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																
																if(modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
																 	out.print(" ");
																 	out.print("checked=\"checked\"");
																 	out.print(" ");
																  }
																
																%>> Feminino</>
															</div>
															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-danger waves-effect waves-light"
																onclick="deleteAjax();">Excluir</button>
															<button type="button" class="btn btn-info"
																data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar
																usuarios</button>
														</form>
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span> <br>
										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaUsuariosCarregadosTela">
												<thead>
													<tr>
														<th scope="col">Nome</th>
														<th scope="col">Login</th>
														<th scope="col">Email</th>
														<th scope="col">Perfil</th>
														<th scope="col">Sexo</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${modelLogins}" var="ml">
														<tr>
														<td><c:out value="${ml.nome}"></c:out></td>
														<td><c:out value="${ml.login}"></c:out></td>
														<td><c:out value="${ml.email}"></c:out></td>
														<td><c:out value="${ml.perfil}"></c:out></td>
														<td><c:out value="${ml.sexo}"></c:out></td>
														<td><a class="btn btn-info" href="<%=request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Editar</a></td>
														<td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Required Jquery -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
	<!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Buscar Usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-info" type="button"
								onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaUsuarios">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Selecionar</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<br> <span id="totalResultados"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		function visualizaImg(fotoembase64, fileFoto) {
			
			var preview = document.getElementById(fotoembase64); //campo IMG HTML
			var fileUser = document.getElementById(fileFoto).files[0];
			var reader = new FileReader();
			
			reader.onloadend = function () {
				preview.src = reader.result;
			};
			
			if(fileUser){
				reader.readAsDataURL(fileUser);
			}else{
				preview.src = '';
			}
			
			
		}
	
	
		function verEditar(id) {
			var urlAction = document.getElementById('formUser').action;

			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;

		}

		function buscarUsuario() {
			var nomeBusca = document.getElementById("nomeBusca").value;
			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {

				var urlAction = document.getElementById('formUser').action;

				$
						.ajax(
								{
									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=buscarUserAjax',
									success : function(response) {
										var json = JSON.parse(response);

										$('#tabelaUsuarios > tbody > tr')
												.remove();
										for (var p = 0; p < json.length; p++) {
											$('#tabelaUsuarios > tbody')
													.append(
															'<tr> <td>'
																	+ json[p].id
																	+ '</td> <td>'
																	+ json[p].nome
																	+ '</td> <td> <button onclick="verEditar('
																	+ json[p].id
																	+ ')" type="button" class="btn btn-dark"><i class="bi bi-check-circle"></i></button> </td> </tr>');

										}

										document
												.getElementById('totalResultados').textContent = 'Resultados: '
												+ json.length;
									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao buscar usuario por nome: '
											+ xhr.responseText);
								});

			}
		}

		function deleteAjax() {
			if (confirm('Deseja realmente excluir os dados?')) {

				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarajax',
					success : function(response) {
						limparForm();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usuario por id: '
									+ xhr.responseText);
						});

			}
		}

		function criarDelete() {

			if (confirm('Deseja realmente excluir os dados?')) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}
		}

		function limparForm() {

			var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
	</script>

</body>

</html>
