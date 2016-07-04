<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Livreurs" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Livreurs" name="page" />
	</jsp:include>
	<h1>Liste des livreurs</h1>
	<a class="btn btn-primary" href="new">Nouveau livreur</a>
	<br>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="livreur" items="${listeLivreurs}">
			<tr>
				<td>${livreur.id}</td>
				<td>${livreur.code}</td>
				<td>${livreur.nom}</td>
				<td>${livreur.prenom}</td>
				<td><a href="<c:url value="/livreurs/edit?id=${livreur.id}"/>" class="btn btn-primary">Éditer</a></td>
				<td>
					<form method="POST">
						<input type="hidden" name="id" value="${livreur.id}"> <input type="hidden" name="action" value="supprimer">
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>