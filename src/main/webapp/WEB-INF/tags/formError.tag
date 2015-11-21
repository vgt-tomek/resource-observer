<%@tag description="formErrors" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="formErrors" required="true" type="java.util.List"%>

<c:if test="${fn:length(formErrors) gt 0}">
    <div class="errors">
        <c:forEach items="${formErrors}" var="errorMessage">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:forEach>
    </div>
</c:if>
