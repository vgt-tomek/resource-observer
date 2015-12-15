<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                        <option ${version.versionId eq model.leftVersionSelected ? 'selected="selected"':''} value="${version.versionId}">Version #${version.versionId}</option>
                    </c:forEach>
                </select>

                <label for="compare-second">to</label>

                <select class="form-control" id="compare-second" name="second">
                    <c:forEach items="${model.versions}" var="version">
                        <option ${version.versionId eq model.rightVersionSelected ? 'selected="selected"':''} value="${version.versionId}">Version #${version.versionId}</option>
                    </c:forEach>
                </select>

                <input class="btn btn-default btn-nav" type="submit" value="Compare"/>
            </div>
        </form>
    </div>

    <c:if test="${not empty model.diffLines}">
        <div class="container-fluid col-lg-8 col-lg-offset-2 col-md-12 diff-colored">
            <c:forEach items="${model.diffLines}" var="line">
                <div class="diff-${fn:toLowerCase(line.type)}"><c:out value="${line.line}"/></div>
            </c:forEach>
        </div>
    </c:if>

</t:basic>