<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%=request.getContextPath()%>


<nav class="navbar navbar-default">
	<div class="container-fluid row">
		<div class="navbar-header col-xs-2 ">
			<a class="navbar-brand" href="#">WebSiteName</a>
		</div>
		<ul class="nav navbar-nav col-xs-8 ">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="<%=request.getContextPath()%>/commandes/list">Commande</a></li>
			<li><a href="<%=request.getContextPath()%>/pizzas/list">Pizza</a></li>
			<li><a href="<%=request.getContextPath()%>/ingredients/list">Ingrédient</a></li>
			<li><a href="<%=request.getContextPath()%>/clients/list">Client</a></li>
			<li><a href="<%=request.getContextPath()%>/livreurs/list">Livreur</a></li>
			<li><a href="<%=request.getContextPath()%>/statistiques">Statistique</a></li>
			<li><a href="<%=request.getContextPath()%>/utilisateurs/list">Utilisateur</a></li>
		</ul>
		<div class="navbar-text navbar-right col-xs-2">
			${sessionScope.auth_email} <a
				href="$.ajax({
							    url: '/script.cgi',
							    type: 'DELETE',
							    success: function(result) {
							        // Do something with the result
							    }
							});"
				class="navbar-link"><span class="glyphicon glyphicon-log-out"></span></a>
		</div>


	</div>
</nav>
