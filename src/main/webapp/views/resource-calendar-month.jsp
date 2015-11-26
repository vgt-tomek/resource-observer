<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%--@elvariable id="model" type="pl.vgtworld.resourceobserver.app.calendar.models.month.MonthModel"--%>

<t:basic title="Resource observer">

    <h1>${model.resource.name}</h1>

    <div class="container-fluid">
        <div class="col-lg-9 col-md-12">
            <h2>Calendar</h2>
            <t:versionsMonth versionsMonthly="${model.versionsMonthly}"/>
        </div>
    </div>

</t:basic>