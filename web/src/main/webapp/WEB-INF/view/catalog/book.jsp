<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>


<script>
    var reserved = '<spring:message code="book.reserved"/>';
</script>
<div class="col-sm-12">
    <div class="card">
        <div class="card-header p-b-0">
            <h5 class="card-title"><i class="fa fa-book"></i> ${book.name}</h5>
        </div>

        <ul class="list-group list-group-flush">
            <li class="list-group-item"><b><spring:message code="catalog.author"/>: </b>
                <c:forEach var="author" items="${book.authors}" varStatus="status">
                    ${author.name} ${author.surname}
                    <c:if test="${not status.last}">,</c:if>
                </c:forEach>
            </li>
            <li class="list-group-item"><b><spring:message code="catalog.genre"/>: </b>
                ${book.genre}
            </li>

            <li class="list-group-item"><b><spring:message code="catalog.year"/>: </b>
                ${book.year}
            </li>

            <li class="list-group-item"><b><spring:message code="catalog.quantity"/>: </b>
                <span id="quantity">${book.quantity}</span>
            </li>

            <li class="list-group-item"><b><spring:message code="catalog.isbn"/>: </b>
                ${book.isbn}
            </li>

        </ul>
        <!-- логика! -->
        <c:set var="flag" value="false"/>
        <c:if test="${not empty sreader}">
            <c:forEach var="reader" items="${readers}">
                <c:if test="${reader.readerID eq sreader.readerID}">
                    <c:set var="flag" value="true"/>
                </c:if>
            </c:forEach>
        </c:if>
        <!-- логика! -->

        <c:choose>
            <c:when test="${flag eq \"true\"}">
                <button class="btn disabled">
                    <spring:message code="book.reserved"/>
                </button>
            </c:when>
            <c:when test="${book.quantity eq 0}">
                <button class="btn disabled">
                    <spring:message code="book.unavailable"/>
                </button>
            </c:when>
            <c:otherwise>
                <button class="btn btn-primary reserve-book" id="${book.bookID}">
                    <spring:message code="book.reserve"/>
                </button>
            </c:otherwise>
        </c:choose>
    </div>
</div>
