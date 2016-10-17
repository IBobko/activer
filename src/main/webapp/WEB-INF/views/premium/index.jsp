<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 class="title">Информация по платным статусам "onoffline"</h3>

<div style="font-size:12px">
Друзей в рамках сайта, могут искать и добавлять все, включая "Обычных" пользователей, регистрировать новых пользоваталей и зарабатывать по партнерской программе могут только пользователи со статусом "Уникальный".
</div>
<br/>
<div style="font-size: 20px">Ваш баланс: <span style="color:#2b669a">${currentProfileData.balance} lines</span> </div>

<a href="<c:url value="/balance"/>" class="std-button btn btn-default">Пополнить баланс</a>

<br/><br/>
<table style="font-size:12px;font-weight: normal ">
<tr>
    <td>
        <img src="<c:url value="/resources/img/statuses/2.png"/>"/>
    </td>
    <td>
        <strong>При покупки платного аккаунта вы получаете</strong>
        <ul>
            <li>5 подарков</li>
            <li>Возможность индивидуального дизайна страницы</li>
            <li>Возможность приватного доступа</li>
            <li>Возможность приватного просмотра анкет пользователей</li>
            <li>Возможность регистрации партнеров</li>
            <li>Просмотр карты зарегистрированных пользователей</li>
            <li>Участие в ежегодных розыгрышах компании</li>
        </ul>
    </td>
</tr>
<tr>
    <td colspan="2" align="center" style="font-size: 16px"><strong>Стоимость:</strong> <span style="color:#2b669a">30 lines / год</span>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/premium/buy"/>" class="std-button btn btn-default" >Купить Premium аккаунт</a></td>
</tr>
</table>


<br/><br/><br/><br/>

<h3 class="title">Дополнительные возможности</h3>

<div style="font-size:12px">
    Нужно будет придумать сюда какой-то текст бла бла бла бла бла бла бла бла бла бла бла бла
</div>

<style>
    .additions td{
        padding:10px;
    }
</style>

<br/><br/>
<table style="font-size:12px;font-weight: normal " class="additions">
    <tr>
        <td>
            <img src="<c:url value="/resources/img/01.jpg"/>"/>
        </td>
        <td>
            <strong>Будь всегда ТОПЕ</strong>
            <ul>
                <li>Размещение объявление в ленте ТОП онлайн и еще много всяких преимущества</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center" style="font-size: 16px"><strong>Стоимость:</strong> <span style="color:#2b669a">1 line / мес</span>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/premium/buy_topline"/>" class="std-button btn btn-default" >Купить размещение</a></td>
    </tr>
</table>


<br/><br/>
<table style="font-size:12px;font-weight: normal" class="additions">
    <tr>
        <td>
            <img src="<c:url value="/resources/img/01.jpg"/>"/>
        </td>
        <td>
            <strong>Индивидуальный дизайн/strong>
            <ul>
                <li>Индивидуальный дизайн ваше страницы и еще много всяких преимуществ</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center" style="font-size: 16px"><strong>Стоимость:</strong> <span style="color:#2b669a">8 lines / год</span>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/themes"/>" class="std-button btn btn-default" >Выбрать дизайн</a></td>
    </tr>
</table>


