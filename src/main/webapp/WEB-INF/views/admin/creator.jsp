<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>

<h4 style="color: #3F51B5;font-weight:bold;">Сводная по зарегистрированным пользователям</h4>

<div id="partnerListWrapper">
    <table class="table table-hover" id="partnerList">
        <thead>
        <tr>
            <td>Имя</td>
            <td>E-mail</td>
            <td>Тип аккаунта</td>
            <td>Сколько партнеров в сети</td>
            <td>Кто пригласил</td>
            <td>Он/офф лайн</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagedData.elements}" var="adminAccount">
            <tr>
                <admin:admin_account_line adminAccount="${adminAccount}"/>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<admin:paginator paginatorId="adminAccountListPaged" pagedData="${pagedData}" onClickFunction="page"/>

<pre>

сводная по зарегистрированых
всего зарегано на сайте
сейчас онлайн


фильтр
    он/офф лайн
    тип аккаунта
    поиск по email


Бухгалтерия
    выводим сколько денег у нас всего заработано (за выбранный период)
    скольким людям мы должны выплатить (за выбранный период)
    сколько останется после выплаты (за выбранный период)
ИТОГО
    сколько всего мы заработали (за все время)
    сколько всего мы должны выплатить (за все время)
    сколько всего мы заработали (за все время)

    фильтр:
        год
        месяц

</pre>