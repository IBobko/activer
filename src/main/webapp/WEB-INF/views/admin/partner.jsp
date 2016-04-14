<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .modal-open {
        padding-right: 0 !important;
    }

    html {
        overflow-y: scroll;
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

<table class="table table-hover">
    <tr>
        <td>Имя</td>
        <td>Уровень</td>
        <td>Пригласивший (уровень)</td>
        <td>Приглашенных</td>
        <td>Колчиество людей в сети</td>
        <td>Заработано</td>
        <td>Моя прибыль</td>
    </tr>
    <c:forEach items="${partners}" var="partner">
        <tr>
            <td><a href="<c:url value="/profile/id${partner.id}"/>">${partner.firstName}&nbsp;${partner.lastName}</a>
            </td>
            <td>2</td>
            <td>Бобко Игорь (1)</td>
            <td>2</td>
            <td>2</td>
            <td>700$</td>
            <td>200$</td>

        </tr>
    </c:forEach>
</table>