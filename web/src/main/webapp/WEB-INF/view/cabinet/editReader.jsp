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

<div class="modal-dialog modal-sm">
    <div class="modal-content">
        <br>
        <div class="modal-body">
            <div id="myTabContent" class="tab-content">

                <div role="tabpanel" class="tab-pane fade in active show" id="signup">
                    <p><b><spring:message code="menu.edit"/></b></p>

                    <!-- Edit Reader Form -->

                    <form class="form-horizontal"
                          action="${pageContext.request.contextPath}/reader/edit/${sreader.readerID}" method="post"
                          data-toggle="validator">
                        <fieldset>
                            <!-- edit reader Form -->
                            <!-- email-->
                            <div class="form-group">
                                <label class="control-label" for="em"><spring:message
                                        code="login.email"/>:</label>
                                <div class="controls">
                                    <input type="email" id="em" name="em" class="form-control input-large"
                                           placeholder=${sreader.email}
                                                   data-pattern-error="<spring:message  code='data.non-valid'/>">
                                    <small class=" form-text text-muted help-block with-errors"></small>
                                </div>
                            </div>

                            <!--password-->
                            <div class="form-group">
                                <label class="control-label" for="pass"><spring:message
                                        code="login.password"/>:</label>
                                <div class="controls">
                                    <input id="pass" name="pass" class="form-control input-large"
                                           type="password" placeholder="********"
                                           data-pattern-error="<spring:message  code='data.non-valid'/>"
                                           pattern=".{6,30}">
                                    <small class=" form-text text-muted help-block with-errors"><spring:message
                                            code='data.6-30'/>
                                    </small>
                                </div>
                            </div>

                            <br>
                            <!-- surname-->
                            <div class="form-group">
                                <label class="control-label" for="surname">
                                    <spring:message code="login.surname"/>:
                                </label>
                                <div class="controls">
                                    <input id="surname" placeholder="${sreader.surname}"
                                           class="form-control input-large" name="surname"
                                           data-pattern-error="<spring:message  code='data.non-valid'/>"
                                           pattern="^[А-ЯЁ]([a-яё]){0,29}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <spring:message code='data.less-30'/>
                                </small>
                            </div>


                            <!-- name-->
                            <div class="form-group">
                                <label class="control-label" for="name"><spring:message
                                        code="login.name"/>:</label>
                                <div class="controls">
                                    <input id="name" placeholder="${sreader.name}" class="form-control input-large"
                                           name="name"
                                           data-pattern-error="<spring:message  code='data.non-valid'/>"
                                           pattern="^[А-ЯЁ][a-яё]{0,29}$">
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
                                    <input id="secondname" placeholder="${sreader.secondName}"
                                           class="form-control input-large" name="secondname"
                                           data-pattern-error="<spring:message  code='data.non-valid'/>"
                                           pattern="^[А-ЯЁ][a-яё]{0,29}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <spring:message code='data.less-30'/>
                                </small>
                            </div>

                            <br>

                            <!-- birthday-->
                            <div class="form-group">
                                <label class="control-label" for="birthday"><spring:message
                                        code="login.birthday"/>:</label>
                                <div class="controls">
                                    <input value="${sreader.birthday}" id="birthday" class="form-control input-large"
                                           name="birthday"
                                           type="Date" max="2000-01-01" value="1999-05-09"
                                           data-required-error="<spring:message  code='data.required'/>">
                                </div>
                                <small class="form-text text-muted help-block with-errors">
                                </small>
                            </div>


                            <!-- gender-->
                            <div class="control-group">
                                <label class="control-label" for="gender">
                                    <spring:message code="login.gender"/>:
                                </label>
                                <div class="controls">
                                    <select class="form-control input-large" name="gender" id="gender">
                                        <c:choose>
                                            <c:when test="${sreader.gender eq 'male'}">
                                                <option value="1" selected>
                                                    <spring:message code="login.gender.male"/>
                                                </option>
                                                <option value="2">
                                                    <spring:message code="login.gender.female"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="1">
                                                    <spring:message code="login.gender.male"/>
                                                </option>
                                                <option value="2" selected>
                                                    <spring:message code="login.gender.female"/>
                                                </option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </div>
                            </div>

                            <!--button-->
                            <div class="control-group">
                                <label class="control-label"></label>
                                <div class="controls">
                                    <input type="submit" class="btn btn-success"
                                           value="<spring:message code="cabinet.edit"/>"/>
                                    <input type="submit" class="btn btn-secondary"
                                           value="<spring:message code="login.close"/>"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>