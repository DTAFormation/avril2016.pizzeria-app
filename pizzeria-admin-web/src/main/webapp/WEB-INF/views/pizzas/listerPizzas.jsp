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
		<jsp:param value="Pizza" name="page" />
	</jsp:include>
	<h1>Liste des pizzas</h1>
	<a class="btn btn-primary" href="new">Nouvelle Pizza</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Image</td>
			<td>Id</td>
			<td>Nom</td>
			<td>Prix</td>
			<td>Code</td>
			<td>Ingrédients</td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="pizza" items="${listePizzas}">
			<tr>
				<td><img src="<c:url value="${pizza.urlImage}"/>" width="150" height="120"></td>
				<td>${pizza.id}</td>
				<td>${pizza.nom}</td>
				<td>${pizza.prix}€</td>
				<td>${pizza.code}</td>
				<td>
					<ul>
						<c:forEach var="ingredient" items="${pizza.ingredients}">
							<li>${ingredient.nom}</li>
						</c:forEach>
					</ul>
				</td>
				<td><a href="<c:url value="/pizzas/edit?code=${pizza.code}"/>"
					class="btn btn-primary">Editer</a></td>
				<td>
					<form method="POST">
						<input type="hidden" name="code" value="${pizza.code}"> <input
							type="hidden" name="action" value="supprimer">
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>