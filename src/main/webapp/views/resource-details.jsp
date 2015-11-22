<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.details.DetailsModel"--%>

<t:basic title="Resource observer">

    <h1>${model.resource.name}</h1>

    <div class="row">
        <div class="container-fluid col-lg-4">
            <h2>
                Latest scans
                <c:if test="${!empty model.newestScans}">
                    <a class="btn btn-default btn-nav" href="#">View all <span class="badge">${model.scanCount}</span></a>
                </c:if>
            </h2>
            <table class="table table-striped">
                <tr>
                    <th>Timestamp</th>
                    <th>Snapshot</th>
                </tr>
                <c:forEach items="${model.newestScans}" var="scan">
                    <tr>
                        <td>${scan.createdAt}</td>
                        <td>Snapshot #${scan.snapshotId}</td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${empty model.newestScans}">
                <div class="alert alert-info">
                    No scans available.
                </div>
            </c:if>
        </div>
    </div>

</t:basic>