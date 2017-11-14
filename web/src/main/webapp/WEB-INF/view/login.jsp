<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<!-- Button trigger modal -->
<c:if test="${not empty errorMsg and not (errorMsg eq '')}">
    <div class="alert alert-warning alert-dismissible fade show" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <i class="fa fa-times-circle-o"></i>
        </button>
        <c:choose>
            <c:when test="${errorMsg eq 'Invalid Login or Password'}">
                <spring:message code="data.lp"/>
            </c:when>
            <c:otherwise>
                <spring:message code="data.invalid-rerty"/>
            </c:otherwise>
        </c:choose>

    </div>
</c:if>

<div class="container">
    <hr class="prettyline">
    <br>
    <div class="text-center">
        <h3><spring:message code="login.continue"/></h3>
        <br>
        <a class="btn btn-primary btn-lg" href="#signin" data-toggle="modal" data-target=".bs-modal-sm">
            <spring:message code="login.signin"/>/<spring:message code="login.signup"/>
        </a>
    </div>
    <br>
    <hr class="prettyline">
</div>


<!-- Modal -->
<div class="modal fade bs-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content">
            <br>
            <div class="bs-example bs-example-tabs">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" href="#signin" role="tab" data-toggle="tab"><spring:message
                                code="login.signin"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#signup" role="tab" data-toggle="tab"><spring:message
                                code="login.signup"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#lib" role="tab" data-toggle="tab"><spring:message
                                code="login.librarians"/></a>
                    </li>
                </ul>
            </div>


            <div class="modal-body">
                <div id="myTabContent" class="tab-content">

                    <div role="tabpanel" class="tab-pane fade in active show" id="signin">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/login/reader"
                              method="post"
                              data-toggle="validator">
                            <fieldset>
                                <!-- Sign In Form -->
                                <!-- email-->
                                <div class="form-group">
                                    <label class="control-label" for="log"><spring:message
                                            code="login.email"/>:</label>
                                    <div class="controls">
                                        <input id="log" name="login" type="email" class="form-control input-medium"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required>
                                        <small class=" form-text text-muted help-block with-errors"></small>
                                    </div>
                                </div>

                                <!--password-->
                                <div class="form-group">
                                    <label class="control-label" for="pass"><spring:message
                                            code="login.password"/>:</label>
                                    <div class="controls">
                                        <input id="pass" name="password"
                                               class="form-control input-medium" type="password" placeholder="********"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required>
                                        <small class=" form-text text-muted help-block with-errors">
                                        </small>
                                    </div>
                                </div>

                                <!-- submit -->
                                <div class="form-group">
                                    <label class="control-label"></label>
                                    <div class="controls">
                                        <input type="submit" class="btn btn-success"
                                               value="<spring:message code="login.signin"/>"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>


                    <div role="tabpanel" class="tab-pane fade" id="signup">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/login/signup"
                              method="post"
                              data-toggle="validator" role="form">
                            <fieldset>
                                <!-- Sign Up Form -->
                                <!-- email-->
                                <div class="form-group">
                                    <label class="control-label" for="email"><spring:message
                                            code="login.email"/>:</label>
                                    <div class="controls">
                                        <input type="email" id="email" name="email" class="form-control input-large"
                                               placeholder="JohnDoe@example.com"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required>
                                        <small class=" form-text text-muted help-block with-errors"></small>
                                    </div>
                                </div>

                                <!--password-->
                                <div class="form-group">
                                    <label class="control-label" for="password"><spring:message
                                            code="login.password"/>:</label>
                                    <div class="controls">
                                        <input id="password" name="password" class="form-control input-large"
                                               type="password" placeholder="********"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required pattern=".{6,30}">
                                        <small class=" form-text text-muted help-block with-errors"><spring:message
                                                code='data.6-30'/>
                                        </small>
                                    </div>
                                </div>

                                <!-- password2-->
                                <div class="form-group has-danger">
                                    <label class="control-label" for="reenterpassword"><spring:message
                                            code="login.repassword"/>:</label>
                                    <div class="controls">
                                        <input id="reenterpassword" class="form-control input-large"
                                               data-match="#password"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               data-match-error="<spring:message  code='data.no-match'/>"
                                               name="reenterpassword" type="password" placeholder="********"
                                               required pattern=".{6,30}">
                                        <small class=" form-text text-muted help-block with-errors"></small>
                                    </div>

                                </div>

                                <br>
                                <!-- surname-->
                                <div class="form-group">
                                    <label class="control-label" for="surname">
                                        <spring:message code="login.surname"/>:
                                    </label>
                                    <div class="controls">
                                        <input id="surname" class="form-control input-large" name="surname"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required pattern="^[А-ЯЁ]([a-яё]){0,29}$">
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
                                        <input id="name" class="form-control input-large" name="name"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
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
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required pattern="^[А-ЯЁ][a-яё]{0,29}$">
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
                                        <input id="birthday" class="form-control input-large" name="birthday"
                                               type="Date" max="2000-01-01" value="1999-05-09"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required>
                                    </div>
                                    <small class=" form-text text-muted help-block with-errors">
                                    </small>
                                </div>

                                <!-- gender-->
                                <div class="form-group">
                                    <label class="control-label" for="gender"><spring:message
                                            code="login.gender"/>:</label>
                                    <div class="controls">
                                        <select class="form-control input-large" name="gender" id="gender" required>
                                            <option value="1"><spring:message
                                                    code="login.gender.male"/></option>
                                            <option value="2"><spring:message
                                                    code="login.gender.female"/></option>
                                        </select>
                                    </div>
                                </div>

                                <!--button-->
                                <div class="form-group">
                                    <label class="control-label"></label>
                                    <div class="controls">
                                        <input type="submit" class="btn btn-success"
                                               value="<spring:message code="login.signup"/>"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>

                    <div role="tabpanel" class="tab-pane fade" id="lib">
                        <form class="form-horizontal" action="${pageContext.request.contextPath}/login/lib"
                              method="post">
                            <fieldset>
                                <!-- Librarians form -->
                                <div class="form-group">
                                    <label class="control-label" for="loginlib"><spring:message
                                            code="login.email"/>:</label>
                                    <div class="controls">
                                        <input id="loginlib" name="loginlib" type="email"
                                               class="form-control input-medium"
                                               data-pattern-error="<spring:message  code='data.non-valid'/>"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required>
                                        <small class=" form-text text-muted help-block with-errors"></small>
                                    </div>
                                </div>

                                <!--password-->
                                <div class="form-group">
                                    <label class="control-label" for="passwordlib"><spring:message
                                            code="login.password"/>:</label>
                                    <div class="controls">
                                        <input id="passwordlib" name="passwordlib"
                                               class="form-control input-medium" type="password" placeholder="********"
                                               data-required-error="<spring:message  code='data.required'/>"
                                               required>
                                        <small class=" form-text text-muted help-block with-errors"><spring:message
                                                code='data.6-30'/>
                                        </small>
                                    </div>
                                </div>

                                <!-- submit -->
                                <div class="form-group">
                                    <label class="control-label"></label>
                                    <div class="controls">
                                        <input type="submit" class="btn btn-success"
                                               value="<spring:message code="login.signin"/>"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                        code="login.close"/></button>
            </div>
        </div>
    </div>
</div>
