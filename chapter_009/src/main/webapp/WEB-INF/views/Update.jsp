<%@ page import="com.vgoryashko.servlet.crudservlet.UserStore" %>
<%@ page import="com.vgoryashko.servlet.crudservlet.User" %>
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
    <% for (User user: UserStore.getInstance().getAll()) { %>
    <tr>
        <td><%= user.getName() %></td>
        <td><%= user.getLogin() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getCreateDate() %></td>
    </tr>
    <% } %>
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
