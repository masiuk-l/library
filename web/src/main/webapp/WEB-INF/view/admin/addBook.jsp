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
                <div role="tabpanel" class="tab-pane fade in active show" id="addbook">
                    <p><b><spring:message code="admin.add.book"/></b></p>
                    <form class="form-horizontal" action="${pageContext.request.contextPath}/book/add" method="post"
                          data-toggle="validator">
                        <fieldset>
                            <!-- Add Book Form -->
                            <!-- name-->
                            <div class="form-group">
                                <label class="control-label" for="name">
                                    <spring:message code="book.name"/>:
                                </label>
                                <div class="controls">
                                    <input id="name" name="name" class="form-control input-large"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           data-required-error="<spring:message code='data.required'/>"
                                           required pattern="^.{1,29}$">
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
                                            <option value="${author.authorID}">${author.surname} ${author.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <small class="form-text text-muted">
                                    <a href="#addauthor" data-toggle="modal" data-target=".bs-modal-sm">
                                        <spring:message code="author.add"/>
                                    </a>
                                </small>

                            </div>

                            <br>
                            <!--isbn-->
                            <div class="form-group">
                                <label class="control-label" for="isbn">
                                    <spring:message code="catalog.isbn"/>:
                                </label>
                                <div class="controls">
                                    <input id="isbn" class="form-control input-large" name="isbn"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           data-required-error="<spring:message code='data.required'/>"
                                           required pattern="^[0-9\\-]{1,12}$">
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
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           data-required-error="<spring:message code='data.required'/>"
                                           required pattern="^.{1,29}$">
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
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           data-required-error="<spring:message code='data.required'/>"
                                           required pattern="^[0-9]{4}$">
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
                                    <input type="number" value="1" min="1" max="999" id="quantity"
                                           class="form-control input-large" name="quantity"
                                           data-pattern-error="<spring:message code='data.non-valid'/>"
                                           data-required-error="<spring:message code='data.required'/>"
                                           required>
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                </small>
                            </div>


                            <!--button-->
                            <div class="form-group">
                                <label class="control-label"></label>
                                <div class="controls">
                                    <input type="submit" class="btn btn-success"
                                           value="<spring:message code="addbook.title"/>"/>
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


<!-- Modal -->
<div class="modal fade bs-modal-sm" id="Modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog ">
        <div class="modal-content">
            <div class="modal-body">
                <div id="TabContent" class="tab-content">
                    <div role="tabpanel" class="tab-pane fade in active show" id="addauthor">
                        <p><b>Добавление книги</b></p>
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/author/add"
                              method="post"
                              data-toggle="validator">
                            <fieldset>
                                <!-- Add Author Form -->
                                <!-- surname-->
                                <div class="form-group">
                                    <label class="control-label" for="surname">
                                        <spring:message code="login.surname"/>:
                                    </label>
                                    <div class="controls">
                                        <input id="surname" class="form-control input-large" name="surname"
                                               data-pattern-error="<spring:message code='data.non-valid'/>"
                                               data-required-error="<spring:message code='data.required'/>"
                                               required pattern="^[А-ЯЁ]([a-яё]){0,29}$">
                                    </div>
                                    <small class=" form-text text-muted help-block with-errors">
                                        <spring:message code='data.less-30'/>
                                    </small>
                                </div>

                                <!-- name-->
                                <div class="form-group">
                                    <label class="control-label" for="authorname"><spring:message
                                            code="login.name"/>:</label>
                                    <div class="controls">
                                        <input id="authorname" class="form-control input-large" name="name"
                                               data-pattern-error="<spring:message code='data.non-valid'/>"
                                               data-required-error="<spring:message code='data.required'/>"
                                               required pattern="^[А-ЯЁ][a-яё]{0,29}$">
                                    </div>
                                    <small class=" form-text text-muted help-block with-errors">
                                        <spring:message code='data.less-30'/>
                                    </small>
                                </div>

                                <!-- second name-->
                                <div class="form-group">
                                    <label class="control-label" for="secondname"><spring:message
                                            code="login.secondname"/>:</label>
                                    <div class="controls">
                                        <input id="secondname" class="form-control input-large" name="secondname"
                                               data-pattern-error="<spring:message code='data.non-valid'/>"
                                               data-required-error="<spring:message code='data.required'/>"
                                               required pattern="^[А-ЯЁ][a-яё]{0,29}$">
                                    </div>
                                    <small class=" form-text text-muted help-block with-errors">
                                        <spring:message code='data.less-30'/>
                                    </small>
                                </div>


                                <!-- birthday -->
                                <div class="form-group">
                                    <label class="control-label" for="birthday"><spring:message
                                            code="login.birthday"/>:</label>
                                    <div class="controls">
                                        <input id="birthday" class="form-control input-large" name="birthday"
                                               type="Date" max="2000-01-01" value="1999-05-09"
                                               data-required-error="<spring:message code='data.required'/>"
                                               required>
                                    </div>
                                    <small class=" form-text text-muted help-block with-errors">
                                    </small>
                                </div>

                                <!-- country-->
                                <div class="form-group">
                                    <label class="control-label" for="country">
                                        <spring:message code="author.country"/>:
                                    </label>
                                    <div class="controls">
                                        <input id="country" name="country" class="form-control input-medium"
                                               data-pattern-error="<spring:message code='data.non-valid'/>"
                                               data-required-error="<spring:message code='data.required'/>"
                                               required pattern="^[А-ЯЁ][a-яё]{0,29}$">
                                    </div>
                                    <small class=" form-text text-muted help-block with-errors">
                                        <spring:message code='data.less-30'/>
                                    </small>
                                </div>

                                <!-- submit -->
                                <div class="form-group">
                                    <label class="control-label"></label>
                                    <div class="controls">
                                        <input type="submit" class="btn btn-success"
                                               value="<spring:message code='author.add'/>"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <spring:message code="login.close"/>
                </button>
            </div>
        </div>
    </div>
</div>
