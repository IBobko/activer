<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 class="title">Информация по платным статусам "onoffline"</h3>

<div style="font-size:16px">
    Друзей в рамках сайта, могут искать и добавлять все, включая "Обычных" пользователей, регистрировать новых пользоваталей и зарабатывать по партнерской программе могут только пользователи со статусом "Уникальный".
</div>
<br/>
<div style="font-size: 20px; padding-bottom:15px;">Ваш баланс: <span style="color:#2b669a">${currentProfileData.balance} lines</span> </div>

<a href="<c:url value="/balance"/>" class="std-button btn btn-default">Пополнить баланс</a>

<br/><br/>
<table style="font-size:14px;font-weight: normal; margin-top:45px; ">
    <tr>
        <td>
            <img src="<c:url value="/resources/img/buy_premium_status.png"/>"/>
        </td>
        <td style="padding-top:0px; vertical-align: baseline;">
            <strong style="font-size:16px;" >При покупки статуса <span style="color:#ffa51a; font-size:18px;">УНИКАЛЬНЫЙ</span> вы получаете:</strong>

            <ul style="padding-top:10px;">
                <li>30 подарков</li>
                <li>Возможность индивидуального дизайна страницы</li>
                <li>Возможность приватного доступа</li>
                <li>Возможность приватного просмотра анкет пользователей</li>
                <li style="color:#ffa51a;"><b>Возможность регистрации партнеров</b></li>
                <li style="color:#ffa51a;"><b>Просмотр карты зарегистрированных пользователей</b></li>
                <li>Участие в ежегодных розыгрышах компании</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="left" style="font-size: 18px; padding-top: 15px;"><span>Стоимость:</span> <strong style="color:#2b669a">60 lines / год</span>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/premium/buy"/>" class="std-button btn btn-default" >Купить Premium аккаунт</a></td>
    </tr>
</table>
<table style="font-size:14px;font-weight: normal; margin-top:45px; ">
    <tr>
        <td>
            <img src="<c:url value="/resources/img/buy_premium_status.png"/>"/>
        </td>
        <td style="padding-top:0px; vertical-align: baseline;">
            <strong style="font-size:16px;" >При покупки статуса <span style="color:#ffa51a; font-size:18px;">ОСОБЕННЫЙ</span> вы получаете:</strong>

            <ul style="padding-top:10px;">
                <li>15 подарков</li>
                <li>Возможность индивидуального дизайна страницы</li>
                <li>Возможность приватного доступа</li>
                <li>Возможность приватного просмотра анкет пользователей</li>
                <li>Участие в ежегодных розыгрышах компании</li>
            </ul>
        </td>
    </tr>
    <br/><br/>
    <tr>
        <td colspan="2" align="left" style="font-size: 18px; padding-top: 15px;"><span>Стоимость:</span> <strong style="color:#2b669a">30 lines / год</strong>&nbsp;&nbsp;&nbsp;СКОРО НА САЙТЕ!</span></td>
    </tr>
</table>
<br/><br/><br/><br/>

<h3 class="title">Дополнительные возможности</h3>

<style>
    .additions td{
        padding:10px;
    }
</style>

<br/><br/>
<table style="font-size:14px;font-weight: normal " class="additions">
    <tr>
        <td>
            <img src="<c:url value="/resources/img/buy_topline.png"/>"/>
        </td>
        <td style="padding-top:0px; vertical-align: baseline;">
            <strong style="font-size:16px;" >Будь всегда ТОПЕ:</strong>
            <ul style="padding-top:10px;">
                <li>Размещение объявление в ленте ТОП онлайн и еще много всяких преимущества</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="left" style="font-size: 18px; padding-top: 15px;"><span>Стоимость:</span> <strong style="color:#2b669a">1 line / мес</strong>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/top-line"/>" class="std-button btn btn-default" >Купить размещение</a></td>
    </tr>
</table>


<br/><br/>
<table style="font-size:14px;font-weight: normal" class="additions">
    <tr>
        <td>
            <img src="<c:url value="/resources/img/buy_design.png"/>"/>
        </td>
        <td style="padding-top:0px; vertical-align: baseline; ">
            <strong style="font-size:16px;" >Будь ярче. Индивидуальный дизайн своей страницы.</strong>
            <ul style="padding-top:10px;">
                <li>Индивидуальный дизайн вашей страницы.Будь ярче, покажи свою индивидуальность.</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="" style="font-size: 18px; padding-top: 15px;"><span>Стоимость:</span> <strong style="color:#2b669a">8 lines / год</strong>&nbsp;&nbsp;&nbsp;<a href="<c:url value="/themes"/>" class="std-button btn btn-default" >Выбрать дизайн</a></td>
    </tr>
</table>

