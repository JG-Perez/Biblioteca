<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Listado de socios morosos</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<c:choose>
				<c:when test="${not empty listaSociosMorosos}">
					<div class="mt-5" id="tabla">
						<h2 class="bg-info text-center mb-0">Listado de Socios Morosos</h2>
						<table class="table table-bordered table-hover shadow p-3 mb-5 bg-white rounded">
							<tr class="alert-info">
								<th scope="col">ID</th>
								<th scope="col">NOMBRE</th>
								<th scope="col">VER LIBROS</th>
							</tr>
							<c:forEach items="${listaSociosMorosos}" var="socio">
							<tr>
								<td>${socio.idSocio}</td>
								<td>${socio.nombre}</td>
								<td><a href="${pageContext.request.contextPath}/controllerAdmin?operacion=mostrarLibrosFueraPlazo&socio=${socio.idSocio}">Ver Libros</a></td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-success" role="alert">
						Ningun moroso a la vista
					</div>
				</c:otherwise>
			</c:choose>
		<c:if test="${librosFueraDePlazo != null && not empty librosFueraDePlazo}">
			<div class="mt-5" id="tabla">
				<h2 class="bg-info text-center mb-0">${librosFueraDePlazo.get(0).getNombreSocio()}</h2>
				<table class="table table-bordered table-hover shadow p-3 mb-5 bg-white rounded">
					<tr class="alert-info">
						<th scope="col">TITULO</th>
						<th scope="col">DIAS DE DEMORA</th>
					</tr>
					<c:forEach items="${librosFueraDePlazo}" var="libro">
					<tr>
						<td>${libro.titulo}</td>
						<td>${libro.diasDemora}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>