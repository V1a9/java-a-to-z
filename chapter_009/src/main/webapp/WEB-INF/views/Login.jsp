<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<c:if test="${error != null}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form method="POST" action="${pageContext.servletContext.contextPath}/sign">
    Login:<br>
    <input type="text" name="login"><br>
    Password:<br>
    <input type="password" name="password"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
