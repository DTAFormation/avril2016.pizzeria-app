<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Commandes" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Commande" name="page" />
	</jsp:include>
	<h1>Liste des commandes</h1>
	<a class="btn btn-primary" href="new">Nouvelle Commande</a>
	<br><br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Numéro</td>
			<td>Statut</td>
			<td>Date</td>
			<td>Livreur</td>
			<td>Client</td>
			<td></td>
		</tr>

		<c:forEach var="commande" items="${listeCommandes}">
		<tr>
			<td>${commande.numeroCommande}</td>
			<td>${commande.statut.libelle}</td>
			<td><fmt:formatDate value="${commande.dateCommande.time}" type="both" dateStyle="short" timeStyle="short" /></td>
			<td>${commande.livreur.prenom} ${commande.livreur.nom}</td>
			<td>${commande.client.prenom} ${commande.client.nom}</td>
			<td>
				<div class="row">
					<div class="col-md-6">
						<a href="<c:url value="/commandes/edit?code=${commande.numeroCommande}"/>" class="btn btn-primary">Editer</a>
						<br>
						<form method="POST">
							<input type="hidden" name="code" value="${commande.numeroCommande}">
							<input type="hidden" name="action" value="supprimer">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form>
					</div>
				</div>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>