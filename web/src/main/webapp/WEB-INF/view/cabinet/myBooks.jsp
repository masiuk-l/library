<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<script>
    var returned = '<fmt:message bundle="${i18n}" key="book.returned"/>';
</script>
<div class="container">
    <div class="card-columns">

        <c:forEach var="form" items="${forms}">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${form.book.name}</h4>
                    <p class="card-text">
                        <b>
                            <fmt:message bundle="${i18n}" key="catalog.genre"/>:
                        </b>
                            ${form.book.genre}
                    </p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="catalog.author"/>: </b>
                        <c:forEach var="author" items="${form.book.authors}" varStatus="status">
                            ${author.name} ${author.surname}
                            <c:if test="${not status.last}">,</c:if>
                        </c:forEach>
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="catalog.year"/>: </b>
                            ${form.book.year}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="book.receival.type"/>: </b>
                            ${form.receivalType}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="book.receival.date"/>: </b>
                            ${form.receivalDate}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="book.return.date"/>: </b>
                            ${form.returnDate}
                    </li>
                </ul>
                <div class="card-body">
                    <button class="btn btn-primary return-book" id="${form.book.bookID}">
                        <fmt:message bundle="${i18n}" key="book.return"/>
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>