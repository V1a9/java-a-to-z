<%@ page import="com.vgoryashko.servlet.crudservlet.User" %>
<%@ page import="com.vgoryashko.servlet.crudservlet.UserStore" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Get user</title>
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

    <% if (request.getAttribute("email").equals(user.getEmail())) { %>

    <tr style="background-color: goldenrod">
        <td><%= user.getName() %></td>
        <td><%= user.getLogin() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getCreateDate() %></td>
    </tr>

    <% } else { %>

    <tr>
        <td><%= user.getName() %></td>
        <td><%= user.getLogin() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getCreateDate() %></td>
    </tr>

    <% } %>

    <% } %>
</table>
<table style="width: 20%">
    <tr>
        <td style="border: 0">
            <a href="<%=request.getContextPath()%>/new">Create User</a>
        </td>
        <td style="border: 0">
            <form action="<%=request.getContextPath()%>/delete">
                <input type="submit" value="Delete user">
            </form>
        </td>
        <td style="border: 0">
            <form action="<%=request.getContextPath()%>/update">
                <input type="submit" value="Update user">
            </form>
        </td>
        <td style="border: 0">
            <form action="<%=request.getContextPath()%>/get">
                <input type="submit" value="Get user">
            </form>
        </td>
    </tr>
</table>

</body>
</html>
