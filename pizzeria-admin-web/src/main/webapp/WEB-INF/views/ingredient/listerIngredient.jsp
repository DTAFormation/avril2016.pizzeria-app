<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Ingrédients" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Ingredients" name="page" />
	</jsp:include>
	<h1>Liste des ingrédients</h1>
	<a class="btn btn-primary" href="new">Nouvel ingrédient</a>
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
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="ingredient" items="${listeIngredients}">
			<tr>
				<td>${ingredient.id}</td>
				<td>${ingredient.code}</td>
				<td>${ingredient.nom}</td>
				<td><a href="<c:url value="/ingredients/edit?code=${ingredient.code}"/>" class="btn btn-primary">Éditer</a></td>
				<td>
					<form method="POST">
						<input type="hidden" name="code" value="${ingredient.code}"> <input type="hidden" name="action" value="supprimer">
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>