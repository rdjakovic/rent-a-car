<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<c:choose>
		<c:when test="${rentalForm['new']}">
			<h1>Add Rental</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit Rental</h1>
		</c:otherwise>
	</c:choose>
	<br />


<form:form modelAttribute="rental"  class="form-horizontal">
	<form:hidden path="id" />

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="form-group">
        <form:label path="customer" class="col-sm-2">Customer</form:label>
        <form:label path="customer" class="col-sm-6">${rental.customer.firstName}  ${rental.customer.lastName}</form:label>
    </div>



	<div class="form-group">
		<form:label path="days" class="col-sm-2">Days rented</form:label>
		<div class="col-sm-6">
			<form:input path="days"  class="form-control" />
		</div>
		<div class="col-sm-4">
			<span class="label label-danger"><form:errors path="days" /></span>
		</div>
	</div>



    <div class="form-group">
        <form:label path="note" class="col-sm-2">Note/Description</form:label>
        <div class="col-sm-6">
            <form:textarea path="note" rows="5" class="form-control" id="note" />
        </div>
        <div class="col-sm-4">
            <span class="label label-danger"><form:errors path="note" /></span>
        </div>
    </div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">
				Save
			</button>
		</div>
	</div>
</form:form>

</div>
