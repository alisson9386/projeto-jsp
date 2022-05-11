<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	String nome = request.getParameter("nome");
	out.print("Nome: "+nome);
	
	String idade = request.getParameter("idade");
	out.print(" Idade: " + idade);
%>

</body>
</html>