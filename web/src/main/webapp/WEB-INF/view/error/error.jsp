<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<div class="container text-center">
    <div class="page-header center-block">
        <h1><spring:message code="error.title"/>
            <small><spring:message code="error.oops"/></small>
        </h1>
    </div>
    <img src="${pageContext.request.contextPath}/asserts/img/error.gif" class="rounded center-block">
</div>