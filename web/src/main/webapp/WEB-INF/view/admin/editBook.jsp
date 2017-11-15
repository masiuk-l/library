<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<c:if test="${not empty errorMsg and not (errorMsg eq '')}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <i class="fa fa-times-circle-o"></i>
        </button>
        <spring:message code="data.invalid-rerty"/>
    </div>
</c:if>

<div class="modal-dialog ">
    <div class="modal-content">
        <div class="modal-body">
            <div id="myTabContent" class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active show" id="editbook">
                    <p><b><spring:message code="editBook.title"/></b></p>
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/book/edit/${book.bookID}"
                          method="post"
                          data-toggle="validator">
                        <fieldset>
                            <!-- Edit Book Form -->
                            <!-- name-->
                            <div class="form-group">
                                <label class="control-label" for="name">
                                    <spring:message code="book.name"/>:
                                </label>
                                <div class="controls">
                                    <input id="name" name="name" placeholder="${book.name}"
                                           class="form-control input-large"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           pattern="^.{1,29}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <spring:message code='data.less-30'/>
                                </small>
                            </div>

                            <!--author(s)-->
                            <div class="form-group">
                                <label class="control-label" for="author">
                                    <spring:message code="catalog.author"/>:
                                </label>
                                <div class="controls">
                                    <select multiple class="form-control input-large" name="author" id="author"
                                            required
                                            data-required-error="<spring:message code='data.required'/>">
                                        <c:forEach var="author" items="${authors}">
                                            <c:set var="flag" value="false"/>
                                            <c:forEach var="featuredAuthor" items="${book.authors}">
                                                <c:if test="${author.authorID eq featuredAuthor.authorID}">
                                                    <c:set var="flag" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:choose>
                                                <c:when test="${flag eq true}">
                                                    <option selected
                                                            value="${author.authorID}">${author.surname} ${author.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${author.authorID}">${author.surname} ${author.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <spring:message code='data.less-30'/>
                                </small>
                                <%--<small class="form-text text-muted">--%>
                                <%--<a href="#addauthor" data-toggle="modal" data-target=".bs-modal-sm">--%>
                                <%--<spring:message code="author.add"/>--%>
                                <%--</a>--%>
                                <%--</small>--%>

                            </div>

                            <br>
                            <!--isbn-->
                            <div class="form-group">
                                <label class="control-label" for="isbn">
                                    <spring:message code="catalog.isbn"/>:
                                </label>
                                <div class="controls">
                                    <input id="isbn" class="form-control input-large" placeholder="${book.isbn}"
                                           name="isbn"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           pattern="^[0-9\\-]{1,12}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <spring:message code='data.less-12'/>
                                </small>
                            </div>


                            <!--genre-->
                            <div class="form-group">
                                <label class="control-label" for="genre">
                                    <spring:message code="catalog.genre"/>:
                                </label>
                                <div class="controls">
                                    <input id="genre" class="form-control input-large" name="genre"
                                           placeholder="${book.genre}"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           pattern="^.{1,30}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">

                                </small>
                            </div>

                            <!--year-->
                            <div class="form-group">
                                <label class="control-label" for="year">
                                    <spring:message code="catalog.year"/>:
                                </label>
                                <div class="controls">
                                    <input id="year" class="form-control input-large" name="year"
                                           placeholder="${book.year}"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           pattern="^[0-9]{4}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                </small>
                            </div>

                            <!--quantity-->
                            <div class="form-group">
                                <label class="control-label" for="quantity">
                                    <spring:message code="catalog.quantity"/>:
                                </label>
                                <div class="controls">
                                    <input type="number" value="${book.quantity}" min="1" max="999" id="quantity"
                                           class="form-control input-large" name="quantity"
                                           data-pattern-error="<spring:message code='data.non-valid'/>">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                </small>
                            </div>


                            <!--button-->
                            <div class="form-group">
                                <label class="control-label"></label>
                                <div class="controls">
                                    <input type="submit" class="btn btn-success"
                                           value="<spring:message code="editBook.title"/>"/>
                                    <a href="${pageContext.request.contextPath}/main/"
                                       class="btn btn-secondary">
                                        <spring:message code="login.close"/>
                                    </a>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


