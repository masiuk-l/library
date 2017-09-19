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

        <c:forEach var="formVO" items="${formVOS}">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${formVO.bookVO.book.name}</h4>
                    <p class="card-text">
                        <b>
                            <fmt:message bundle="${i18n}" key="catalog.genre"/>:
                        </b>
                            ${formVO.bookVO.book.genre}
                    </p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="catalog.author"/>: </b>
                        <c:forEach var="author" items="${formVO.bookVO.authors}" varStatus="status">
                            ${author.name} ${author.surname}
                            <c:if test="${not status.last}">,</c:if>
                        </c:forEach>
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="catalog.year"/>: </b>
                            ${formVO.bookVO.book.year}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="book.receival.type"/>: </b>
                            ${formVO.form.receivalType}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="book.receival.date"/>: </b>
                            ${formVO.form.receivalDate}
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="book.return.date"/>: </b>
                            ${formVO.form.returnDate}
                    </li>
                </ul>
                <div class="card-body">
                    <button class="btn btn-primary return-book" id="${formVO.bookVO.book.bookID}">
                        <fmt:message bundle="${i18n}" key="book.return"/>
                    </button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>