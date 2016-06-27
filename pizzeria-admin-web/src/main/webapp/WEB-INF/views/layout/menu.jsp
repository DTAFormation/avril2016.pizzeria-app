<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav class="navbar navbar-default">
	<div class="container-fluid row">
		<div class="navbar-header col-xs-2 ">
			<a class="navbar-brand" href="#">The best Pizzeria</a>
		</div>
		<ul class="nav navbar-nav col-xs-8 ">
			<li><a href="#">Home</a></li>
			<li <c:if test="${'Commande' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/commandes/list">Commande</a></li>
			<li <c:if test="${'Pizza' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/pizzas/list">Pizza</a></li>
			<li <c:if test="${'Ingrédient' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/ingredients/list">Ingrédient</a></li>
			<li <c:if test="${'Client' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/clients/list">Client</a></li>
			<li <c:if test="${'Livreur' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/livreurs/list">Livreur</a></li>
			<li
				<c:if test="${'Statistique' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/statistiques">Statistique</a></li>
			<li
				<c:if test="${'Utilisateur' == param.page}"> class="active"</c:if>><a
				href="<%=request.getContextPath()%>/utilisateurs/list">Utilisateur</a></li>

		</ul>
		<div class="navbar-text navbar-right col-xs-2">
			${sessionScope.auth_email} <a id="logout" href="<%=request.getContextPath()%>/logout"
				class="navbar-link"><span
				class="glyphicon glyphicon-log-out"></span></a>
		</div>


	</div>
</nav>
<script type="text/javascript">
	document.getElementById('logout').addEventListener('click',
			function(evt) {
				$.ajax({
					url : '<%=request.getContextPath()%>/login',
					type : 'DELETE',

				});
			}, false)
</script>