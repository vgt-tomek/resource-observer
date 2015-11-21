<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:basic title="Resource observer">
    <h1>Resources</h1>

    <div class="container-fluid col-lg-6 col-lg-offset-3">
        <div class="side-menu">
            <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/create-resource">Create new</a>
        </div>

        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th class="text-center">Active</th>
            </tr>
                <%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.list.ListModel"--%>
            <c:forEach items="${model.resources}" var="resource">
                <tr>
                    <td>${resource.name}</td>
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
                </tr>
            </c:forEach>
        </table>
    </div>
</t:basic>