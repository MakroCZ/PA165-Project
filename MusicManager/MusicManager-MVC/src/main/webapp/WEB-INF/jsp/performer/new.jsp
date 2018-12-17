<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:template title="Performers">
    <jsp:attribute name="body">

        <div id="backgroundpage">
            <!-- First container -->
            <div class="d-flex h-100 w-100" id="page-content-wrapper">
                <div class="container-fluid">
                    <!-- Navigation bar container -->
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/performer/list"><fmt:message key="performer.new.title"/></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                                <li class="nav-item active">
                                    <a class="nav-link ml-2" href="#" id="menu-toggle">Menu<span class="sr-only">(current)</span></a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <!-- End navigation bar container -->
                    <!-- End first container -->

                    <!-- Second container -->
                    <div  class="tab navbar navbar-expand-lg navbar-light mt-4">
                        <div class="d-flex w-100 p-4 align-items-center">
                            <!-- Container items -->
                            <form:form method="post" action="${pageContext.request.contextPath}/performer/create"
                                       modelAttribute="performerCreate" cssClass="form-horizontal">
                                <div class="form-group ${name_error?'has-error':''}">
                                    <form:label path="name" cssClass="col-sm-2 control-label"><fmt:message key="entity.performer.name"/></form:label>
                                        <div class="col-sm-10">
                                        <form:input path="name" cssClass="form-control"/>
                                        <form:errors path="name" cssClass="help-block"/>
                                    </div>
                                </div>
                                <div class="form-group ${country_error?'has-error':''}">
                                    <form:label path="country" cssClass="col-sm-2 control-label"><fmt:message key="entity.performer.country"/></form:label>
                                        <div class="col-sm-10">
                                        <form:input path="country" cssClass="form-control"/>
                                        <form:errors path="country" cssClass="help-block"/>
                                    </div>
                                </div>

                                <div class="form-group ${startDate_error?'has-error':''}">
                                    <form:label path="startDate" cssClass="col-sm-2 control-label"><fmt:message key="entity.performer.startDate"/></form:label>
                                        <div class="col-sm-10">
                                        <form:input path="startDate" type="date" class="inputdate" cssClass="form-control"/>
                                        <form:errors path="startDate" type="date" cssClass="help-block"/>
                                    </div>
                                </div>

                                <button class="btn btn-primary" type="submit"><fmt:message key="performer.new.confirm"/></button>
                            </form:form>
                            <!-- End container items -->
                        </div>
                        <!-- End second container -->
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</my:template>