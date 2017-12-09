<%@ page import="com.vgoryashko.servlet.crudservlet.User" %>
<%@ page import="java.util.ArrayList" %>
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
    <% for (User user: (ArrayList<User>) request.getAttribute("users")) { %>

    <tr>
        <td><%= user.getName() %></td>
        <td><%= user.getLogin() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getCreateDate() %></td>
    </tr>

    <% } %>
</table>

<form method="POST" action="<%=request.getContextPath()%>/get">
    Enter User's email: <input type="text" name="email">
    <input type="submit" value="Submit">
</form>

</body>
</html>
