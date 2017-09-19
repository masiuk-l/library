<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:useBean id="now" class="java.util.Date" scope="page"/>

<fmt:formatDate value='${now}' pattern='yyyy-MM-dd' var="searchFormated"/>
<c:set var="strDate" value="${searchFormated}"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>

<script>
    var ban = '<fmt:message bundle="${i18n}" key="reader.ban"></fmt:message>';
    var unban = '<fmt:message bundle="${i18n}" key="reader.unban"></fmt:message>';
</script>

<div class="container">
    <div class="card-columns">
        <c:forEach var="readerVO" items="${readerVOS}">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${readerVO.reader.surname} ${readerVO.reader.name} ${readerVO.reader.secondName}</h4>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="login.gender"/>: </b>
                        <c:choose>
                            <c:when test="${readerVO.reader.gender eq 'male'}">
                                <fmt:message bundle="${i18n}" key="login.gender.male"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message bundle="${i18n}" key="login.gender.female"/>
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="login.birthday"/>: </b>
                            ${readerVO.reader.birthday}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="reader.books"/>: </b>
                        <ul class=" list-group list-unstyled">
                            <c:forEach var="formVO" items="${readerVO.formVOS}">
                                <li class="">
                                    <c:choose>
                                        <c:when test="${formVO.form.returnDate gt strDate}">
                                            <span class="text-success"><i
                                                    class="fa fa-check-circle-o"></i> ${formVO.bookVO.book.name}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-danger"><i
                                                    class="fa fa-times-circle-o"></i> ${formVO.bookVO.book.name}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${readerVO.reader.status eq 'BANNED'}">
                            <button id="${readerVO.reader.readerID}" class="btn btn-success ban-reader">
                                <fmt:message bundle="${i18n}" key="reader.unban"/>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button id="${readerVO.reader.readerID}" class="btn btn-danger ban-reader">
                                <fmt:message bundle="${i18n}" key="reader.ban"/>
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
</div>