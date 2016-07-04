<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Utilisateurs" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Utilisateurs" name="page" />
	</jsp:include>
	<h1>Liste des utilisateurs</h1>
	<a class="btn btn-primary" href="new">Nouvel Utilisateur</a>
	<br>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<th>Id</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Email</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="utilisateur" items="${listeUtilisateurs}">
			<tr>
				<td>${utilisateur.id}</td>
				<td>${utilisateur.nom}</td>
				<td>${utilisateur.prenom}</td>
				<td>${utilisateur.email}</td>
				<td><a href="<c:url value="/utilisateurs/edit?email=${utilisateur.email}"/>" class="btn btn-primary">Éditer</a>
				<td>
					<form method="POST">
						<input type="hidden" name="email" value="${utilisateur.email}"> <input type="hidden" name="action" value="supprimer">
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>