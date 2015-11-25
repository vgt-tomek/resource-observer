<%@tag description="versionsMonth" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="vgt" uri="/WEB-INF/vgt.tld" %>
<%@attribute name="versionsMonthly" required="true" type="pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable"%>

<table class="table table-bordered table-version-calendar">
    <tr>
        <c:forEach items="${versionsMonthly.weekDayNames}" var="name">
            <th class="text-center">${name}</th>
        </c:forEach>
    </tr>
    <c:forEach items="${versionsMonthly.weeks}" var="week">
        <tr>
            <c:forEach items="${week.days}" var="day">
                <th class="text-right">${day.dayOfMonth}</th>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach items="${week.days}" var="day">
                <td>
                    <c:if test="${empty day.versions}">
                        &nbsp;
                    </c:if>
                    <c:if test="${not empty day.versions}">
                        <ul class="versions">
                            <c:forEach items="${day.versions}" var="version">
                                <li>
                                    <vgt:VersionLink version="${version}"/>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
