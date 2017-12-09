<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Delete user</title>
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

<form method="POST" action="<%=request.getContextPath()%>/delete">
    Enter User's email: <input type="text" name="email"><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
