<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Genres">
<jsp:attribute name="body">
    <div id="backgroundpage">
        <div class="d-flex h-100 w-100" id="page-content-wrapper">
            <div class="container-fluid">
         <my:a href="/genre/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Create new genre
         </my:a>
                <a href="#menu-toggle" class="btn btn-primary js-scroll-trigger" id="menu-toggle">Menu</a>

                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${genres}" var="category">
                  <tr>
                      <td>${genre.id}</td>
                      <td>
                          <c:out value="${genre.name}"/>
                      </td>
                      <td>
                          <c:out value="${genre.description}"/>
                      </td>
                  </tr>
               </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</jsp:attribute>
</my:pagetemplate>