<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Update user</title>
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
<c:set var="updatingUser" scope="page" value="${loggedInUser}"/>
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
        <c:when test="${updatingUser.role == 'Admin'}">
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
                <c:if test="${user.email == updatingUser.email}">
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
    <h3 style="text-align: left">Update User</h3>
    <form name="updateForm" class="form-horizontal" method="POST" action="${pageContext.servletContext.contextPath}/ustorage/update" onsubmit="return validateUpdateForm()">
        <div class="form-group">
            <c:if test="${loggedInUser.role == 'Admin'}">
                <label for="email">User's email to be updated:</label>
                <input type="email" class="form-control" id="email" placeholder="Enter email.." name="email">
                <label for="role">User role(select one):</label>
                <select class="form-control" id="role" name="role">
                    <option>Admin</option>
                    <option>User</option>
                    <option>Guest</option>
                </select>
            </c:if>
            <c:if test="${loggedInUser.role != 'Admin'}">
                <input type="hidden" name="email" value="${loggedInUser.email}">
                <input type="hidden" name="role" value="${loggedInUser.role}">
            </c:if>
            <label for="name">User name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name.." name="name">
            <label for="login">User login:</label>
            <input type="text" class="form-control" id="login" placeholder="Enter login.." name="login">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password.." name="password">
            <label for="country">Country:</label>
            <input type="text" class="form-control" id="country" placeholder="Enter country.." name="country">
            <label for="city">City:</label>
            <input type="text" class="form-control" id="city" placeholder="Enter city.." name="city">
            <label for="date">Create date:</label>
            <input type="text" class="form-control" id="date" placeholder="Enter date.." name="date"><br>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>

<script>
    function validateUpdateForm() {
        if (document.updateForm.name.value === "") {
            alert('Please enter user name.');
            return false;
        }
        if (document.updateForm.login.value === "") {
            alert('Please enter user login.');
            return false;
        }
        if (document.updateForm.password.value === "") {
            alert('Please enter user password.');
            return false;
        }
        if (document.updateForm.country.value === "") {
            alert('Please enter user country.');
            return false;
        }
        if (document.updateForm.city.value === "") {
            alert('Please enter user city.');
            return false;
        }
        if (document.updateForm.date.value === "") {
            alert('Please enter user create date.');
            return false;
        }
    }
</script>

<script>
    $(document).ready(function () {
        $.ajax("${pageContext.servletContext.contextPath}/ustorage/getcountries", {
            method: 'GET',
            complete: function (data) {
                var countries = JSON.parse(data.responseText);
                var countriesList = document.getElementById("country");
                for (var i = 0; i < countries.length; i++) {
                    var option = document.createElement("option");
                    option.setAttribute("value", countries[i]);
                    var textOption = document.createTextNode(countries[i]);
                    countriesList.appendChild(option);
                    option.appendChild(textOption);
                }
            }
        })
    });
</script>

<script>
    $(document).ready(function () {
        $("#country").change(function () {
            $.ajax("${pageContext.servletContext.contextPath}/ustorage/getcities", {
                method: 'POST',
                data: {country: $('#country').valueOf()[0].value},
                complete: function (data) {
                    var cities = JSON.parse(data.responseText);
                    var citiesList = document.getElementById("city");
                    for (var j = 0; j < cities.length; j++) {
                        var option = document.createElement("option");
                        var text = document.createTextNode(cities[j]);
                        option.setAttribute("value", cities[j]);
                        option.appendChild(text);
                        citiesList.appendChild(option);
                    }
                }
            })
        })
    });
</script>

</body>
</html>
