<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New genre">
<jsp:attribute name="body">
  <div id="backgroundpage">
      <div class="d-flex h-100 w-100" id="page-content-wrapper">
          <div class="container-fluid">
              <a href="#menu-toggle" class="btn btn-primary js-scroll-trigger" id="menu-toggle">Menu</a>
              <form:form method="post" action="${pageContext.request.contextPath}/genre/create"
                         modelAttribute="genreCreate" cssClass="form-horizontal">
             <div class="form-group ${name_error?'has-error':''}">
                 <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                 <div class="col-sm-10">
                     <form:input path="name" cssClass="form-control"/>
                     <form:errors path="name" cssClass="help-block"/>
                 </div>
             </div>
             <div class="form-group ${description_error?'has-error':''}">
                 <form:label path="description" cssClass="col-sm-2 control-label">Description</form:label>
                 <div class="col-sm-10">
                     <form:textarea cols="80" rows="20" path="description" cssClass="form-control"/>
                     <form:errors path="description" cssClass="help-block"/>
                 </div>
             </div>

            <button class="btn btn-primary" type="submit">Create product</button>
            </form:form>
          </div>
      </div>
  </div>
</jsp:attribute>
</my:pagetemplate>