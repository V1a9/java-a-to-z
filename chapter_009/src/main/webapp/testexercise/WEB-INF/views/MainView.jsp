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
    <title>Data view</title>
</head>
<body>

<div class="container-float">
    <div class="row">
        <div class="col-xl-1">
            <div class="container">
                <div class="btn-group-vertical">
                    <c:if test="${loggedUserRole == 'ADMIN'}">
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                                Show:
                            </button>
                            <div class="dropdown-menu">
                                <button class="dropdown-item" onclick="return showData('users')">Users</button>
                                <button class="dropdown-item" onclick="return showData('roles')">Roles</button>
                                <button class="dropdown-item" onclick="return showData('addresses')">Addresses</button>
                                <button class="dropdown-item" onclick="return showData('musics')">Music</button>
                            </div>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                                Create:
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">User</a>
                                <a class="dropdown-item" href="#">Role</a>
                                <a class="dropdown-item" href="#">Address</a>
                                <a class="dropdown-item" href="#">Music</a>
                            </div>
                        </div>
                    </c:if>
                    <%--<button class="button" formaction="${pageContext.request.contextPath}/testexercise/read" onclick="return pickAction('read')">Read</button>--%>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Find Users by:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/testexercise/read">Name</a>
                            <a class="dropdown-item" href="#">Address</a>
                            <a class="dropdown-item" href="#">Music</a>
                            <a class="dropdown-item" href="#">Role</a>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Update:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/testexercise/read">User</a>
                            <a class="dropdown-item" href="#">Address</a>
                            <a class="dropdown-item" href="#">Music</a>
                            <a class="dropdown-item" href="#">Role</a>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">
                            Delete:
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/testexercise/read">Delete</a>
                            <a class="dropdown-item" href="#">Address</a>
                            <a class="dropdown-item" href="#">Music</a>
                            <a class="dropdown-item" href="#">Role</a>
                        </div>
                    </div>
                    <button class="btn btn-secondary" formaction="${pageContext.request.contextPath}/testexercise/logout" onclick="return pickAction('logout')">Logout</button>
                </div>
            </div>
        </div>
        <div class="col-xl">
            <table id="data">

            </table>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/Actions.js"></script>
<script src="${pageContext.request.contextPath}/js/MainWindowTailoring.js"></script>

</body>
</html>
