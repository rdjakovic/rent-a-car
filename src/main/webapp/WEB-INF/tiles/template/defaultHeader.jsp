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
<spring:url value="/admin/users" var="urlUsers" />

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
				<security:authorize access="hasRole('ADMIN')">
					<li>
						<a href="${urlUsers}">Users</a>
					</li>
				</security:authorize>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAnonymous()">
					<li><a href="<c:url value='/login' />">
						<span class="glyphicon glyphicon-log-in"></span> Log In
					</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
						   role="button" aria-haspopup="true" aria-expanded="false">
							<span class="glyphicon glyphicon-user"></span>
								<security:authentication property="principal.name" />
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<c:url var="logoutUrl" value="/logout" />
								<form:form	id="logoutForm" action="${logoutUrl}"></form:form>
								<a href="#" onclick="document.getElementById('logoutForm').submit()">
									<span class="glyphicon glyphicon-log-out"></span> Logout
								</a>
							</li>
						</ul>
					</li>
				</security:authorize>
			</ul>
			</li>
		</div>
	</div>
</nav>
