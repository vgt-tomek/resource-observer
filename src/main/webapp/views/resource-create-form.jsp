<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:basic title="Create new resource">
    <h1>Create resource</h1>

    <div class="container-fluid col-lg-6 col-lg-offset-3">
        <t:ResourceForm action="${pageContext.request.contextPath}/app/create-resource"/>
    </div>
</t:basic>