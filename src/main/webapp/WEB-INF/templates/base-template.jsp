<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value='/search' var="searchUrl"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title"/></title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/photo-upload.css"/>">



    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/activer.photo-upload.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/sockjs-0.3.4.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/stomp.js"/>"></script>

    <script>


        if (window.ACTIVER == undefined) {
            window.ACTIVER = {};
        }

        window.ACTIVER.context_path = "<% out.print(getServletConfig().getServletContext().getContextPath());%>";
        window.ACTIVER.Data = {};
        $.getJSON( "<c:url value="/js/data.json"/>", function( data ) {
            for (var attrname in data) { window.ACTIVER.Data[attrname] = data[attrname]; }
            $(function(){
                $.each(window.ACTIVER,function(i,o){
                    if (o.init != undefined) {
                        o.init();
                    }
                })
            });
        });

    </script>
</head>
<body>

<nav style="margin-top:20px" class="navbar navbar-default">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/profile"/> ">Activer</a>
        <sec:authorize access="isAuthenticated()">
            <a class="navbar-brand" href="<c:url value="/message"/> ">Сообщения</a>
            <a class="navbar-brand" href="<c:url value="/search/people"/> ">Поиск людей</a>
            <a class="navbar-brand" href="<c:url value="/friend"/> ">Друзья</a>
            <div style="float:right">
                <form:form method="get" commandName="searchForm" action="${searchUrl}" cssClass="navbar-form navbar-left">
                    <form:input cssClass="form-control" placeholder="Введите текст поиска" path="s"/>
                    <button type="submit" class="btn btn-default">Искать</button>
                </form:form>

                <a href="/j_spring_security_logout" style="margin-top: 10px">Выход</a>
            </div>
        </sec:authorize>
    </div>
</nav>

<div class="container">
    <tiles:insertAttribute name="content"/>
</div>
<hr/>
<footer style="text-align: center ">
    (c) 2015 Projected by <a href="http://todo100.ru">http://todo100.ru</a><br/>
</footer>
</body>
</html>
