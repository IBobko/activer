<%--suppress ELValidationInJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">

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

</head>

<body>

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <nav class="navbar navbar-default navbar-sidebar">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button id="sidebar-toggle" type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand my-navbar-brand" href="#">
                        <img alt="Brand" src="<c:url value="/resources/img/logo.png"/>">
                    </a>
                </div>
            </div>
        </nav>
        <ul class="sidebar-nav">
            <li class="active">
                <a href="<c:url value="/auth/"/>">
                    <span class="fa fa-user"></span>
                    Авторизация
                </a>
            </li>
            <li>
                <a href="<c:url value="/auth/signup"/>">
                    <span class="fa fa-smile-o"></span>
                    Регистрация
                </a>
            </li>
        </ul>

        <p class="copyright">on/off line <span class="fa fa-copyright"></span> 2016</p>
    </div>
    <!-- /Sidebar -->

    <!-- Page content -->
    <div id="page-content-wrapper" class="container-fluid">




        <h1>Регистрация</h1>
        <hr/>
        <div class="alert alert-info">Все поля обязательны для заполнения.</div>
        <form:form method="post" commandName="registerForm">
            <div style="width:350px;">
                <c:if test="${ie != null}">
                    <div class="alert alert-danger">
                        <ul>
                            <c:forEach items="${ie.errors}" var="error">
                                <li>${error}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
                <c:if test="${referAccount != null}">
                    Вы пришли сюда по ссылке ${referAccount}
                    <form:input path="refer" type="hidden"/>
                </c:if>

                <div class="form-group">
                    <label for="promoCode">Промо-код</label>
                    <form:input type="text" class="form-control" id="promoCode" placeholder="Код" path="promo"/>
                </div>
                <div class="form-group">
                    <label for="firstNameInput">Имя</label>
                    <form:input type="text" class="form-control" id="firstNameInput" placeholder="Введите имя" path="firstName"/>
                </div>
                <div class="form-group">
                    <label for="lastNameInput">Фамилия</label>
                    <form:input type="text" class="form-control" id="lastNameInput" placeholder="Введите фамилию" path="lastName"/>
                </div>
                <div class="form-group">
                    <label for="emailInput">Email</label>
                    <form:input type="email" class="form-control" id="emailInput" placeholder="Введите email" path="email"/>
                </div>
                <br/>
            </div>
            <button type="submit" class="btn btn-default">Зарегистрироваться</button>
        </form:form>








    </div>
    <!-- /Page content -->

</div>
<!-- /#wrapper -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#sidebar-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>

