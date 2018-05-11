<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Delete user</title>
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
<c:set var="deletingUser" scope="page" value="${loggedInUser}"/>
<div class="center">
    <h2>Hello ${deletingUser.name}!</h2>
</div>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>User name</th>
            <th>User role</th>
            <th>User login</th>
            <th>User password</th>
            <th>User email</th>
            <th>Country</th>
            <th>City</th>
            <th>User create date</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${deletingUser.role == 'Admin'}">
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td> ${user.name} </td>
                        <td> ${user.role} </td>
                        <td> ${user.login} </td>
                        <td> ${user.password} </td>
                        <td> ${user.email} </td>
                        <td> ${user.country} </td>
                        <td> ${user.city} </td>
                        <td> ${user.createDate} </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="user" items="${users}">
                    <c:if test="${user.email == deletingUser.email}">
                        <tr>
                            <td> ${user.name} </td>
                            <td> ${user.role} </td>
                            <td> ${user.login} </td>
                            <td> ${user.password} </td>
                            <td> ${user.email} </td>
                            <td> ${user.country} </td>
                            <td> ${user.city} </td>
                            <td> ${user.createDate} </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <div class="container">
        <form class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/ustorage/delete" onsubmit="return confirmation()">
            <div class="form-group">
                <c:if test="${deletingUser.role == 'Admin'}">
                    <input type="text" class="form-control" id="email" placeholder="Enter User's email to be deleted.." name="email"><br>
                    <button type="submit" class="btn btn-danger" onclick="return validateForm()">Confirm</button>
                </c:if>
                <c:if test="${deletingUser.role != 'Admin'}">
                    <input type="hidden" name="email" value="${deletingUser.email}">
                    <button type="submit" class="btn btn-danger" onclick="return validateForm()">Confirm</button>
                </c:if>
            </div>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/ustorage/scripts/ValidateForm.js"></script>
<script src="${pageContext.request.contextPath}/ustorage/scripts/Confirmation.js"></script>

</body>
</html>
