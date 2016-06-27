<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Editer Ingredient" name="title" />
</jsp:include>
<body class="container">

	<h1><c:out value="${ titre }"/></h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>


	<c:if test="${ingredient != null}">
		<form method="POST">

			<c:if test="${ingredient.id != null}">
				<div class="form-group">
					<label for="id">Id</label> <input type="text" class="form-control"
						id="id" name="id" value="${ingredient.id}" readonly>
				</div>
			</c:if>
			
			<c:if test="${ingredient.id != null}">
				<div class="form-group">
				<label for="code">Code</label> <input type="text" class="form-control"
					id="code" name="code" value="${ingredient.code}" readonly required>
				</div>
			</c:if>
			<c:if test="${ingredient.id == null}">
				<div class="form-group">
				<label for="code">Code</label> <input type="text" class="form-control"
					id="code" name="code" value="${ingredient.code}" required>
				</div>
			</c:if>
			<div class="form-group">
				<label for="name">Nom</label> <input type="text" class="form-control"
					id="name" name="name" value="${ingredient.name}" required>
			</div>
			<input name="Referer" type="texte" value="${Referer}" hidden>
			<button type="submit" class="btn btn-primary">Valider</button>
		</form>
	</c:if>

</body>
</html>