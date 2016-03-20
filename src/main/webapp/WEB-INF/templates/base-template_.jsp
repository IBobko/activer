<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>On\Off Line</title>

    <!-- Favicon -->
    <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&subset=latin,cyrillic' rel='stylesheet' type='text/css'>

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/sidebar.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
    <tiles:insertAttribute name="content"/>
</body>
</html>
