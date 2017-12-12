<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update user</title>
    <style>
        table#tb01, th, td {
            width: 30%;
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<c:set var="updatingUser" scope="page" value="${loggedInUser}"/>

<table id="tb01">

    <th>User name</th>
    <th>User role</th>
    <th>User login</th>
    <th>User password</th>
    <th>User email</th>
    <th>User create date</th>

    <c:choose>

        <c:when test="${updatingUser.role == 'Admin'}">

            <c:forEach var="user" items="${users}">

                <tr>
                    <td> ${user.name} </td>
                    <td> ${user.role} </td>
                    <td> ${user.login} </td>
                    <td> ${user.password} </td>
                    <td> ${user.email} </td>
                    <td> ${user.createDate} </td>
                </tr>

            </c:forEach>

        </c:when>
        <c:otherwise>

            <c:forEach var="user" items="${users}">

                <c:if test="${user.email == updatingUser.email}">

                    <tr>
                        <td> ${user.name} </td>
                        <td> ${user.role} </td>
                        <td> ${user.login} </td>
                        <td> ${user.password} </td>
                        <td> ${user.email} </td>
                        <td> ${user.createDate} </td>
                    </tr>

                </c:if>

            </c:forEach>

        </c:otherwise>

    </c:choose>

</table>

<form method="POST" action="${pageContext.servletContext.contextPath}/update">

    <c:if test="${loggedInUser.role == 'Admin'}">

        User's email that will be updated:<br>
        <input type="text" name="email"><br><br>
        <label>User role:</label>
        <select name="role">
            <option value = "Admin">Admin</option>
            <option value = "User">User</option>
        </select><br>

    </c:if>

    <c:if test="${loggedInUser.role == 'User'}">
        <input type="hidden" name="email" value="${loggedInUser.email}">
        <input type="hidden" name="role" value="${loggedInUser.role}">
    </c:if>

    User name:<br>
    <input type="text" name="name"><br>
    User login:<br>
    <input type="text" name="login"><br>
    User password:<br>
    <input type="password" name="password"><br>
    User create date:<br>
    <input type="text" name="date"><br><br>
    <input type="submit" value="Submit">

</form>

</body>
</html>
