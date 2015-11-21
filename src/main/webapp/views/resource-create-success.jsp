<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:messagePage message="Resource added successfully." type="success">
    <jsp:attribute name="navigation">
        <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app">Go back to resource list</a>
        <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/create-resource">Add another</a>
    </jsp:attribute>
</t:messagePage>