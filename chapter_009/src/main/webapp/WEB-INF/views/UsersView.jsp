<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.vgoryashko.servlet.crudservlet.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users storage</title>
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

        <c:forEach var="user" items="${users}" >

            <c:choose>

                <c:when test="${foundUser != null}">

                    <c:choose>

                        <c:when test="${foundUser.email == user.email}">

                            <tr style="background-color: goldenrod">
                                <td> ${user.name} </td>
                                <td> ${user.login} </td>
                                <td> ${user.email} </td>
                                <td> ${user.createDate} </td>
                            </tr>

                        </c:when>

                        <c:otherwise>

                            <tr>
                                <td> ${user.name} </td>
                                <td> ${user.login} </td>
                                <td> ${user.email} </td>
                                <td> ${user.createDate} </td>
                            </tr>

                        </c:otherwise>

                    </c:choose>

                </c:when>

                <c:otherwise>

                    <tr>
                        <td> ${user.name} </td>
                        <td> ${user.login} </td>
                        <td> ${user.email} </td>
                        <td> ${user.createDate} </td>
                    </tr>

                </c:otherwise>

            </c:choose>

        </c:forEach>

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
