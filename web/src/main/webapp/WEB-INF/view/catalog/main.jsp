<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<div class="container">
    <c:if test="${not empty Msg and not (Msg eq '')}">
        <c:choose>
            <c:when test="${Msg eq 'Invalid input'}">
                <div class="text-center">
                    <h1 class="display-3"><fmt:message bundle="${i18n}" key="data.sorry"/></h1>
                    <h1 class="display-4"><fmt:message bundle="${i18n}" key="data.non-valid"/></h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="text-center">
                    <h1 class="display-3"><fmt:message bundle="${i18n}" key="data.sorry"/></h1>
                    <h1 class="display-4"><fmt:message bundle="${i18n}" key="data.no-such-books"/></h1>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
    <div class="card-deck">

        <c:forEach var="book" items="${books}">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">${book.name}</h4>
                    <p class="card-text">
                        <b>
                            <fmt:message bundle="${i18n}" key="catalog.genre"/>:
                        </b>
                            ${book.genre}
                    </p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="catalog.author"/>: </b>
                        <c:forEach var="author" items="${book.authors}" varStatus="status">
                            ${author.name} ${author.surname}
                            <c:if test="${not status.last}">,</c:if>
                        </c:forEach>
                    </li>
                    <li class="list-group-item">
                        <b><fmt:message bundle="${i18n}" key="catalog.year"/>: </b>
                            ${book.year}</li>
                </ul>
                <c:choose>
                    <c:when test="${not empty slibrarian}">
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/frontController?command=editbook&id=${book.bookID}"
                               class="btn btn-primary">
                                <fmt:message bundle="${i18n}" key="cabinet.edit"/>
                            </a>
                            <a href="${pageContext.request.contextPath}/frontController?command=deletebook&id=${book.bookID}"
                               class="btn btn-danger">
                                <fmt:message bundle="${i18n}" key="book.delete"/>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${(not empty sreader) and (sreader.status eq 'BANNED')}">
                                <div class="card-body">
                                    <a href="#"
                                       class="btn btn-secondary">
                                        <fmt:message bundle="${i18n}" key="reader.banned"/>
                                    </a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/frontController?command=book&id=${book.bookID}"
                                       class="btn btn-primary">
                                        <fmt:message bundle="${i18n}" key="catalog.reserve"/>
                                    </a>
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </c:otherwise>
                </c:choose>

            </div>
        </c:forEach>
    </div>
    <nav>
        <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${pageCount}">
                <c:choose>
                    <c:when test="${page eq pageNumber}">
                        <li class="page-item active"><a class="page-link"
                                                        href="${pageContext.request.contextPath}/frontController?command=catalog&page=${page}&pageSize=3">${page}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/frontController?command=catalog&page=${page}&pageSize=3">${page}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </nav>
</div>