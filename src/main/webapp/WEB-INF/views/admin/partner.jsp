<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>

<style type="text/css">
    html {
        overflow-y: scroll;
    }

    .modal-open {
        padding-right: 0 !important;
    }

</style>
<button class="std-button btn btn-default" style="float:right" data-toggle="modal"
        data-target="#inviteNewParnersWindow"><span
        class="glyphicon glyphicon-plus"></span> Пригласить новых партнеров
</button>
<br/>
<br/>
<br/>
<br/>
<br/>

при нажатии выводит на экран (сразу выделенную) реферальную ссылку + кнопки для расшаривания в соц сети (вк, фейс бук, одноклассники) т итпа того http://joxi.ru/xAeve47SYx8elr
+ так же возможность ввести мыло друга что б ему на это мыло упало письмо со ссылкой для регистрации.


<div class="modal fade" id="inviteNewParnersWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Приглашение</h4>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="myReferLink" readonly
                       value="http://onoffline/auth/signup?referCode=02398"/>
                <div style="line-height: 50px">
                    Введи E-mail друга, чтобы он тоже смог присоедениться к нам
                    <input class="form-control" type="email">

                    <button type="submit" class="std-button btn btn-default"><span
                            class="glyphicon glyphicon-send"></span> Отправить
                    </button>
                </div>
            </div>
            <div class="modal-footer">
                <div style="float:left">Поделиться в социальных сетях:</div>
                <script type="text/javascript" src="//yastatic.net/es5-shims/0.0.2/es5-shims.min.js"
                        charset="utf-8"></script>
                <script type="text/javascript" src="//yastatic.net/share2/share.js" charset="utf-8"></script>
                <div class="ya-share2" data-services="vkontakte,facebook,odnoklassniki,moimir"
                     data-title="ON / OFF LINE"
                     data-description="Моя ссылка на супер сайте. <a href='http://onoffline/auth/signup?referCode=02398'>http://onoffline/auth/signup?referCode=02398</a> "></div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $('#inviteNewParnersWindow').on('shown.bs.modal', function () {
            $("#inviteNewParnersWindow #myReferLink").select();
        })
    });
</script>

<br/><br/><br/><br/>

<table class="table table-hover">
    <tr>
        <td>Количество партнеров в каждом уровне</td>
        <td>сколько денег заработал каждый уровень</td>
        <td>сколько денег я получил с каждого уровня</td>
        <td>сколько всего у меня партнеров в сети (сумма всех партнеров</td>
        <td>сколько всего денег я заработал</td>
    </tr>
    <tr>
        <td>400$</td>
    </tr>
</table>

<pre>
    ранжирование:
по ФИО (фамилии)
по уровню партнера
по уроню того кто его пригласил
по кол-ву заработанных партнеров денег
по кол-ву денег которые принес мне мой партнер
</pre>

<pre>
фильтры:
уровень партнера (1-6)
уровень тех кто приглашает (2-5)
    </pre>
<div id="partnerListWrapper">
    <table class="table table-hover" id="partnerList">
        <thead>
        <tr>
            <td style="width:226px">Имя</td>
            <td>Уровень</td>
            <td>Пригласивший (уровень)</td>
            <td>Приглашенных</td>
            <td>Колчиество людей в сети</td>
            <td>Заработано</td>
            <td>Моя прибыль</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagedData.elements}" var="partner">
            <tr>
                <admin:partner_line partner="${partner}"/>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<nav>
    <ul class="pagination" id="partnerListPaged">
        <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        <c:forEach var="i" begin="1" end="${pagedData.count}">
            <li id="partnerListPagedItem${i}" <c:if test="${pagedData.page == i-1}">class="active"</c:if>><a
                    href="javascript:page(${i-1})">${i}</a></li>
        </c:forEach>

        <li class="disabled"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
    </ul>
</nav>

<script type="text/javascript">
    function page(p) {
        var data = {
            page: p
        };
        var partnerList = $('#partnerList tbody');
        $('#partnerListWrapper').css('height', $('#partnerListWrapper').height() + "px");
        partnerList.html('');
        $("#partnerListPaged [class='active']").removeClass('active');
        $("#partnerListPagedItem" + (p + 1)).addClass('active');
        $.get("<c:url value="/admin/partnerPaged"/>", data, function (response) {
            for (index in response.elements) {
                var line = $('#partnerLine').val();
                for (key in response.elements[index]) {
                    line = line.replace("#" + key, response.elements[index][key]);
                }
                partnerList.append(line);
            }
        });
        return false;
    }
</script>

<textarea style="display:none" id="partnerLine">
    <admin:partner_line/>
</textarea>

