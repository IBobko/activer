<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <a href="<c:url value="/profile"/>">
                    <span class="fa fa-user"></span>
                    Моя страница
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="fa fa-smile-o"></span>
                    Друзья
                </a>
            </li>
            <li>
                <a href="<c:url value="/message/2"/>">
                    <span class="fa fa-envelope-o"></span>
                    Сообщения<span class="badge">12</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="fa fa-photo"></span>
                    Фотографии<span class="badge">3</span>
                </a>
            </li>
            <li>
                <a href="#">
                    <span class="fa fa-play-circle"></span>
                    Видео
                </a>
            </li>
        </ul>
        <button class="btn btn-sm text-uppercase premium"><span class="fa fa-star-o"></span> премиум-аккаунт</button>
        <p class="copyright">on/off line <span class="fa fa-copyright"></span> 2016</p>
    </div>
    <!-- /Sidebar -->

    <!-- Page content -->
    <div id="page-content-wrapper" class="container-fluid">
        <!-- Navbar -->
        <%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>

        <script src="<c:url value="/resources/js/activer.messaging.js"/>"></script>

        <script type="text/javascript">
            window.ACTIVER.Data.browseProfile = {
                id: '${friend.id}'
            };
        </script>

        <div id="templatePost" style="display:none">
            <profile:post message="${templatePost}"/>
        </div>
        <div>
            <div id="conversationDiv">
                <div class="well" id="messages" style="margin-top:10px;overflow-y: scroll; height:600px;">
                    <c:forEach items="${lastMessages}" var="message">
                        <profile:post message="${message}"/>
                    </c:forEach>
                </div>
                <form id="dialogForm">
                    <button id="sendName" class="btn btn-default" style="float:right">Отправить</button>
                    <div style="margin-right:96px">
                        <input type="text" id="text" class="form-control"/>
                    </div>
                </form>
            </div>
        </div>



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
