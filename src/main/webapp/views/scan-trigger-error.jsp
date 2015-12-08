<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:messagePage message="Creating manual scan trigger failed." type="danger">
    <jsp:attribute name="navigation">
        <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app">Go back to resource list</a>
    </jsp:attribute>
</t:messagePage>