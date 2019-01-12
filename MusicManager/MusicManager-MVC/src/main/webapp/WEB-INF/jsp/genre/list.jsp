<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!--
* This class represents a genre/list body
* @author Yehor Safonov; 487596
*-->

<!-- Template definition -->
<my:template title="Genres">
<jsp:attribute name="body">

<!-- Body Content -->
<div id="backgroundpage">
    <!-- First container -->
   <div class="d-flex h-100 w-100" id="page-content-wrapper">
      <div class="container-fluid">

          <!-- Navigation bar container -->
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="/pa165/genre/list/">Genre</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
               <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                  <li class="nav-item active">
                     <a class="nav-link ml-2" href="#" id="menu-toggle">Menu <span class="sr-only">(current)</span></a>
                  </li>
                   <li class="nav-item active">
                       <a class="nav-link ml-2" href="/pa165/genre/new">Create genre <span class="sr-only">(current)</span></a>
                   </li>
               </ul>
               <form class="form-inline">
                  <input  id="search-name" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                  <a id="search-button" href="/pa165/genre/list/" href="#menu-toggle" class="btn btn-primary js-scroll-trigger searchbutton" >Find</a>
               </form>
            </div>
         </nav>
          <!-- End navigation bar container -->
          <!-- End first container -->

          <!-- Second container -->
          <div  class="tab navbar navbar-expand-lg navbar-light mt-4">
                       <div class="d-flex w-100 p-4 align-items-center">
                           <!-- Container items -->
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th class="buttontd">All songs</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${genres}" var="genre">
                                  <tr>
                                      <td>${genre.id}</td>
                                      <td>
                                          <c:out value="${genre.name}"/>
                                      </td>
                                      <td>
                                          <c:out value="${genre.description}"/>
                                      </td>
                                      <td class="buttontd">
                                          <a id="search-allsong-button" href="/pa165/genre/songlist/${genre.id}" class="btn btn-primary js-scroll-trigger searchbutton" >Find</a>
                                      </td>
                                      <td>
                                      <form method="get" action="${pageContext.request.contextPath}/genre/update/${genre.id}">
                                         <button type="submit" class="btn btn-primary">Update</button>
                                      </form>
                                      </td>
                                      <td>
                                      <form method="post" action="${pageContext.request.contextPath}/genre/delete/${genre.id}">
                                         <button type="submit" class="btn btn-primary">Delete</button>
                                      </form>
                                      </td>
                                  </tr>
                               </c:forEach>
                                    </tbody>
                                </table>
                           <!-- End container items -->
               </div>
              <!-- End second container -->
         </div>
      </div>
   </div>
</div>


</jsp:attribute>
</my:template>
<!-- End template definition -->