<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .center {
            margin: auto;
            border: none;
            text-align: center;
            color: #ffffff;
            border-radius: 4px;
            background-color: #2e8b57;
            padding: 10px;
        }
    </style>
</head>
<body>

<div class="center">
    <h2>Welcome to UserStorage Homepage!</h2>
</div>
<c:if test="${error != null}">
    <div class="alert alert-danger" style="text-align: center; font-size: medium">
        <strong>${error}</strong>
    </div>
</c:if>
<div class="container">
    <form name="loginForm" method="POST" action="${pageContext.servletContext.contextPath}/sign">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="login" placeholder="Your login..">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="password" placeholder="Your password..">
        </div>
        <button type="submit" class="btn btn-primary" onclick="return validateLoginForm()">Submit</button>
    </form>
</div>

<script>
    function validateLoginForm() {
        if (document.loginForm.login.value === "") {
            alert("Please enter user login.");
            return false;
        }
        if (document.loginForm.password.value === "") {
            alert("Please enter user password.");
            return false;
        }
    }
</script>

<script src="/scripts/alert.js"></script>

</body>
</html>
