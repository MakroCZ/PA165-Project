<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template title="Albums">
<jsp:attribute name="body">

<!-- Page Content -->
<div id="backgroundpage">
    <div class="d-flex h-100 w-100" id="page-content-wrapper">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand">Album</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a class="nav-link ml-2" href="#" id="menu-toggle">Menu <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link ml-2" href="${pageContext.request.contextPath}/album/new">Create album <span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                    <form class="form-inline">
                        <input id="search-name" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <a id="search-button" href="${pageContext.request.contextPath}/album/list/" class="btn btn-primary js-scroll-trigger searchbutton">Find</a>
                    </form>
                </div>
            </nav>
            <div class="tab navbar navbar-expand-lg navbar-light mt-4">
                <div class="d-flex w-100 p-4 align-items-center">

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Date</th>
                            <th scope="col">Performer</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${albums}" var="album">
                        <tr>
                            <td>${album.name}</td>
                            <td>${album.releaseDate}</td>
                            <td>${album.performer.name}</td>
                            <td>
                                <my:a href="/album/detail/${album.id}" class="btn btn-primary">Detail</my:a>
                            </td>
                            <td>
                                <my:a href="/album/edit/${album.id}" class="btn btn-primary">Edit</my:a>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/album/delete/${album.id}">
                                    <button type="submit" class="btn btn-primary">Delete</button>
                                </form>
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

</jsp:attribute>
</my:template>