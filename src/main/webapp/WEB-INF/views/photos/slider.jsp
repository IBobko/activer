<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--<html>--%>
<%--<head>--%>

<%--<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js"/>"></script>--%>
<%--<script type="text/javascript" src="<c:url value="/resources/yoxview/yoxview-init.js"/>"></script>--%>

<%--</head>--%>
<%--<body>--%>
<%--<div class="yoxview">--%>
<%--<c:forEach items="${photos}" var="photo">--%>
<%--<a href="${staticFiles}/${photo.middlePath}."><img style="width:200px; height:130px" src="${staticFiles}/${photo.middlePath}." alt="First" title="First image" /></a>--%>
<%--</c:forEach>--%>
<%--</div>--%>

<%--<script>--%>
<%--$(function(){--%>
<%--$(".yoxview").yoxview();--%>
<%--});--%>

<%--</script>--%>
<%--</body>--%>
<%--</html>--%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>YoxView demo - Basic usage</title>
    <link rel="Stylesheet" type="text/css" href="style.css"/>
    <script type="text/javascript" src="<c:url value="/resources/yoxview/yoxview-init.js"/>"></script>
    <script type="text/javascript">

        jQuery.noConflict();
        jQuery(document).ready(function () {

            jQuery(".yoxview").yoxview(
                    {
                        backgroundColor: '#000000',
                        backgroundOpacity: 0.8,
                        lang: 'ru',
                    });

        });


        $(document).ready(function () {
            $(".yoxview").yoxview();
        });
    </script>
</head>
<body>
<div id="container">
    <h1>YoxView demo - Basic usage</h1>

    Click on the thumbnails to open YoxView:

    <!-- This is how your thumbnails should look like: -->
    <%--<div class="thumbnails yoxview">--%>
    <%--<c:forEach items="${photos}" var="photo">--%>
    <%--<a href="${staticFiles}/${photo.middlePath}."><img style="width:200px; height:130px" src="${staticFiles}/${photo.middlePath}." alt="First" title="First image" /></a>--%>
    <%--</c:forEach>--%>
    <%--</div>--%>


    <div class="thumbnails yoxview">
        <c:forEach items="${photos}" var="photo">
            <a href="${staticFiles}/${photo.path}.jpg"><img style="width:200px; height:130px"
                                                            src="${staticFiles}/${photo.middlePath.trim()}.jpg"
                                                            alt="First" title="The first image"/></a>
        </c:forEach>
    </div>
    <!-- The thumbnails end here -->
    <hr/>
    <br/>
    For more examples, go to <a href="http://www.yoxigen.com/yoxview/usage.aspx">the Yoxview usage page</a>.
</div>
</body>
</html>