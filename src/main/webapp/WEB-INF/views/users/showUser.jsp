<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>User Details</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${user.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Username</label>
        <div class="col-sm-10">${user.userName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Role</label>
        <div class="col-sm-10">${user.roles.role}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Password</label>
        <div class="col-sm-10">${user.password}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">First Name</label>
        <div class="col-sm-10">${user.firstName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Last Name</label>
        <div class="col-sm-10">${user.lastName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Email</label>
        <div class="col-sm-10">${user.email}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Enabled</label>
        <div class="col-sm-10">${user.enabled}</div>
    </div>

</div>