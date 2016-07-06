<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Ajouter Commande" name="title" />
</jsp:include>

<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Commandes" name="page" />
	</jsp:include>
	<c:set var="editPage" value="${ (commande != null && commande.id != null) }" />

	<h1>
		<c:out value="${ editPage ? 'Éditer la commande' : 'Créer une commande' }" />
	</h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>

	<c:if test="${commande != null}">
		<form method="POST">
			<c:if test="${editPage}">
				<input type="hidden" name="id" value="${commande.id}">
			</c:if>
			<div class="form-group">
				<label for="nom">Numéro :</label> <input type="text" class="form-control" id="nom" name="numero" value="${commande.numeroCommande}" placeholder="Numéro de la commande (Chaîne de caractères)"
					<c:if test="${editPage}">readonly</c:if> required autofocus>
			</div>
			<div class="form-group">
				<label for="statut">Statut :</label> <select name="statut" id="statut" class="form-control">
					<c:forEach var="statut" items="${statuts}">
						<option value="${statut}" <c:if test="${commande.statut == statut}">selected</c:if>>${statut.libelle}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="date">Date :</label> <input type="datetime-local" class="form-control" name="date" id="date"
					value="<fmt:formatDate value="${commande.dateCommande.time}" pattern="yyyy-MM-dd"/>T<fmt:formatDate value="${commande.dateCommande.time}" pattern="HH:mm"/>" required>
			</div>
			<div class="form-group">
				<label for="livreur">Livreur :</label> <select name="livreur" id="livreur" class="form-control">
					<c:forEach var="livreur" items="${livreurs}">
						<option value="${livreur.id}" <c:if test="${commande.livreur.id == livreur.id}">selected</c:if>>${livreur.prenom}&nbsp;${livreur.nom}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="client">Client :</label> <select name="client" id="client" class="form-control">
					<c:forEach var="client" items="${clients}">
						<option value="${client.id}" <c:if test="${commande.client.id == client.id}">selected</c:if>>${client.prenom}&nbsp;${client.nom}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Pizzas :</label>
				<c:choose>
					<c:when test="${editPage}">
						<c:forEach var="pizzaCmd" items="${commande.pizzas}">
							<div class="row">
								<div class="col-md-2">
									<span style="display: block; margin-top: 5px">${pizzaCmd.pizza.nom}</span>
								</div>
								<div class="col-md-1">
									<input type="number" class="form-control" name="${pizzaCmd.pizza.code}" value="${pizzaCmd.quantite}" min="0">
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="pizza" items="${pizzas}">
							<div class="row">
								<div class="col-md-2">
									<span style="display: block; margin-top: 5px">${pizza.nom}</span>
								</div>
								<div class="col-md-1">
									<input type="number" class="form-control" name="${pizza.code}" value="0" min="0">
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<button type="submit" class="btn btn-success">Valider</button>
			<a href="<c:url value="/commandes/list"></c:url>" class="btn btn-primary">Retour</a>
		</form>
	</c:if>
</body>
</html>