<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="New album">
 <jsp:attribute name="body">

 <!-- Page Content -->
 <div id="backgroundpage">
     <div class="d-flex h-100 w-100" id="page-content-wrapper">
         <div class="container-fluid">
             <nav class="navbar navbar-expand-lg navbar-light bg-light">
                 <a class="navbar-brand" href="${pageContext.request.contextPath}/album/list">New album</a>
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
             <div class="tab navbar navbar-expand-lg navbar-light mt-4">
                 <div class="d-flex h-100">
                     <div class="container-fluid">
                      <form:form method="post" action="${pageContext.request.contextPath}/album/create" modelAttribute="albumCreate" cssClass="form-horizontal">
                         <div class="form-group ${name_error?'has-error':''}">
                             <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                             <div class="col-sm-10">
                                 <form:input path="name" cssClass="form-control"/>
                                 <form:errors path="name" cssClass="help-block"/>
                             </div>
                         </div>
                          <div class="form-group">
                              <form:label path="performerId" cssClass="col-sm-2 control-label">Performer</form:label>
                              <div class="col-sm-10">
                                  <form:select path="performerId" cssClass="form-control">
                                      <c:forEach items="${performers}" var="c">
                                          <form:option value="${c.id}">${c.name}</form:option>
                                      </c:forEach>
                                  </form:select>
                                  <p class="help-block"><form:errors path="performerId" cssClass="error"/></p>
                              </div>
                          </div>
                              <div class="form-group ${date_error?'has-error':''}">
                                    <form:label path="releaseDate" cssClass="col-sm-2 control-label">Date</form:label>
                                        <div class="col-sm-10">
                                        <form:input path="releaseDate" type="date" class="inputdate" cssClass="form-control"/>
                                        <form:errors path="releaseDate" type="date" cssClass="help-block"/>
                                    </div>
                                </div>
                          <button class="btn btn-primary nt-2 ml-2" type="submit">Create album</button>
                          </form:form>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>

</jsp:attribute>
</my:template>