<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Pizza" name="title" />
</jsp:include>

<body class="container">
	<h1>Liste des ingredients</h1>
	<a class="btn btn-primary" href="new">Nouvel ingredient</a>
	<br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<tr>
			<td>Informations</td>
			<td></td>
		</tr>

		<c:forEach var="ingredient" items="${listeIngredients}">
		<tr>
			<td>
				<div class="row">
					<div class="col-md-6">
						Ref. ${ingredient.code}
						<br> <b>${ingredient.name}</b>
					</div>
					<div class="col-md-6">
						<a href="<c:url value="/ingredients/edit?code=${ingredient.code}"/>" class="btn btn-primary">Editer</a>
						<br>
						<form method="POST">
							<input type="hidden" name="code" value="${ingredient.code}">
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