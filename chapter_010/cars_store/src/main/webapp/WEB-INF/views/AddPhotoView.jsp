<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <title>Add Photos</title>
</head>
<body class="w3-content">

<c:set var="loggedUser" value="${loggedUser}"/>
<c:set var="advertId" value="${advertId}"/>

<h2>Welcome to Cars Store!</h2>

<c:if test="${loggedUser != null}">
    <form id="loggedUser">
        <input type="text" name="login" value="${loggedUser}" hidden>
    </form>
</c:if>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="mainview">Main</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="newadvert">New Advertisement</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="logout">Logout</a>
        </li>
        <c:if test="${loggedUser != null}">
            <li class="nav-item active">
                <a class="nav-link" href="#">User (${loggedUser})</a>
            </li>
        </c:if>
    </ul>
</nav>

<div class="container">
    <h2>New advertisement:</h2>
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
        <tr>
            <td>${advertId}</td>
        </tr>
        </tbody>
    </table>

    <form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/addphoto?advertId=${advertId}">
        File to upload:<br>
        <input type="file" name="upfile" required><br/><br/>
        <input type="text" name="id" value="${advertId}" hidden>
        <input type="submit" class="btn btn-primary" value="Upload" >
        <input type="button" value="Finish" class="inline btn btn-primary button float-right" onclick="redirectMain()">
    </form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/AddPhotoScript.js"></script>

</body>
</html>
