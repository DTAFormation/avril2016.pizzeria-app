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
		<jsp:param value="Ingredient" name="page" />
	</jsp:include>
	<h1>Liste des ingredients</h1>
	<a class="btn btn-primary" href="new">Nouvel ingredient</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Id</td>
			<td>Nom</td>
			<td></td>
			<td></td>
		</tr>

		<c:forEach var="ingredient" items="${listeIngredients}">
			<tr>
				<td>
					${ingredient.id}
				</td>
				<td>
					${ingredient.nom}
				</td>
				<td>			
					<a href="<c:url value="/ingredients/edit?code=${ingredient.code}"/>" class="btn btn-primary">Editer</a> 			
				</td>
				<td>
					<form method="POST">
						<input type="hidden" name="code" value="${ingredient.code}">
						<input type="hidden" name="action" value="supprimer">
						<button type="submit" class="btn btn-danger">Supprimer</button>
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>