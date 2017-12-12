<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Create User</title>
    <style>
        table#tb01, th, td {
            width: 30%;
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<table id="tb01">
    <th>User name</th>
    <th>User role</th>
    <th>User login</th>
    <th>User password</th>
    <th>User email</th>
    <th>User create date</th>

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

</table>

<form method="POST" action="${pageContext.servletContext.contextPath}/new">
    User name:<br>
    <input type="text" name="name"><br><br>
    <label>User role:</label>
    <select name="role">
        <option value = "Admin">Admin</option>
        <option value = "User">User</option>
    </select><br>
    User login: <br>
    <input type="text" name="login"><br>
    User password:<br>
    <input type="password" name="password"><br>
    User email:<br>
    <input type="text" name="email"><br>
    User create date:<br>
    <input type="text" name="date"><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>