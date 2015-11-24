<%@tag description="scanHistory" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="vgt" uri="/WEB-INF/vgt.tld" %>
<%@attribute name="scans" required="true" type="java.util.List"%>
<%--@elvariable id="scan" type="pl.vgtworld.resourceobserver.services.dto.Scan"--%>

<table class="table table-striped">
    <tr>
        <th>Timestamp</th>
        <th>Snapshot</th>
    </tr>
    <c:forEach items="${scans}" var="scan">
        <tr>
            <td><vgt:SimpleDatetime date="${scan.createdAt}"/></td>
            <td>
                <c:choose>
                    <c:when test="${empty scan.version}">N/A</c:when>
                    <c:otherwise><vgt:VersionLink version="${scan.version}"/></c:otherwise>
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
