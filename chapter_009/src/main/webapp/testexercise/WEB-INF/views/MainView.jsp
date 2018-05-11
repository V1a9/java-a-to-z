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
    <style>
        #data {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #data td, #users th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #data tr:nth-child(even){background-color: #f2f2f2;}

        #data tr:hover {background-color: #ddd;}

        #data th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }

    </style>
    <title>Data view - ${loggedUser.name} (${loggedUserRole}) </title>
</head>
<c:choose>
    <c:when test="${user != null && role != null}">
        <c:set var="data" value="user" scope="page"></c:set>
    </c:when>
    <c:when test="${role != null && user == null}">
        <c:set var="data" value="role" scope="page"></c:set>
    </c:when>
    <c:when test="${address != null}">
        <c:set var="data" value="address" scope="page"></c:set>
    </c:when>
    <c:when test="${music != null}">
        <c:set var="data" value="music" scope="page"></c:set>
    </c:when>
    <c:otherwise>
        <c:set var="data" value="user" scope="page"></c:set>
    </c:otherwise>
</c:choose>
<body onload="return showData('${data}')">
<div><h3 style="text-align: center">Hello ${loggedUser.name}!!!</h3></div>
<div class="container-float">
    <div class="row">
        <div class="col-xl-1">
            <div class="container">
                <div class="btn-group-vertical">
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Show:
                        </button>
                        <div class="dropdown-menu">
                            <button class="dropdown-item" onclick="return showData('user')">Users</button>
                            <button class="dropdown-item" onclick="return showData('role')">Roles</button>
                            <button class="dropdown-item" onclick="return showData('address')">Addresses</button>
                            <button class="dropdown-item" onclick="return showData('music')">Music</button>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Create:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" onclick="return pickAction('create', 'user')">User</a>
                            <a class="dropdown-item" role="button" href="${pageContext.request.contextPath}/testexercise/adduser">Add User (full)</a>
                            <a class="dropdown-item" onclick="return pickAction('create', 'role')">Role</a>
                            <a class="dropdown-item" onclick="return pickAction('create', 'address')">Address</a>
                            <a class="dropdown-item" onclick="return pickAction('create', 'music')">Music</a>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Find User by:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" onclick="return pickAction('find', 'role')">Role</a>
                            <a class="dropdown-item" onclick="return pickAction('find', 'address')">Address</a>
                            <a class="dropdown-item" onclick="return pickAction('find', 'music')">Music</a>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Update:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" onclick="return pickAction('update', 'user')">User</a>
                            <a class="dropdown-item" onclick="return pickAction('update', 'role')">Role</a>
                            <a class="dropdown-item" onclick="return pickAction('update', 'address')">Address</a>
                            <a class="dropdown-item" onclick="return pickAction('update', 'music')">Music</a>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Delete:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" onclick="return pickAction('delete', 'user')">User</a>
                            <a class="dropdown-item" onclick="return pickAction('delete', 'role')">Role</a>
                            <a class="dropdown-item" onclick="return pickAction('delete', 'address')">Address</a>
                            <a class="dropdown-item" onclick="return pickAction('delete', 'music')">Music</a>
                        </div>
                    </div>
                    <button class="btn btn-secondary" onclick="return pickAction('logout')">Logout</button>
                </div>
            </div>
        </div>
        <div class="col-xl">
            <table id="data">

            </table>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/PickAction.js"></script>
<script src="${pageContext.request.contextPath}/js/MainWindowTailoring.js"></script>
<script src="${pageContext.request.contextPath}/js/CustomizeButtons.js"></script>

</body>
</html>
