<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<script>
    var returned = '<spring:message code="book.returned"/>';
</script>
<div class="container">
    <div class="card-columns">

        <c:forEach var="form" items="${forms}">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${form.book.name}</h4>
                    <p class="card-text">
                        <b>
                            <spring:message code="catalog.genre"/>:
                        </b>
                            ${form.book.genre}
                    </p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <b><spring:message code="catalog.author"/>: </b>
                        <c:forEach var="author" items="${form.book.authors}" varStatus="status">
                            ${author.name} ${author.surname}
                            <c:if test="${not status.last}">,</c:if>
                        </c:forEach>
                    </li>
                    <li class="list-group-item">
                        <b><spring:message code="catalog.year"/>: </b>
                            ${form.book.year}
                    </li>
                    <li class="list-group-item">
                        <b><spring:message code="book.receival.type"/>: </b>
                            ${form.receivalType}
                    </li>
                    <li class="list-group-item">
                        <b><spring:message code="book.receival.date"/>: </b>
                            ${form.receivalDate}
                    </li>
                    <li class="list-group-item">
                        <b><spring:message code="book.return.date"/>: </b>
                            ${form.returnDate}
                    </li>
                </ul>
                <div class="card-body">
                    <button class="btn btn-primary return-book" id="${form.book.bookID}">
                        <spring:message code="book.return"/>
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>