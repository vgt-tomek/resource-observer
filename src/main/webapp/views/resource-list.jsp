<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vgt" uri="/WEB-INF/vgt.tld" %>

<t:basic title="Resource observer">
    <h1>Resources</h1>

    <div class="container-fluid col-lg-8 col-lg-offset-2 col-md-12">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th class="text-center">Check interval</th>
                <th class="text-center" colspan="2">Last check</th>
                <th class="text-right">Last version change</th>
                <th class="text-center">Version count</th>
                <th class="text-center">Active</th>
                <th class="text-center">Options</th>
            </tr>
                <%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.list.ListModel"--%>
            <c:forEach items="${model.resources}" var="resource">
                <tr>
                    <td>${resource.name}</td>
                    <td class="text-center">${resource.checkInterval}</td>
                    <td class="text-right">
                        <vgt:TimeAgo since="${resource.lastCheckAt}"/>
                        <c:if test="${resource.lastCheckAt ne resource.lastSeenAt}">
                            <div class="last-seen-warning">Last seen: <vgt:TimeAgo since="${resource.lastSeenAt}"/></div>
                        </c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${resource.scanTriggerPending}">
                                <span>queued</span>
                            </c:when>
                            <c:otherwise>
                                <form method="POST" action="${pageContext.request.contextPath}/app/manual-scan-trigger">
                                    <div>
                                        <input type="hidden" name="resourceId" value="${resource.id}"/>
                                        <button type="submit" name="manualScanTriggerSubmit" value="submit" class="btn btn-default btn-xs btn-nav" title="Refresh">
                                            <span class="glyphicon glyphicon-refresh"></span>
                                        </button>
                                    </div>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-right">
                        <c:if test="${resource.newFlag eq true}">
                            <span class="badge">NEW</span>
                        </c:if>
                        <vgt:TimeAgo since="${resource.lastVersionChange}"/>
                    </td>
                    <td class="text-center">${resource.distinctSnapshotsCount}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${resource.active == true}">
                                <span class="glyphicon glyphicon-ok glyphicon-green"></span>
                            </c:when>
                            <c:otherwise>
                                <span class="glyphicon glyphicon-remove glyphicon-red"></span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <a class="btn btn-default btn-xs btn-nav glyphicon glyphicon-edit" href="${pageContext.request.contextPath}/app/edit-resource/${resource.id}" title="Edit"></a>
                        <a class="btn btn-default btn-xs btn-nav glyphicon glyphicon-stats" href="${pageContext.request.contextPath}/app/resource-details/${resource.id}" title="Details"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:basic>