<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MusicManager</title>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/simple-sidebar.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap-grid.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">


    <jsp:invoke fragment="head"/>
</head>


<body>

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="/musicManager/">
                    Music Manager
                </a>
            </li>
            <li>
                <a href="/musicManager/song/list/">Songs</a>
            </li>
            <li>
                <a href="/musicManager/album/list/">Albums</a>
            </li>
            <li>
                <a href="/musicManager/performer/list/">Performers</a>
            </li>
            <li>
                <a href="/musicManager/genre/list/">Genres</a>
            </li>
            <li>
                <a href="/musicManager/about">About</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->

    <!-- authenticated user info -->
    <c:if test="${not empty authenticatedUser}">
        <div class="row">
            <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <c:out value="${authenticatedUser.givenName} ${authenticatedUser.surname}"/>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->


<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
</body>
</html>
