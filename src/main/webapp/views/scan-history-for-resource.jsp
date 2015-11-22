<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.scans.models.resourcehistory.ResourceHistoryModel"--%>

<t:basic title="Resource observer">

    <h1>Scan history: ${model.resource.name}</h1>

    <div class="row">
        <div class="container-fluid col-lg-6 col-lg-offset-3">
            <t:scanHistory scans="${model.scans}"/>
        </div>
    </div>

</t:basic>