<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Ajouter Commande" name="title" />
</jsp:include>
<body class="container">

	<h1><c:out value="${ (commande != null && commande.id != null) ? 'Editer la commande' : 'Créer une commande' }"/></h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>

	<form method="POST">

		<c:if test="${commande.id != null}">
			<div class="form-group">
				<label for="id">Id</label> <input type="text" class="form-control"
					id="id" name="id" value="${commande.id}" readonly>
			</div>
		</c:if>

		<div class="form-group">
			<label for="nom">Numero</label> <input type="text" class="form-control"
				id="nom" name="numero" value="${commande.numeroCommande}">
		</div>
		
		<div class="form-group">
			<label for="statut">Statut</label>
			<select name="statut" id="statut" class="form-control">
				<c:forEach var="statut" items="${statuts}">
					<option value="${statut}" <c:if test="${commande.statut == statut}">selected</c:if>>${statut}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label for="date">Date</label> <input type="text"
				class="form-control" name="date" id="date" value="<fmt:formatDate value="${commande.dateCommande.time}" type="both" dateStyle="short" timeStyle="short" />">
		</div>

		<div class="form-group">
			<label for="livreur">Livreur</label>
			<select name="livreur" id="livreur" class="form-control">
				<c:forEach var="livreur" items="${livreurs}">
					<option value="${livreur.id}">${livreur.prenom} ${livreur.nom}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label for="client">Client</label>
			<select name="client" id="client" class="form-control">
				<c:forEach var="client" items="${clients}">
					<option value="${client.id}">${client.prenom} ${client.nom}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label>Pizzas</label>
			<c:forEach var="pizza" items="${pizzas}">
				<div class="row">
					<div class="col-md-2">
						<span style="display:block; margin-top:5px">${pizza.nom}</span>
					</div>
					<div class="col-md-1">
						<input type="number" class="form-control" name="${pizza.code}" value="0" min="0" ">
					</div>
				</div>
			</c:forEach>
		</div>

		<button type="submit" class="btn btn-primary">Valider</button>
	</form>
</body>
</html>