<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Listado de Autores</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<c:if test="${not empty listadoAutores}">
			<div class="mt-5" id="tabla">
				<h2 class="bg-info text-center mb-0">Listado de Autores</h2>
				<table class="table table-bordered table-hover shadow p-3 mb-5 bg-white rounded">
					<head>
						<tr class="alert-info">
							<th scope="col">CODIGO</th>
							<th scope="col">NOMBRE</th>
							<th scope="col">FECHA NACIMIENTO</th>
						</tr>
					</head>
					<tbody>
						<c:forEach items="${listadoAutores}" var="autor">
							<tr>
								<td>${autor.idAutor}</td>
								<td>${autor.nombre}</td>
								<td>${autor.fechaNacimientoFormateada}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>