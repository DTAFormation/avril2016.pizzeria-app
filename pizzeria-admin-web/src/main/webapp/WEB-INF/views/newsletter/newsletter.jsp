<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page news letter" name="title" />

</jsp:include>
<body class="container">
	<jsp:include page="../layout/menu.jsp">
		<jsp:param value="newsletter" name="page" />
	</jsp:include>
	<%-- <h1>
		<c:out
			value="${ (client != null && client.id != null) ? 'Editer le client' : 'Créer un client' }" />
	</h1> --%>
<h1> Page de gestion de la newsletter</h1>

<%-- 
	<c:set var="urlToPost"
		value="${client.id != null ? '/clients/edit':  '/clients/new'}" /> --%>

<form method="POST" action="<c:url value="/newsletter"></c:url>">

			
				<div class="form-group">
					<label for="pizzaPromo">nom de la pizza en promo</label> <input type="text" class="form-control"
						id="pizzaPromo" name="pizzaPromo" value="${newsletter.pizzaPromo}" >
				</div>
			<button type="submit" class="btn btn-primary">Envoyer la newsletter</button>

		</form>











</body>
</html>