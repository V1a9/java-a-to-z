<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users storage</title>
    <style>
        table#tb01, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }
    </style>
</head>

<body>
<c:set var="loggedUser" scope="page" value="${loggedInUser}"/>
    <table id="tb01">
        <th>User name</th>
        <th>User role</th>
        <th>User login</th>
        <th>User password</th>
        <th>User email</th>
        <th>User create date</th>

        <c:choose>

            <c:when test="${loggedUser.role == 'Admin'}">

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

                    <c:if test="${user.email == loggedUser.email}">

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
    <table style="padding: 5px">
        <tr>
            <c:if test="${loggedInUser.role == 'Admin'}">
                <td style="border: 0;">
                    <a href="${pageContext.servletContext.contextPath}/new">Create User</a>
                </td>
            </c:if>
            <td style="border: 0">
                <form action="${pageContext.servletContext.contextPath}/delete">
                    <input type="submit" value="Delete user">
                </form>
            </td>
            <td style="border: 0">
                <form action="${pageContext.servletContext.contextPath}/update">
                    <input type="submit" value="Update user">
                </form>
            </td>
            <c:if test="${loggedInUser.role == 'Admin'}">
                <td style="border: 0">
                    <form action="${pageContext.servletContext.contextPath}/get">
                        <input type="submit" value="Get user">
                    </form>
                </td>
            </c:if>
            <td style="border: 0">
                <form action="${pageContext.servletContext.contextPath}/logout">
                    <input type="submit" value="Logout">
                </form>
            </td>
        </tr>
    </table>

</body>
</html>
