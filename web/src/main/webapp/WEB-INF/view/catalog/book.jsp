<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>


<script>
    var reserved = '<fmt:message bundle="${i18n}" key="book.reserved"/>';
</script>
<div class="col-sm-12">
    <div class="card">
        <div class="card-header p-b-0">
            <h5 class="card-title"><i class="fa fa-book"></i> ${bookVO.book.name}</h5>
        </div>

        <ul class="list-group list-group-flush">
            <li class="list-group-item"><b><fmt:message bundle="${i18n}" key="catalog.author"/>: </b>
                <c:forEach var="author" items="${bookVO.authors}" varStatus="status">
                    ${author.name} ${author.surname}
                    <c:if test="${not status.last}">,</c:if>
                </c:forEach>
            </li>
            <li class="list-group-item"><b><fmt:message bundle="${i18n}" key="catalog.genre"/>: </b>
                ${bookVO.book.genre}
            </li>

            <li class="list-group-item"><b><fmt:message bundle="${i18n}" key="catalog.year"/>: </b>
                ${bookVO.book.year}
            </li>

            <li class="list-group-item"><b><fmt:message bundle="${i18n}" key="catalog.quantity"/>: </b>
                <span id="quantity">${bookVO.book.quantity}</span>
            </li>

            <li class="list-group-item"><b><fmt:message bundle="${i18n}" key="catalog.isbn"></fmt:message>: </b>
                ${bookVO.book.isbn}
            </li>

        </ul>
        <!-- логика! -->
        <c:set var="flag" value="false"/>
        <c:if test="${not empty sreader}">
            <c:forEach var="reader" items="${bookVO.readers}">
                <c:if test="${reader.readerID eq sreader.readerID}">
                    <c:set var="flag" value="true"/>
                </c:if>
            </c:forEach>
        </c:if>
        <!-- логика! -->

        <c:choose>
            <c:when test="${flag eq \"true\"}">
                <button class="btn disabled">
                    <fmt:message bundle="${i18n}" key="book.reserved"/>
                </button>
            </c:when>
            <c:when test="${bookVO.book.quantity eq 0}">
                <button class="btn disabled">
                    <fmt:message bundle="${i18n}" key="book.unavailable"/>
                </button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-primary reserve-book" id="${bookVO.book.bookID}">
                    <fmt:message bundle="${i18n}" key="book.reserve"/>
                </button>
            </c:otherwise>
        </c:choose>
    </div>
</div>
