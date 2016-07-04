<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Clients" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Clients" name="page" />
	</jsp:include>
	<h1>Liste des clients</h1>
	<a class="btn btn-primary" href="new">Nouveau Client</a>
	<br>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<th>Id</th>
			<th>Dernière modification</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Email</th>
			<th>Téléphone</th>
			<th>Adresse</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="client" items="${listeClients}">
			<tr>
				<td>${client.id}</td>
				<td>${client.dateDerniereModification}</td>
				<td>${client.nom}</td>
				<td>${client.prenom}</td>
				<td>${client.email}</td>
				<td>${client.telephone}</td>
				<td>${client.adresse}</td>
				<td><a href="<c:url value="/clients/edit?email=${client.email}"/>" class="btn btn-primary">Éditer</a></td>
				<td>
					<form method="POST">
						<input type="hidden" name="email" value="${client.email}"> <input type="hidden" name="action" value="supprimer">
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>