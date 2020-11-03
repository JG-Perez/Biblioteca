<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Obtener socio por nombre</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<div class="col-4 mx-auto mt-5 p-3 shadow p-3 mb-5 bg-white rounded">
			<h2>Modificar socio</h2>
			<form method="post"
				action="${pageContext.request.contextPath}/controllerAdmin">
				<div class="form-group">
					<label for="nombre">Introduzca parte del nombre del Socio a
						modificar</label> <input type="text" class="form-control"
						id="buscaSubcadena" name="buscaSubcadena" value="${subcadena}" />
				</div>
				<div class="form-group">
					<input type="hidden" id="operacion" name="operacion"
						value="verSocioAModificar">
				</div>
				<input type="submit" class="btn btn-primary" name="submint"
					value="Buscar">
			</form>
		</div>
		<c:if test="${socioAModificar != null}">
			<c:choose>
				<c:when test="${not empty socioAModificar}">
					<div class="mt-5" id="tabla">
						<h2 class="bg-info text-center mb-0">Listado de Socios</h2>
						<table
							class="table table-bordered table-hover shadow p-3 mb-5 bg-white rounded">
							<tr class="alert-info">
								<th scope="col">IDSOCIO</th>
								<th scope="col">NOMBRE</th>
								<th scope="col">DIRECCION</th>
								<th scope="col">EDITAR</th>
							</tr>
							<c:forEach items="${socioAModificar}" var="socio">
								<tr>
									<td>${socio.idSocio}</td>
									<td>${socio.nombre}</td>
									<td>${socio.direccion}</td>
									<td><a
										href="${pageContext.request.contextPath}/controllerAdmin?operacion=editarCamposSocio&socio=${socio.idSocio}">Editar</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger" role="alert" id="diverror">
					<p>
						<strong><c:out value="Ningún registro coincide con: ${subcadena} "/></strong> <br>
					</p>
				</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</body>
</html>