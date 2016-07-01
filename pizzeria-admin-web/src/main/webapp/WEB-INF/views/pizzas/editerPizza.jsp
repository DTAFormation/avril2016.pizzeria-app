<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Editer Pizza" name="title" />
</jsp:include>
<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="Pizza" name="page" />
	</jsp:include>
	<h1>
		<c:out
			value="${ (pizza != null && pizza.id != null) ? 'Editer la pizza' : 'Créer une pizza' }" />
	</h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>


	<c:if test="${pizza != null}">
		<form method="POST">

			<c:if test="${pizza.id != null}">
				<div class="form-group">
					<label for="id">Id</label> <input type="text" class="form-control"
						id="id" name="id" value="${pizza.id}" readonly>
				</div>
			</c:if>

			<div class="form-group">
				<label for="nom">Nom</label> <input type="text" class="form-control"
					id="nom" name="nom" value="${pizza.nom}" required>
			</div>

			<div class="form-group">
				<label for="urlImage">URL Image</label> <input type="text"
					class="form-control" name="urlImage" id="urlImage"
					value="${pizza.urlImage}" required>
			</div>
			<c:if test="${pizza.code == null}">
				<div class="form-group">
					<label for="code">Code</label> <input type="text"
						class="form-control" name="code" id="code" value="${pizza.code}"
						required>
				</div>
			</c:if>

			<div class="form-group">
				<label for="prix">Prix</label> <input type="text"
					class="form-control" name="prix" id="prix" value="${pizza.prix}"
					required>
			</div>
			<div class="form-group">
				<div class="col-md-6 col-lg-6">
					<label for="ingredient">liste ingredients</label>
					<ul id="pizzaIngredient" class="list-group">
						<c:forEach var="ingredients" items="${pizza.ingredients}">
							<li id="ingredient-${ ingredients.code }" class="list-group-item">${ ingredients.nom }<input
								type="text" name="ingredient" value="${ ingredients.code }"
								hidden></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-md-6 col-lg-6">
					<label for="ingredients">liste de tout les ingredients</label>
					<ul id="allIngredient" class="list-group">
						<c:forEach var="ingredients" items="${listeIngredient}">
							<li id="li-${ingredients.code}"
								onclick="addIngredient('${ingredients.code}', '${ ingredients.nom }')"
								class="list-group-item">${ ingredients.nom }</li>
						</c:forEach>
					</ul>
					<a class="btn btn-primary" href="<%=request.getContextPath() %>/ingredients/new">Nouvel ingredient</a>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Valider</button>
			<a href="<c:url value="/pizzas/list"></c:url>" class="btn btn-primary">Retour</a>
		</form>
	</c:if>
	<script type="text/javascript">
		function addIngredient(code, name) {
			var html = '<li id="ingredient-'+code+'" class="list-group-item">'
					+ name
					+ '<input type="text" name="ingredient" value="'+code+'" hidden></li>';
			if (!document.getElementById('ingredient-' + code)) {
				$("#pizzaIngredient").append(html);
			} else {
				alert('cette ingredient est déjà présent sur cette pizza');
			}
		}
		document.getElementById('pizzaIngredient').addEventListener('click',
				function(evt) {
					$("#" + evt.srcElement.attributes[0].nodeValue).remove();
				}, false)
	</script>

</body>
</html>