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
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap-grid.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">


    <jsp:invoke fragment="head"/>
</head>


<body>

<div class="app flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card-group mb-0">
                    <div class="card p-4" id="logincard">
                        <div class="card-body">
                            <h1 id="logintextheader">Login</h1>
                            <p class="text-muted">Sign in to your Music Manager account</p>
                            <div class="input-group mb-3">

                                <input
                                        class="form-control"
                                        placeholder="Username"
                                        id="inputloginusername"
                                >
                            </div>
                            <div class="input-group mb-4">
                                <input
                                        class="form-control"
                                        placeholder="Password"
                                        id="inputloginpassword"
                                >
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <button type="submit" class="btn btn-primary px-4">Login</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
</body>
</html>
