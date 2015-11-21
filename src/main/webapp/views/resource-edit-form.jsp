<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:basic title="Edit resource">
    <h1>Edit resource</h1>

    <div class="container-fluid col-lg-6 col-lg-offset-3">
            <%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.form.FormModel"--%>
        <t:ResourceForm action="${pageContext.request.contextPath}/app/create-resource" resource="${model.resource}" errors="${model.errors}"/>
    </div>
</t:basic>