<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Modificar socios</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<c:if test="${requestScope.confrimarOperacion != null}">
			<div class="mt-4 alert alert-success" role="alert"
				id="divconfirmacion">
				<p>
					<strong><c:out value="Proceso completado" /></strong> <br>
					<c:out value="${requestScope.confrimarOperacion}" />
				</p>
			</div>
		</c:if>
		<div class="col-4 mx-auto mt-5 p-3 shadow p-3 mb-5 bg-white rounded">
			<h2>Modificar los datos</h2>
			<form method="post"
				action="${pageContext.request.contextPath}/controllerAdmin">
				<div class="form-group">
					<label for="nombre">Nombre</label> <input type="hidden"
						id="idSocio" name="idSocio" value="${socioAModificar.idSocio}">
					<input type="text" class="form-control" id="nombre" name="nombre"
						value="${socioAModificar.nombre}" />
				</div>
				<div class="form-group">
					<label for="nombre">Direccion</label> <input type="text"
						class="form-control" id="direccion" name="direccion"
						value="${socioAModificar.direccion}" /> <input type="hidden"
						id="operacion" name="operacion" value="actualizarSocio">
				</div>
				<input type="submit" class="btn btn-primary" name="submint"
					value="Editar">
			</form>
		</div>
	</div>
</body>
</html>