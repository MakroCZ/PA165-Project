<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="${pageContext.request.locale}" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">

<!--
* This class represents a login.tag object.
* @author Yehor Safonov; 487596
*-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MusicManager</title>

    <!-- Custom styles for this template -->
    <link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">

    <!-- Mapping bootstrap static styles -->
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap-grid.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
</head>

<!-- Login form body -->
<body>

<!-- Login form container -->
<div class="app flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card-group mb-0">
                    <div class="card p-4" id="logincard">
                        <div class="card-body">
                            
                            <h1>Login</h1>
                            <p class="text-muted">Sign in to your Music Manager account</p>
                            
                              <c:if test="${not empty param.error}">
                                <div>
                                    Invalid username and password.
                                </div>
                             </c:if>
                             <c:if test="${not empty param.logout}">
                                <div>
                                    You have been logged out.
                                </div>
                             </c:if>

                            <form th:action="@{/login}" method="post">
                                    <div class="input-group mb-3">
                                        <input
                                                type="text"
                                                name="username"
                                                class="form-control"
                                                placeholder="Username"
                                        >
                                    </div>
                                    <div class="input-group mb-4">
                                        <input
                                                type="password"
                                                name="password"
                                                class="form-control"
                                                placeholder="Password"
                                        >
                                    </div>
                                    <div class="row">
                                        <div class="col-6">
                                            <button type="submit" class="btn btn-primary px-4">Login</button>
                                        </div>
                                    </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
