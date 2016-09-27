<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Welcome to Vehicles app</h1>

    <a href="<c:url value="/login" />">Login</a> |
    <a href="<c:url value="/login/register" />">Register</a>
  </body>
</html>
