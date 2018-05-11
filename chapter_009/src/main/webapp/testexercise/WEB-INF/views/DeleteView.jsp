<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <title>Delete ${entity} - ${loggedUser.name} (${loggedUserRole})</title>
</head>
<body>
<div><h3 style="text-align: center">Hello ${loggedUser.name}!!!</h3></div>

<div class="container">
    <h5>Delete ${entity}:</h5>
    <form method="post" class="form-horizontal" id="user" action="${pageContext.servletContext.contextPath}/testexercise/delete?entity=${entity}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="id">ID:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id" placeholder="Enter id.." name="id">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-info" onclick="return confirm('Are you sure?')">Submit</button>
                <a href="${pageContext.request.contextPath}/testexercise/" class="btn btn-info" role="button" onclick="return confirm('Are you sure?')">Cancel</a>
            </div>
        </div>
    </form>
</div>

</body>
</html>
