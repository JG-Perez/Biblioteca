<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:directive.include file="../includes/includefile.jspf" />
<title>Alta autor</title>
</head>
<body>
	<div class="container">
		<div id="menu">
			<jsp:directive.include file="../WEB-INF/menu.jspf" />
		</div>
		<div class="mt-4" id="mensaje">
			<c:if test="${requestScope.error != null}">
				<div class="alert alert-danger" role="alert" id="diverror">
					<p>
						<strong><c:out value="Error" /></strong> <br>
						<c:out value="${requestScope.error}" />
					</p>
				</div>
			</c:if>
			<c:if test="${requestScope.confirmarOperacion != null}">
				<div class="alert alert-success" role="alert" id="divconfirmacion">
					<p>
						<strong><c:out value="Mensaje" /></strong> <br>
						<c:out value="${requestScope.confirmarOperacion}" />
					</p>
				</div>
			</c:if>
		</div>
		<div class="col-4 mx-auto mt-5 p-3 shadow p-3 mb-5 bg-white rounded" id="formAutor">
			<h2>Nuevo Autor</h2>
			<form name="frmAutor" method="post" action="${pageContext.request.contextPath}/controllerAdmin">
				<div class="form-group">
					<label for="nombre">Nombre</label>
					<input type="text" class="form-control" id="nombre" name="nombre" value="${nuevoAutor.nombre}"/>
				</div>
				<div class="form-group">
					<label for="fechaNacimento">Fecha Nacimiento</label>
					<input type="text" class="form-control" id="fechaNacimiento" name="fechaNacimiento" placeholder="dd-MM-yyyy" value="${fechaErronea}"/>
				</div>
				<input type="hidden" name="operacion" id="operacion" value="insertaAutor">
				<input type="submit" class="btn btn-primary" name="submint" value="Guardar">
			</form>
		</div>
	</div>
</body>
</html>