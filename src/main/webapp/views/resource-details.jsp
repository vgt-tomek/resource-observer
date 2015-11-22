<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel"--%>

<t:basic title="Resource observer">

    <h1>${model.resource.name}</h1>

    <div class="row">
        <div class="container-fluid col-lg-4">
            <h2>Versions</h2>
            <table class="table table-striped">
                <tr>
                    <th class="text-center">Version</th>
                    <th class="text-center">First occurrence</th>
                    <th class="text-center">Last occurrence</th>
                    <th class="text-center">Total</th>
                </tr>
                <c:forEach items="${model.versions}" var="version">
                    <tr>
                        <td>Version #${version.versionId}</td>
                        <td class="text-center">${version.firstOccurrence}</td>
                        <td class="text-center">${version.lastOccurrence}</td>
                        <td class="text-right">${version.count}</td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${empty model.versions}">
                <div class="alert alert-info">
                    No versions available.
                </div>
            </c:if>
        </div>
        <div class="container-fluid col-lg-4">
            <h2>
                Latest scans
                <c:if test="${!empty model.newestScans}">
                    <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/scans/resource-history/${model.resource.id}">
                        View all <span class="badge">${model.scanCount}</span>
                    </a>
                </c:if>
            </h2>
            <t:scanHistory scans="${model.newestScans}"/>
        </div>
    </div>

</t:basic>