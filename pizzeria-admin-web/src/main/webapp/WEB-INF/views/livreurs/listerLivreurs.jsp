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
		<jsp:param value="Livreur" name="page" />
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
			<h1>Liste des livreurs - ${active}</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-4">
			<a class="btn btn-success"
				href="<%=request.getContextPath()%>/livreurs/new">Nouveau
				Livreur</a>
		</div>
		<div class="col-xs-8 btn-group">
			<a href="<%=request.getContextPath()%>/livreurs/list/active"
				class="btn btn-default">Actifs</a> <a
				href="<%=request.getContextPath()%>/livreurs/list/inactive"
				class="btn btn-default">Inactifs</a> <a
				href="<%=request.getContextPath()%>/livreurs/list"
				class="btn btn-default">Tous</a>
		</div>
	</div>
	
	<br>

	<table class="table">
		<tr>
			<td>Id</td>
			<td>Nom Prénom</td>
			<td>Actions</td>
		</tr>
		<c:if test="${active == 'actif' || active == 'tous'}">
			<c:forEach var="livreur" items="${listeLivreurs}">
				<c:if test="${livreur.actif}">
					<tr>
						<td>${livreur.id}/ ${livreur.code}</td>
						<td>${livreur.nom}${livreur.prenom}</td>
						<td><a
							href="<c:url value="/livreurs/edit?id=${livreur.id}"/>"
							class="btn btn-primary">Éditer</a>
							<form method="POST">
								<input type="hidden" name="id" value="${livreur.id}"> <input
									type="hidden" name="action" value="supprimer">
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form></td>
					</tr>
				</c:if>
			</c:forEach>
			</c:if>
		<c:if test="${active ==  'inactif' || active == 'tous'}">
			<c:forEach var="livreur" items="${listeLivreurs}">
				<c:if test="${!livreur.actif}">
					<tr>
						<td>${livreur.id}/ ${livreur.code}</td>
						<td>${livreur.nom}${livreur.prenom}</td>
						<td><a
							href="<c:url value="/livreurs/edit?id=${livreur.id}"/>"
							class="btn btn-primary">Éditer</a>
							<form method="POST">
								<input type="hidden" name="id" value="${livreur.id}"> <input
									type="hidden" name="action" value="supprimer">
								<button type="submit" class="btn btn-danger">Supprimer</button>
							</form></td>
					</tr>
				</c:if>
			</c:forEach>
		</c:if>
	</table>

</body>
</html>