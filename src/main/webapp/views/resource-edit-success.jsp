<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:messagePage message="Resource saved successfully." type="success">
    <jsp:attribute name="navigation">
        <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app">Go back to resource list</a>
    </jsp:attribute>
</t:messagePage>