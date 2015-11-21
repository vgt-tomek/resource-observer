<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:basic title="Resource observer">
    <h1>Resources</h1>

    <div class="container-fluid col-lg-6 col-lg-offset-3">
        <div class="side-menu">
            <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/create-resource">Create new</a>
        </div>

        <table class="table table-stripped">
            <tr>
                <th>Name</th>
                <th>Active</th>
            </tr>
                <%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.resources.models.list.ListModel"--%>
            <c:forEach items="${model.resources}" var="resource">
                <tr>
                    <td>${resource.name}</td>
                    <td>${resource.active == true ? "yes" : "no"}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</t:basic>