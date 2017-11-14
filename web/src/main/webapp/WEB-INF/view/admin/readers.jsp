<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="now" class="java.util.Date" scope="page"/>

<fmt:formatDate value='${now}' pattern='yyyy-MM-dd' var="searchFormated"/>
<c:set var="strDate" value="${searchFormated}"/>

<script>
    var ban = '<spring:message code="reader.ban"/>';
    var unban = '<spring:message code="reader.unban"/>';
</script>

<div class="container">
    <div class="card-columns">
        <c:forEach var="reader" items="${readers}">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${reader.surname} ${reader.name} ${reader.secondName}</h4>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <b><spring:message code="login.gender"/>: </b>
                        <c:choose>
                            <c:when test="${reader.gender eq 'male'}">
                                <spring:message code="login.gender.male"/>
                            </c:when>
                            <c:otherwise>
                                <spring:message code="login.gender.female"/>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li class="list-group-item">
                        <b><spring:message code="login.birthday"/>: </b>
                            ${reader.birthday}
                    </li>
                    <li class="list-group-item">
                        <b><spring:message code="reader.books"/>: </b>
                        <ul class=" list-group list-unstyled">
                            <c:forEach var="form" items="${reader.forms}">
                                <li class="">
                                    <c:choose>
                                        <c:when test="${form.returnDate gt strDate}">
                                            <span class="text-success"><i
                                                    class="fa fa-check-circle-o"></i> ${form.book.name}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger"><i
                                                    class="fa fa-times-circle-o"></i> ${form.book.name}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${reader.status eq 'BANNED'}">
                            <button id="${reader.readerID}" class="btn btn-success ban-reader">
                                <spring:message code="reader.unban"/>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button id="${reader.readerID}" class="btn btn-danger ban-reader">
                                <spring:message code="reader.ban"/>
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
</div>