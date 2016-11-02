<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" type="date" pattern="dd/MM/yyyy" var="fmtNow" />

<spring:url value="/" var="urlHome" />
<spring:url value="/rentals?rentalDate=${fmtNow}" var="urlRentals" />
<spring:url value="/customers" var="urlCustomers" />
<spring:url value="/vehicles" var="urlVehicles" />
<spring:url value="/admin" var="urlAdmin" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Rent-a-car</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${urlHome}">Home</a></li>
				<li><a href="${urlCustomers}">Customers</a></li>
				<li><a href="${urlRentals}">Rentals</a></li>
				<li><a href="${urlVehicles}">Vehicles</a></li>
				<li>
					<security:authorize access="hasRole('admin')">
						<a href="${urlAdmin}">Admin</a>
					</security:authorize>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<security:authorize access="isAuthenticated()">
						Logged in: <security:authentication property="principal.username" />!
					</security:authorize>
				</li>
				<li>
					<c:url var="logoutUrl" value="/logout" />
					<form:form	id="logoutForm" action="${logoutUrl}">
					</form:form>
					<a href="#" onclick="document.getElementById('logoutForm').submit()">
						<span class="glyphicon glyphicon-log-out"></span> Logout</a>
					<!--<form:form action="${pageContext.request.contextPath}/logout" method="POST">
						<input type="submit" value="Logout" />
					</form:form>-->
				</li>
			</ul>
			</li>
		</div>
	</div>
</nav>
