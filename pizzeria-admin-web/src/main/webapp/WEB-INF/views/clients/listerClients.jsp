<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Clients" name="title" />
</jsp:include>

<body class="container">
	<h1>Liste des clients</h1>
	<a class="btn btn-primary" href="new">Nouveau Client</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Id</td>
			<td>Dernière modif</td>
			<td>Nom</td>
			<td>Prenom</td>
			<td>Email</td>
			<td>Telephone</td>
			<td>Adresse</td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="client" items="${listeClients}">
		<tr>
			<td>
				${client.id}
			</td>
			<td>
				${client.derniereModification}
			</td>
			<td>
				${client.nom}
			</td>
			<td>
				${client.prenom}
			</td>
			<td>
				${client.email}
			</td>
			<td>
				${client.telephone}
			</td>
			<td>
				 ${client.adresse}
			</td>
			<td>
				<a href="<c:url value="/clients/edit?email=${client.email}"/>" class="btn btn-primary">Editer</a>
			</td>
			<td>
				<form method="POST">
					<input type="hidden" name="email" value="${client.email}">
					<input type="hidden" name="action" value="supprimer">
					<button type="submit" class="btn btn-danger">Supprimer</button>
				</form>
			</td>
		</tr>
		</c:forEach>

	

	</table>
</body>
</html>