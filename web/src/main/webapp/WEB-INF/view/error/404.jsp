<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<div class="container text-center">
    <div class="page-header center-block">
        <h1>404
            <small><spring:message code="error.page_not_found"/></small>
        </h1>
    </div>
    <img src="${pageContext.request.contextPath}/asserts/img/404.png" class="rounded w-25">
</div>