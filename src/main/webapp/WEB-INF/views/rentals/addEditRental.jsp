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

<spring:url value="/rentals" var="rentalsUrl" />
<form:form action="${rentalsUrl}" method="post" modelAttribute="rental"  class="form-horizontal">
	<form:hidden path="id" />

	<div class="form-group">
		<form:label path="rentalDate" cssClass="col-sm-2">Car brand</form:label>
		<div class="col-sm-6">
			<form:input path="rentalDate" cssClass="form-control" />
		</div>
		<div class="col-sm-4">
			<span class="label label-danger"><form:errors path="rentalDate" /></span>
		</div>
	</div>

    <div class="form-group">
        <form:label path="customer" cssClass="col-sm-2">Customer</form:label>
        <div class="col-sm-6">
            <form:select path="customer" class="form-control">
                <form:option value="NONE" label="--- Select ---" />
                <form:options items="${customers}" />
            </form:select>
            <form:errors path="customer" class="control-label" />
        </div>
    </div>

    <div class="form-group">
        <form:label path="vehicle" cssClass="col-sm-2">Customer</form:label>
        <div class="col-sm-6">
            <form:select path="vehicle" class="form-control">
                <form:option value="NONE" label="--- Select ---" />
                <form:options items="${vehicles}" />
            </form:select>
            <form:errors path="vehicle" class="control-label" />
        </div>
    </div>

	<div class="form-group">
		<form:label path="days" cssClass="col-sm-2">Car model</form:label>
		<div class="col-sm-6">
			<form:input path="days"  cssClass="form-control" />
		</div>
		<div class="col-sm-4">
			<span class="label label-danger"><form:errors path="days" /></span>
		</div>
	</div>

    <div class="form-group}">
        <form:label path="note" cssClass="col-sm-2">Note</label>
        <div class="col-sm-10">
            <form:textarea path="note" rows="5" class="form-control" id="note" placeholder="Notes/Description" />
            <form:errors path="note" class="control-label" />
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
