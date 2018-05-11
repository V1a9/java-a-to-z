<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>Register</title>
</head>
<body>

<c:if test="${error != null}">
    <div class="alert alert-danger" style="text-align: center; font-size: medium">
        <strong>${error}</strong>
    </div>
</c:if>

<div class="container">
    <form method="POST" action="${pageContext.request.contextPath}/register">
        <h5>Register form:</h5>
        <div class="form-group">
            <label for="register">Name:</label>
            <input type="text" class="form-control" id="register" placeholder="Enter name.." name="name">
        </div>
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login.." name="login">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password.." name="pwd">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-secondary" role="button" onclick="return confirm('Are you sure?')">Cancel</a>
    </form>
</div>

</body>
</html>
