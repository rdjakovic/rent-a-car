<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/" var="urlHome" />
<spring:url value="/rentals" var="urlRentals" />
<spring:url value="/customers" var="urlCustomers" />
<spring:url value="/vehicles" var="urlVehicles" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Home</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav">
			    <li class="active"><a href="${urlRentals}">Rentals</a></li>
				<li class="active"><a href="${urlCustomers}">Customers</a></li>
				<li class="active"><a href="${urlVehicles}">Vehicles</a></li>
			</ul>
		</div>
	</div>
</nav>