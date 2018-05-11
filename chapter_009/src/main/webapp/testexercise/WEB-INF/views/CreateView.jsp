<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>Create ${entity} - ${loggedUser.name} (${loggedUserRole})</title>
</head>
<body>

<div><h3 style="text-align: center">Hello ${loggedUser.name}!!!</h3></div>

<div class="container">
    <h5>Create ${entity}:</h5>
    <form method="post" class="form-horizontal" id="user" action="${pageContext.servletContext.contextPath}/testexercise/create">
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Enter name.." name="name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="login">Login:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="login" placeholder="Enter login.." name="login">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="pwd">Password:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="pwd" placeholder="Enter password.." name="pwd">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="selRole">Select role:</label>
            <div class="col-sm-10">
                <select class="form-control" id="selRole" name="role">
                    <option value="">Choose Role:</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Submit</button>
                <a href="${pageContext.request.contextPath}/testexercise/" class="btn btn-info" role="button" onclick="return confirm('Are you sure?')">Cancel</a>
            </div>
        </div>
    </form>
</div>

<div class="container">
    <h5>Create ${entity}:</h5>
    <form method="post" class="form-horizontal" id="role" action="${pageContext.servletContext.contextPath}/testexercise/create">
        <div class="form-group">
            <label class="control-label col-sm-2" for="createRole">Role:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="createRole" placeholder="Enter role.." name="role">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Submit</button>
                <a href="${pageContext.request.contextPath}/testexercise/" class="btn btn-info" role="button" onclick="return confirm('Are you sure?')">Cancel</a>
            </div>
        </div>
    </form>
</div>

<div class="container">
    <h5>Create ${entity}:</h5>
    <form method="post" class="form-horizontal" id="music" action="${pageContext.servletContext.contextPath}/testexercise/create">
        <div class="form-group">
            <label class="control-label col-sm-2" for="createMusic">Music:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="createMusic" placeholder="Enter music.." name="music">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Submit</button>
                <a href="${pageContext.request.contextPath}/testexercise/" class="btn btn-info" role="button" onclick="return confirm('Are you sure?')">Cancel</a>
            </div>
        </div>
    </form>
</div>

<div class="container">
    <h5>Create ${entity}:</h5>
    <form method="post" class="form-horizontal" id="address" action="${pageContext.servletContext.contextPath}/testexercise/create">
        <div class="form-group">
            <label class="control-label col-sm-2" for="createAddress">Address:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="createAddress" placeholder="Enter address.." name="address">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info">Submit</button>
                <a href="${pageContext.request.contextPath}/testexercise/" class="btn btn-info" role="button" onclick="return confirm('Are you sure?')">Cancel</a>
            </div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/CustomizeCreateView.js"></script>"
<script src="${pageContext.request.contextPath}/js/GetRoles.js"></script>

</body>
</html>
