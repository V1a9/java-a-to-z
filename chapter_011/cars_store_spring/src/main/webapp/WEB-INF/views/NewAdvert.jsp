<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <style>
        .sub-entry {
            width: 50%;
            float: left;
            padding-right: 5%;
        }
        .button {
            text-align: center;
            padding-top: 10px;
            clear: both;
        }
    </style>

    <title>New Advertisement</title>
</head>
<body class="w3-content">

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
            <a class="nav-link" href="mainview.do">Main</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="logout.do">Logout</a>
        </li>
        <c:if test="${loggedUser != null}">
            <li class="nav-item active">
                <a class="nav-link" href="#">User (${loggedUser})</a>
            </li>
        </c:if>
    </ul>
</nav>

<div class="container">

    <form method="post" action="${pageContext.request.contextPath}/newadvert.do">
        <div class="sub-entry">
            <div class="form-group">
                <label class="control-label col-sm-2" for="vin">VIN:</label>
                <input type="text" class="form-control" id="vin" placeholder="Enter vin.." name="vin" required>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="brand">Brand:</label>
                <select class="form-control" id="brand" name="brand" required>
                    <option value="">Choose brand:</option>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="body">Select body:</label>
                <select class="form-control" id="body" name="body" required>
                    <option value="">Choose body:</option>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="engine">Select engine:</label>
                <select class="form-control" id="engine" name="engine" required>
                    <option value="">Choose engine:</option>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="transmission">Select transmission:</label>
                <select class="form-control" id="transmission" name="transmission" required>
                    <option value="">Choose transmission:</option>
                </select>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="suspension">Select suspension:</label>
                <select class="form-control" id="suspension" name="suspension" required>
                    <option value="">Choose suspension:</option>
                </select>
            </div>
        </div>
        <div class="sub-entry">
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" rows="10" required></textarea>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="price">Price:</label>
                <input type="text" class="form-control" id="price" placeholder="Enter price.." name="price" required>
            </div>
        </div>
        <button type="submit" class="btn btn-primary button">Submit</button>
    </form>

</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/NewAdvertScripts.js"></script>

</body>
</html>
