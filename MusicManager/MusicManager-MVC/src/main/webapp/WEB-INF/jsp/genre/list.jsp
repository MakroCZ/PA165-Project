<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Genres">
<jsp:attribute name="body">


<!-- Page Content -->
<div id="backgroundpage">
   <div class="d-flex h-100 w-100" id="page-content-wrapper">
      <div class="container-fluid">
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Genre</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                  <li class="nav-item active">
                     <a class="nav-link ml-2" href="#" id="menu-toggle">Back to menu <span class="sr-only">(current)</span></a>
                  </li>
               </ul>
               <form class="form-inline">
                  <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                  <a href="#menu-toggle" class="btn btn-primary js-scroll-trigger searchbutton" >Find</a>
               </form>
            </div>
         </nav>
         <div  class="tab navbar navbar-expand-lg navbar-light bg-light mt-4">
            <div class="d-flex h-100 ">
               <div class="container-fluid">

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
      </div>
   </div>
</div>


</jsp:attribute>
</my:template>