<%@tag description="Basic template" pageEncoding="UTF-8" %>
<%@attribute name="navigation" fragment="true" %>
<%@attribute name="message" required="true" type="java.lang.String" %>
<%@attribute name="type" required="true" type="java.lang.String" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body style="padding-top:200px;">
<div class="content">

    <div class="container-fluid">

        <div class="alert alert-${type} col-lg-6 col-lg-offset-3">
            ${message}
        </div>

        <div class="col-lg-6 col-lg-offset-3 text-center">
            <jsp:invoke fragment="navigation" />
        </div>

    </div>

</div>
</body>
</html>