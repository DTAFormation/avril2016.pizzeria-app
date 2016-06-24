<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
	<div class="container-fluid row">
		<div class="navbar-header col-xs-2 ">
			<a class="navbar-brand" href="#">WebSiteName</a>
		</div>
		<ul class="nav navbar-nav col-xs-8 ">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="<%=request.getContextPath()%>">commande</a></li>
			<li><a href="<%=request.getContextPath()%>/pizzas/list">pizza</a></li>
			<li><a href="<%=request.getContextPath()%>/ingredients/list">ingrédient</a></li>
			<li><a href="<%=request.getContextPath()%>/clients/list">client</a></li>
			<li><a href="<%=request.getContextPath()%>/livreurs/list">livreur</a></li>
			<li><a href="<%=request.getContextPath()%>/statistiques">statistique</a></li>
			<li><a href="<%=request.getContextPath()%>/utilisateurs/list">utilisateur</a></li>
		</ul>
		<p class="navbar-text navbar-right col-xs-2">
			Signed in as <a href="#" class="navbar-link">toto</a>
		</p>


	</div>
</nav>
