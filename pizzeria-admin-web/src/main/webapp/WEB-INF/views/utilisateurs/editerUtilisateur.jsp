<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Editer Utilisateur" name="title" />
</jsp:include>
<body class="container">

	<h1><c:out value="${ (utilisateur != null && utilisateur.id != null) ? 'Editer utilisateur' : 'Créer un utilisateur' }"/></h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>


	<c:if test="${utilisateur != null}">
		<form method="POST">

			<c:if test="${utilisateur.id != null}">
				<div class="form-group">
					<label for="id">Id</label> <input type="text" class="form-control"
						id="id" name="id" value="${utilisateur.id}" readonly>
				</div>
			</c:if>

			<div class="form-group">
				<label for="nom">Nom</label> <input type="text" class="form-control"
					id="nom" name="nom" value="${utilisateur.nom}">
			</div>
			
			<div class="form-group">
				<label for="nom">Prénom</label> <input type="text" class="form-control"
					id="prenom" name="prenom" value="${utilisateur.prenom}">
			</div>
			
			<div class="form-group">
		        <label for="email">Email</label>
		        <input type="email" class="form-control" id="email" name="email" value="${utilisateur.email}" required>
		    </div>
		    
		
		    <div class="form-group">
		        <label for="motDePasse">Mot de passe</label> <input type="password"
		                                                            class="form-control" name="motDePasse"
		                                                            id="motDePasse"
		                                                            value="${utilisateur.motDePasse}"
		                                                            required>
		    </div>
		    
		    <div class="form-group">
		        <label for="confirmationMotDePasse">Confirmation Mot de passe</label> 
		        <input type="password" class="form-control" name="confirmationMotDePasse"
		                 id="confirmationMotDePasse" value="" required>
		    </div>

			<button type="submit" class="btn btn-primary">Valider</button>
		</form>
	</c:if>

</body>
</html>