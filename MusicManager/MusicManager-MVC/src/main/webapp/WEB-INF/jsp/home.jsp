<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:template>
<jsp:attribute name="body">
        <div id="backgroundmain">
            <div class="d-flex h-100 w-100" id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="container d-flex h-100 align-items-center">
                        <div class="mx-auto text-center hometext">
                            <h1 class="mx-auto my-0 text-uppercase masthead">Music Manager</h1>
                            <p  class="desription">"You have to take a deep breath. and allow the music to flow through you. Revel in it, allow yourself to awe. When you play allow the music to break your heart with its beauty"</p>
                            <a href="#menu-toggle" class="btn btn-primary js-scroll-trigger" id="menu-toggle">Start</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
 </jsp:attribute>
</my:template>