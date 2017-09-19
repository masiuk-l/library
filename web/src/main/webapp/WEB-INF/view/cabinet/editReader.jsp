<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>

<c:if test="${not empty errorMsg and not (errorMsg eq '')}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <i class="fa fa-times-circle-o"></i>
        </button>
        <fmt:message bundle="${i18n}" key="data.invalid-rerty"/>
    </div>
</c:if>

<div class="modal-dialog modal-sm">
    <div class="modal-content">
        <br>
        <div class="modal-body">
            <div id="myTabContent" class="tab-content">

                <div role="tabpanel" class="tab-pane fade in active show" id="signup">
                    <p><b><fmt:message bundle="${i18n}" key="menu.edit"/></b></p>

                    <!-- Edit Reader Form -->

                    <form class="form-horizontal" action="frontController?command=editreader" method="post"
                          data-toggle="validator">
                        <fieldset>
                            <!-- edit reader Form -->
                            <!-- email-->
                            <div class="form-group">
                                <label class="control-label" for="em"><fmt:message bundle="${i18n}"
                                                                                   key="login.email"/>:</label>
                                <div class="controls">
                                    <input type="email" id="em" name="em" class="form-control input-large"
                                           placeholder=${sreader.email}
                                                   data-pattern-error="<fmt:message bundle='${i18n}' key='data.non-valid'/>">
                                    <small class=" form-text text-muted help-block with-errors"></small>
                                </div>
                            </div>

                            <!--password-->
                            <div class="form-group">
                                <label class="control-label" for="pass"><fmt:message bundle="${i18n}"
                                                                                     key="login.password"/>:</label>
                                <div class="controls">
                                    <input id="pass" name="pass" class="form-control input-large"
                                           type="password" placeholder="********"
                                           data-pattern-error="<fmt:message bundle='${i18n}' key='data.non-valid'/>"
                                           pattern=".{6,30}">
                                    <small class=" form-text text-muted help-block with-errors"><fmt:message
                                            bundle='${i18n}' key='data.6-30'/>
                                    </small>
                                </div>
                            </div>

                            <br>
                            <!-- surname-->
                            <div class="form-group">
                                <label class="control-label" for="surname">
                                    <fmt:message bundle="${i18n}" key="login.surname"/>:
                                </label>
                                <div class="controls">
                                    <input id="surname" placeholder="${sreader.surname}"
                                           class="form-control input-large" name="surname"
                                           data-pattern-error="<fmt:message bundle='${i18n}' key='data.non-valid'/>"
                                           pattern="^[А-ЯЁ]([a-яё]){0,29}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <fmt:message bundle='${i18n}' key='data.less-30'/>
                                </small>
                            </div>


                            <!-- name-->
                            <div class="form-group">
                                <label class="control-label" for="name"><fmt:message bundle="${i18n}"
                                                                                     key="login.name"/>:</label>
                                <div class="controls">
                                    <input id="name" placeholder="${sreader.name}" class="form-control input-large"
                                           name="name"
                                           data-pattern-error="<fmt:message bundle='${i18n}' key='data.non-valid'/>"
                                           pattern="^[А-ЯЁ][a-яё]{0,29}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <fmt:message bundle='${i18n}' key='data.less-30'/>
                                </small>
                            </div>

                            <!-- second name-->
                            <div class="form-group">
                                <label class="control-label" for="secondname"><fmt:message bundle="${i18n}"
                                                                                           key="login.secondname"/>:</label>
                                <div class="controls">
                                    <input id="secondname" placeholder="${sreader.secondName}"
                                           class="form-control input-large" name="secondname"
                                           data-pattern-error="<fmt:message bundle='${i18n}' key='data.non-valid'/>"
                                           pattern="^[А-ЯЁ][a-яё]{0,29}$">
                                </div>
                                <small class=" form-text text-muted help-block with-errors">
                                    <fmt:message bundle='${i18n}' key='data.less-30'/>
                                </small>
                            </div>

                            <br>

                            <!-- birthday-->
                            <div class="form-group">
                                <label class="control-label" for="birthday"><fmt:message bundle="${i18n}"
                                                                                         key="login.birthday"/>:</label>
                                <div class="controls">
                                    <input value="${sreader.birthday}" id="birthday" class="form-control input-large"
                                           name="birthday"
                                           type="Date" max="2000-01-01" value="1999-05-09"
                                           data-required-error="<fmt:message bundle='${i18n}' key='data.required'/>">
                                </div>
                                <small class="form-text text-muted help-block with-errors">
                                </small>
                            </div>


                            <!-- gender-->
                            <div class="control-group">
                                <label class="control-label" for="gender">
                                    <fmt:message bundle="${i18n}" key="login.gender"/>:
                                </label>
                                <div class="controls">
                                    <select class="form-control input-large" name="gender" id="gender">
                                        <c:choose>
                                            <c:when test="${sreader.gender eq 'male'}">
                                                <option value="1" selected>
                                                    <fmt:message bundle="${i18n}" key="login.gender.male"/>
                                                </option>
                                                <option value="2">
                                                    <fmt:message bundle="${i18n}" key="login.gender.female"/></option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="1">
                                                    <fmt:message bundle="${i18n}" key="login.gender.male"/>
                                                </option>
                                                <option value="2" selected>
                                                    <fmt:message bundle="${i18n}" key="login.gender.female"/>
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
                                           value="<fmt:message bundle="${i18n}" key="cabinet.edit"/>"/>
                                    <input type="submit" class="btn btn-secondary"
                                           value="<fmt:message bundle="${i18n}" key="login.close"/>"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>