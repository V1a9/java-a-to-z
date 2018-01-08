<%@ page import="java.io.File" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
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
<c:set var="userInput" scope="page" value="${userInput}"/>

<div class="center">
    <h2 style="color: #ffffff">Hello ${loggedUser.name}!</h2>
</div><br>

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
        </tbody>
    </table>
</div>

<div class="container">
    <h3 style="text-align: left">Create User</h3>
    <form name="createForm" class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/new">
        <div class="form-group">
            <label for="name">User name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name.." name="name">
            <label for="role">User role(select one):</label>
            <select class="form-control" id="role" name="role">
                <option>Admin</option>
                <option>User</option>
                <option>Guest</option>
            </select>
            <label for="login">User login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login.." name="login">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password.." name="password">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" placeholder="Enter email.." name="email">
            <label for="country">Country:</label>
            <select class="form-control" id="country" name="country">
                <option value="">Select country:</option>
            </select>
            <label for="city">City:</label>
            <select class="form-control" id="city" name="city">
                <option value="">Select city:</option>
            </select>
            <label for="date">Create date:</label>
            <input type="text" class="form-control" id="date" placeholder="Enter date.." name="date"><br>
            <button type="submit" class="btn btn-primary" onclick="return validateCreateForm()">Submit</button>
        </div>
    </form>
</div>

<%
    File alert = new File("web/scripts/alert.js");
    System.out.println(alert.getCanonicalPath());
    System.out.println(alert.exists());
%>

<%--<script src="${pageContext.request.contextPath}/web/scripts/alert.js"></script>--%>
<script src="/web/scripts/alert.js"></script>
<script src="${pageContext.request.contextPath}/web/scripts/ValidateForm.js"></script>
<script src="${pageContext.request.contextPath}/web/scripts/GetCountries.js"></script>
<script src="${pageContext.request.contextPath}/web/scripts/GetCities.js"></script>

</body>
</html>