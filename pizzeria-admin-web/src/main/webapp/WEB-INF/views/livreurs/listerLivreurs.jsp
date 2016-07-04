<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Pizza" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Livreur" name="page" />
	</jsp:include>
	<h1>Liste des livreurs</h1>
	<a href="<c:url value="/livreurs/newlivreur"/>" class="btn btn-primary">Nouveau livreur</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Id / Code</td>
			<td>Nom Prénom</td>
			<td>Actions</td>
		</tr>

		<c:forEach var="livreur" items="${listeLivreurs}">
		<tr>
			<td>
				${livreur.id} / ${livreur.code}
			</td>
			<td>
				${livreur.nom} ${livreur.prenom}
			</td>
			<td>
				<a href="<c:url value="/livreurs/edit?id=${livreur.id}"/>" class="btn btn-primary">Éditer</a>
				<form method="POST">
					<input type="hidden" name="id" value="${livreur.id}">
					<input type="hidden" name="action" value="supprimer">
					<button type="submit" class="btn btn-danger">Supprimer</button>
				</form>
			</td>
			
			<%--
			<!-- Ancienne version qui ne rentrait pas dans la structure de tableau ; réactiver si besoin, supprimer sinon. -->
			<td>
				<div class="row">
					<div class="col-md-6">
						 ${livreur.id} / ${livreur.code} 
						<br> ${livreur.nom} ${livreur.prenom}
						<br>
					</div>
					<div class="col-md-6">
						<a href="<c:url value="/livreurs/edit?id=${livreur.id}"/>" class="btn btn-primary">Éditer</a>
						<br>
						<form method="POST">
							<input type="hidden" name="id" value="${livreur.id}">
							<input type="hidden" name="action" value="supprimer">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form>
					</div>
				</div>
			</td>
			--%>
			
		</tr>
		</c:forEach>
	</table>
</body>
</html>