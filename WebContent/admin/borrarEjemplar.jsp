<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Borrar ejemplares libros</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<div class="col-4 mx-auto mt-5 p-3 shadow p-3 mb-5 bg-white rounded">
			<h2>Busqueda sencilla</h2>
			<form method="post"
				action="${pageContext.request.contextPath}/controllerAdmin">
				<div class="form-group">
					<input type="text" class="form-control" id="valorDeBusqueda"
						name="valorDeBusqueda" value="${valorDeBusqueda}" />
				</div>
				<div class="form-group mx-auto col-5">
					<select class="form-control" name="criterioDeBusqueda">
						<option value="autor"
							<c:if test="${requestScope.criterioDeBusqueda == null or requestScope.criterioDeBusqueda.equals('autor')}">
	  							<c:out value="selected"></c:out>
	  						</c:if>>
							<c:out value="autor"></c:out>
						</option>
						<option value="titulo"
							<c:if test="${requestScope.criterioDeBusqueda.equals('titulo')}">
  								<c:out value="selected"></c:out>
  							</c:if>>
							<c:out value="titulo"></c:out>
						</option>
						<option value="isbn"
							<c:if test="${request.Scope.criterioDeBusqueda.equals('isbn')}">
  								<c:out value="selected"></c:out>
  							</c:if>>
							<c:out value="isbn"></c:out>
						</option>
					</select>
				</div>
				<div class="mx-auto col-4">
					<input type="hidden" name="operacion" id="operacion"
						value="consultarLibro"> <input type="submit"
						class="btn btn-primary" name="submint" value="Buscar">
				</div>
			</form>
		</div>
		<c:if test="${librosBuscados != null}">
			<c:choose>
				<c:when test="${not empty librosBuscados}">
					<div class="mt-5" id="tabla">
						<h2 class="bg-info text-center mb-0">Listado de Libros</h2>
						<table
							class="table table-bordered table-hover shadow p-3 mb-5 bg-white rounded">
							<head>
							<tr class="alert-info">
								<th scope="col">TITULO</th>
								<th scope="col">AUTOR</th>
								<th scope="col">BORRAR</th>
							</tr>
							</head>
							<tbody>
								<c:forEach items="${librosBuscados}" var="libro">
									<tr>
										<td>${libro.titulo}</td>
										<td>${libro.nombreAutor}</td>
										<td><a href="#">Eliminar Ejemplares</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger" role="alert" id="diverror">
						<p>
							<strong><c:out value="Ningún ${criterioDeBusqueda} coincide con: ${valorDeBusqueda} "/></strong> <br>
						</p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
</body>
</html>