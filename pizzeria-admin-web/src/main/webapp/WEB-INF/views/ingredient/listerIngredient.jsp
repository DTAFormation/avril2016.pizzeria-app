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

	<div class="row">
		<c:if test="${msg != null}">
			<div class="alert alert-danger" role="alert">${msg}</div>
		</c:if>
		<c:if test="${msg_success != null}">
			<div class="alert alert-success" role="alert">${msg_success}</div>
		</c:if>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<h1>Liste des ingrédients - ${active}</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-4">
			<a class="btn btn-success" href="<%=request.getContextPath()%>/ingredients/new">Nouvel ingrédient</a>
		</div>
		<div class="col-xs-8 btn-group">
			<a href="<%=request.getContextPath()%>/ingredients/list/active" class="btn btn-default">Actifs</a>
			<a href="<%=request.getContextPath()%>/ingredients/list/inactive" class="btn btn-default">Inactifs</a>
			<a href="<%=request.getContextPath()%>/ingredients/list" class="btn btn-default">Tous</a>
		</div>
	</div>

	<br>

	<table class="table">
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Nom</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<c:if test="${active == 'active' || active == 'toutes'}">
			<c:forEach var="ingredient" items="${listeIngredients}">
				<c:if test="${ingredient.actif}">
					<tr>
						<td>${ingredient.id}</td>
						<td>${ingredient.code}</td>
						<td>${ingredient.nom}</td>
						<td><a href="<c:url value="/ingredients/edit?code=${ingredient.code}"/>" class="btn btn-primary">Éditer</a></td>
						<td>
							<form method="POST" class="col-xs-4 col-xs-offset-4">
								<input type="hidden" name="code" value="${ingredient.code}"> <input type="hidden" name="action" value="toggle">
								<button type="submit" class="btn btn-danger">Désactiver</button>
							</form>
						</td>
						<td></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
		<c:if test="${active == 'inactive' || active == 'toutes'}">
			<c:forEach var="ingredient" items="${listeIngredients}">
				<c:if test="${!ingredient.actif}">
					<tr>
						<td>${ingredient.id}</td>
						<td>${ingredient.code}</td>
						<td>${ingredient.nom}</td>
						<td><a href="<c:url value="/ingredients/edit?code=${ingredient.code}"/>" class="btn btn-primary">Éditer</a></td>
						<td>
							<form method="POST" class="col-xs-4">
								<input type="hidden" name="code" value="${ingredient.code}"> <input type="hidden" name="action" value="toggle">
								<button type="submit" class="btn btn-success">Réactiver</button>
							</form>
						</td>
						<td>
							<form method="POST">
								<input type="hidden" name="code" value="${ingredient.code}"> <input type="hidden" name="action" value="supprimer">
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>