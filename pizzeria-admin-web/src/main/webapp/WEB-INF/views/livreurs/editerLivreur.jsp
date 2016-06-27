<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Editer Livreur" name="title" />
</jsp:include>
<body class="container">
	<h1><c:out value="${ (livreur != null && livreur.id != null) ? 'Editer un livreur' : 'Créer un livreur' }"/></h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>



		<form method="POST">

			<c:if test="${livreur.id != null}">
				<div class="form-group">
					<label for="id">Id</label> <input type="text" class="form-control"
						id="id" name="id" value="${livreur.id}" readonly>
				</div>
			</c:if>
			<div class="form-group">
				<label for="nom">Nom</label> <input type="text" class="form-control"
					id="nom" name="nom" value="${livreur.nom}"required>
			</div>
			<div class="form-group">
				<label for="prenom">Prénom</label> <input type="text"
					class="form-control" name="prenom" id="prenom"
					value="${livreur.prenom}"required>
			</div>

			<button type="submit" class="btn btn-primary">Valider</button>
		</form>

</body>
</html>