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
<table id="tb01">
    <th>User name</th>
    <th>User login</th>
    <th>User email</th>
    <th>User create date</th>
    <c:forEach var="user" items="${users}">

        <tr>
            <td> ${user.name} </td>
            <td> ${user.login} </td>
            <td> ${user.email} </td>
            <td> ${user.createDate} </td>
        </tr>

    </c:forEach>
</table>

<form method="POST" action="<%=request.getContextPath()%>/update">
    User's email that will be updated: <input type="text" name="email"><br>
    User name: <input type="text" name="newName"><br>
    User login: <input type="text" name="newLogin"><br>
    User create date: <input type="text" name="newDate"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
