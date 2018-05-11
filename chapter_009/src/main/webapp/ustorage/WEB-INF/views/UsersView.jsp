<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users storage</title>
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
<c:set var="loggedUser" scope="page" value="${loggedInUser}"/>
<c:set var="foundUser" scope="page" value="${foundUser}"/>
<div class="center">
    <h2>Hello ${loggedUser.name}!</h2>
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
            <c:when test="${loggedUser.role == 'Admin'}">
                <c:forEach var="user" items="${users}">
                    <c:choose>
                        <c:when test="${foundUser.email == user.email}">
                            <tr class="success">
                                <td> ${user.name} </td>
                                <td> ${user.role} </td>
                                <td> ${user.login} </td>
                                <td> ${user.password} </td>
                                <td> ${user.email} </td>
                                <td> ${user.country} </td>
                                <td> ${user.city} </td>
                                <td> ${user.createDate} </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
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
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="user" items="${users}">
                    <c:if test="${user.email == loggedUser.email}">
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
</div>

<div class="container">
    <c:if test="${loggedInUser.role == 'Admin'}">
        <a href="${pageContext.servletContext.contextPath}/ustorage/new" class="btn btn-primary" role="button">Create User</a>
        <a href="${pageContext.servletContext.contextPath}/ustorage/get" class="btn btn-primary" role="button">Get User</a>
    </c:if>
    <a href="${pageContext.servletContext.contextPath}/ustorage/delete" class="btn btn-danger" role="button">Delete User</a>
    <a href="${pageContext.servletContext.contextPath}/ustorage/update" class="btn btn-primary" role="button">Update User</a>
    <a href="${pageContext.servletContext.contextPath}/ustorage/logout" class="btn btn-primary" role="button">Logout</a>
</div>

</body>
</html>
