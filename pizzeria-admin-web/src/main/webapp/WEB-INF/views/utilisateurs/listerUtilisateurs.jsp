<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Utilisateurs" name="title" />
</jsp:include>

<body class="container">
	<h1>Liste des utilisateurs</h1>
	<br>
	<a class="btn btn-primary" href="new">Nouvel Utilisateur</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Nom</td>
			<td>Prénom</td>
			<td>Email</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="utilisateur" items="${listeUtilisateurs}">
		<tr>
			<td>
				<div class="row">
					<div class="col-md-2">
						<b>${utilisateur.nom}</b>
					</div>
				</div>
			</td>
			
			<td>
				<div class="row">
					<div class="col-md-2">
						<b>${utilisateur.prenom}</b>
					</div>
				</div>
			</td>
			
			<td>
				<div class="row">
					<div class="col-md-2">
						<b>${utilisateur.email}</b>
					</div>
				</div>
			</td>
			
			<td>
				<div class="row">
					<div class="col-md-2">
						<a href="<c:url value="/utilisateurs/edit?email=${utilisateur.email}"/>" class="btn btn-primary">Editer</a>
						<br>
						<%-- <form method="POST">
							<input type="hidden" name="email" value="${utilisateur.email}">
							<input type="hidden" name="action" value="supprimer">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form> --%>
					</div>
				</div>
			</td>
			
			<td>
				<div class="row">
					<div class="col-md-2">
						<%-- <a href="<c:url value="/utilisateurs/edit?email=${utilisateur.email}"/>" class="btn btn-primary">Editer</a>
						<br> --%>
						<form method="POST">
							<input type="hidden" name="email" value="${utilisateur.email}">
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