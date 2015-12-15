<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.diff.models.form.FormModel"--%>

<t:basic title="Resource diff">

    <h1>diff view - ${model.resource.name}</h1>

    <div class="text-center">
        <c:forEach items="${model.errors}" var="error">
            <div class="alert alert-danger">${error}</div>
        </c:forEach>
        <form class="form-inline" method="post" action="${pageContext.request.contextPath}/app/diff/${model.resource.id}">
            <div class="form-group">
                <label for="compare-first">Compare</label>

                <select class="form-control" id="compare-first" name="first">
                    <c:forEach items="${model.versions}" var="version">
                        <option value="${version.versionId}">Version #${version.versionId}</option>
                    </c:forEach>
                </select>

                <label for="compare-second">to</label>

                <select class="form-control" id="compare-second" name="second">
                    <c:forEach items="${model.versions}" var="version">
                        <option value="${version.versionId}">Version #${version.versionId}</option>
                    </c:forEach>
                </select>

                <input class="btn btn-default btn-nav" type="submit" value="Compare"/>
            </div>
        </form>
    </div>

</t:basic>