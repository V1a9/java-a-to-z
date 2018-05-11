<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>Main page</title>
</head>

<body>

<c:set var="loggedUser" value="${loggedUser}"/>

<h2>Welcome to Cars Store!</h2>

<c:if test="${loggedUser != null}">
    <form id="loggedUser">
        <input type="text" name="login" value="${loggedUser}" hidden>
    </form>
</c:if>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="login">Login</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="register">Register</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="advert">New Advertisement</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="logout">Logout</a>
        </li>
        <c:if test="${loggedUser != null}">
            <li class="nav-item active" style="position: relative; left: 1580px;">
                <a class="nav-link" href="#">User (${loggedUser})</a>
            </li>
        </c:if>
    </ul>
</nav></br>

<!-- Sidebar -->
<div class="w3-sidebar w3-light-grey w3-bar-block" style="width:20%">
    <h4 class="w3-bar-item">Display options:</h4>
    <form id="filter" onsubmit="return getFilteredAdverts()">
        <input style="margin-left: 20px" type="checkbox" name="today">   Posted today</br>
        <input style="margin-left: 20px" type="checkbox" name="photos" >   With photo
        <hr>
        <h5 class="w3-bar-item" style="padding-top: 0; padding-bottom: 0; margin-bottom: 0">Brands:</h5></br>
    </form>
</div>

<div class="container">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Car's spec</th>
            <th>Description</th>
            <th>Price</th>
            <th>Status</th>
            <th>Created</th>
            <th>Photos</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/IndexScripts.js"></script>

</body>
</html>