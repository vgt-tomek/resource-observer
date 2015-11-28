<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.calendar.models.month.MonthModel"--%>

<t:basic title="Resource observer">

    <h1>${model.pageHeadTitle}</h1>

    <div class="container-fluid">
        <div class="text-center calendar-navigation">
            <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/resource-calendar${model.previousMonthLinkSuffix}">Previous</a>
            <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/resource-details/${model.resource.id}">Back to resource details</a>
            <a class="btn btn-default btn-nav" href="${pageContext.request.contextPath}/app/resource-calendar${model.nextMonthLinkSuffix}">Next</a>
        </div>

        <div class="col-lg-9 col-md-12">
            <h2>Calendar</h2>

            <t:versionsMonth versionsMonthly="${model.versionsMonthly}"/>
        </div>

        <div class="col-lg-3 col-md-12">
            <h2>Log</h2>

            <div class="container-scrollable">
                <t:scanHistory scans="${model.scanLog}"/>
            </div>
        </div>
    </div>

</t:basic>