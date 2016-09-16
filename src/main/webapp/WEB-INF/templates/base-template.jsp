<%--suppress XmlPathReference,JSUnresolvedLibraryURL --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="main" tagdir="/WEB-INF/tags/main" %>

<%@ taglib prefix="leftmenu" uri="/WEB-INF/tlds/LeftMenu.tld" %>

<leftmenu:init/>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <title>On\Off Line</title>

    <!-- Favicon -->
    <link rel="icon" href="<c:url value="/resources/img/favicon.ico"/>" type="image/x-icon">

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&subset=latin,cyrillic' rel='stylesheet'
          type='text/css'>

    <!-- Custom CSS -->

    <c:choose>
        <c:when test="${profile.theme == 'kitty'}">
            <link href="<c:url value="/resources/css/styles-hello_kitty.css"/>" rel="stylesheet">
            <link href="<c:url value="/resources/css/sidebar-hello_kitty.css"/>" rel="stylesheet">
        </c:when>
        <c:when test="${profile.theme == 'carbone'}">
            <link href="<c:url value="/resources/css/styles-carbone.css"/>" rel="stylesheet">
            <link href="<c:url value="/resources/css/sidebar-carbone.css"/>" rel="stylesheet">
        </c:when>
        <c:otherwise>
            <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet">
            <link href="<c:url value="/resources/css/sidebar.css"/>" rel="stylesheet">
        </c:otherwise>
    </c:choose>


    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-datetimepicker.css"/>" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>



    <![endif]-->
    <script src="http://stevenlevithan.com/assets/misc/date.format.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/sockjs-0.3.4.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/stomp.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-datetimepicker.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/locales/bootstrap-datetimepicker.ru.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/activer.global.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/activer.messaging.js"/>"></script>

    <script type="text/javascript">

        if (window.ACTIVER == undefined) {
            window.ACTIVER = {};
        }

        window.ACTIVER.MessageBadge = {
            eBadge :null,
            init: function(){
                this.eBadge = $('#globalUnreadMessageBadge');
            },

            increase: function() {
                this.add(1);
            },

            add: function(i) {
                if ((this.getCurrent() + i) < 0) return;
                this.eBadge.html(this.getCurrent() + i);
                if (this.getCurrent() == 0) {
                    this.eBadge.css("display","none");
                } else {
                    this.eBadge.css("display","");
                }
            },

            getCurrent: function() {
                return this.eBadge.html()*1;
            },

            decrease: function() {
                this.add(-1);
            }

        };

        window.ACTIVER.context_path = "${pageContext.servletContext.contextPath}";
        window.ACTIVER.Data = {};
        $.getJSON("<c:url value="/js/data.json"/>", function (data) {
            for (var attrname in data) {
                if (data.hasOwnProperty(attrname)) {
                    window.ACTIVER.Data[attrname] = data[attrname];
                }
            }
            $(function () {
                $.each(window.ACTIVER, function (i, o) {
                    if (o.init != undefined) {
                        o.init();
                    }
                })
            });
        });
    </script>
</head>

<div id="wrapper" <c:if test="${sessionScope.containsKey('leftmenu')}">class="toggled"</c:if>>
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
                    <a class="navbar-brand my-navbar-brand" href="<c:url value="/profile"/>">
                        <img alt="Brand" src="<c:url value="/resources/img/logo.png"/>">
                    </a>
                </div>
            </div>
        </nav>
        <main:leftmenu/>
        <!--button class="btn btn-sm text-uppercase premium"><span class="fa fa-star-o"></span> премиум-аккаунт</button-->
        <p class="copyright">on/off line <span class="fa fa-copyright"></span> 2016</p>
    </div>
    <!-- /Sidebar -->

    <!-- Page content -->
    <div id="page-content-wrapper" class="container-fluid">
        <!-- Navbar -->
        <main:topmenu/>

        <!-- /Navbar -->
        <tiles:insertAttribute name="content"/>
    </div>
    <!-- /Page content -->

</div>
<!-- /#wrapper -->

<!-- Menu Toggle Script -->
<script type="text/javascript">
    $("#sidebar-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass(function (a, state) {
            if (state == "toggled") {
                $.get("<c:url value="/settings/leftmenusave"/>");
            } else {
                $.get("<c:url value="/settings/leftmenusave"/>", {yes: 1});
            }
            return "toggled";
        });
    });

    ACTIVER.Global.on("SPENT",function(data){
        var popupWindow = $('#popupWindow');
        var sum = data.message * 1;
        if (sum == 0) {
            popupWindow.html("На вашем счету недостаточно средств");
        } else {
            popupWindow.html("Потрачен " + sum + " доллар");
        }
        popupWindow.show();
    });

    if (window.ACTIVER.Global.onPRIVATE_MESSAGE == null) {
        window.ACTIVER.Global.onPRIVATE_MESSAGE = function (data) {
            console.log(data);

            if (data.type == "DATING") {
                var link = "<a style='color:white;font-weight:bold' href=\"" + window.ACTIVER.context_path + "/dating/dispute?id=" + data.interlocutor + "\">сюда</a>";
                $('#popupWindow').html("Вас вызывают для общения! Быстрее присоединяйтесь. Кликните " + link + ", чтобы пообщаться");
                $('#popupWindow').show();
                return;
            }

            if (data.type == "FLIRT") {
                var link = "<a style='color:white;font-weight:bold' href=\"" + window.ACTIVER.context_path + "/dating/flirt?id=" + data.interlocutor + "\">сюда</a>";
                $('#popupWindow').html("Вас вызывают пофлиртовать! Быстрее присоединяйтесь. Кликните " + link + ", чтобы начать");
                $('#popupWindow').show();
                return;
            }

            if (data.type == "FLIRT_AGREE") {
                document.location = window.ACTIVER.context_path + "/message?dialog=" + data.interlocutor;
                return;
            }

            if (data.type == "PRIVATE_MESSAGE") {
                ACTIVER.MessageBadge.increase();
                var link = "<a href=\"" + window.ACTIVER.context_path + "/message/" + data.from.id + "\">сюда</a>";
                $('#popupWindow').html(data.from.firstName + " " + data.from.lastName + " прислал личное сообщение. Кликните " + link + ", чтобы пообщаться");
                $('#popupWindow').show();
            }


        };
    }
</script>
<div id="popupWindow"
     style="font-size:12px;display:none;bottom:5%; z-index:100000; left:10px; position:fixed; width:300px; height:80px;background:#5d7fb5;border-radius:10px;padding:10px;font-weight:normal;color:white;">

</div>

<script type="text/javascript">
    $('.form_date').datetimepicker({
        language:  'ru',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
</body>
</html>

