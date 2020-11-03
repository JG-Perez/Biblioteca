<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Listado de socios</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<c:if test="${not empty listadoSocios}">
			<div class="mt-5" id="tabla">
				<h2 class="bg-info text-center mb-0">Listado de Socios</h2>
				<table class="table table-bordered table-hover shadow p-3 mb-5 bg-white rounded">
					<tr class="alert-info">
						<th scope="col">ID</th>
						<th scope="col">EMAIL</th>
						<th scope="col">NOMBRE</th>
						<th scope="col">DIRECCION</th>
					</tr>
					<c:forEach items="${listadoSocios}" var="socio">
						<tr>
							<td>${socio.idSocio}</td>
							<td>${socio.email}</td>
							<td>${socio.nombre}</td>
							<td>${socio.direccion}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>