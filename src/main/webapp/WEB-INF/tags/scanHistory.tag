<%@tag description="scanHistory" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="scans" required="true" type="java.util.List"%>
<%--@elvariable id="scan" type="pl.vgtworld.resourceobserver.storage.scan.Scan"--%>

<table class="table table-striped">
    <tr>
        <th>Timestamp</th>
        <th>Snapshot</th>
    </tr>
    <c:forEach items="${scans}" var="scan">
        <tr>
            <td>${scan.createdAt}</td>
            <td>
                <c:choose>
                    <c:when test="${empty scan.snapshotId}">N/A</c:when>
                    <c:otherwise>Snapshot #${scan.snapshotId}</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${empty scans}">
    <div class="alert alert-info">
        No scans available.
    </div>
</c:if>
