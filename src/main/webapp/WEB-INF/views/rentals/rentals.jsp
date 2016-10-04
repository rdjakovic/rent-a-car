<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Rentals</h1>

    <spring:url value="/rentals" var="rentalsUrl" />
    <form:form action="${rentalsUrl}" method="get" modelAttribute="rental" class="navbar-form navbar-right" role="search">
      <div class="form-group">
        <input type="text" name="rentalDate" value="${rentalDate}" class="form-control" placeholder="Search by date"/>
      </div>
      <button type="submit" class="btn btn-default">Search</button>
    </form:form>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>#ID</th>
                <th>Date</th>
                <th>Customer</th>
                <th>Vehicle</th>
                <th>Days rented</th>
                <th>Price</th>
                <th>Note</th>
            </tr>
        </thead>

        <c:forEach var="rental" items="${rentals}">
            <tr>
                <td>
                    ${rental.id}
                </td>
                <td>${rental.rentalDate}</td>
                <td>${rental.customer.lastName}</td>
                <td>${rental.vehicle.carBrand} ${rental.vehicle.carModel}</td>
                <td>${rental.days}</td>
                <td>${rental.days * rental.vehicle.pricePerDay}</td>
                <td>${rental.note}</td>

                <td>
                    <spring:url value="/rentals/${rental.id}" var="rentalUrl" />
                    <spring:url value="/rentals/delete/${rental.id}" var="deleteUrl" />
                    <spring:url value="/rentals/edit/${rental.id}" var="updateUrl" />

                    <button class="btn btn-info" onclick="location.href='${rentalUrl}'">View</button>
                    <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Edit</button>
                    <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
            </tr>
        </c:forEach>
    </table>

	<div class="form-group">
	    <spring:url value="/rentals/new" var="urlAddRental" />
		<div class="col-sm-10">
			<button class="btn btn-primary" onclick="location.href='${urlAddRental}'">Add new rental</button>
		</div>
	</div>



</div>
