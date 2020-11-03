<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Credenciales</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<div class="alert alert-danger mt-3" role="alert">
			<div class="font-weight-bold"><c:out value="Error"></c:out></div>
			<c:out value="Usuario o password incorrectos"></c:out>
		</div>
		<div id="formLogin"
			class="col-4 mx-auto mt-5 p-3 shadow p-3 mb-5 bg-white rounded">
			<form action="j_security_check" method="POST">
				<h2>Introduzca sus datos de usuario</h2>
				<div class="form-group">
					<label for="j_username">Usuario:</label> <input
						class="form-control" type="text" id="j_username" name="j_username" />
				</div>
				<div class="form-group">
					<label for="j_password">Clave:</label> <input class="form-control"
						type="text" id="j_password" name="j_password" />
				</div>
				<input type="submit" class="btn btn-primary" name="Submit"
					value="Acceder"> <a href="/Biblioteca/altasocio.jsp">Registrese</a>
			</form>
		</div>
	</div>
</body>
</html>