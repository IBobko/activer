<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h4 style="color: #3F51B5;font-weight:bold;">Сводная по зарегистрированным пользователям</h4>

<table>
    <tr>
        <td>Всего зарегистрированных пользователей:</td>
        <td>${totalAmount}</td>
    </tr>
    <tr>
        <td>Всего пользователей online:</td>
        <td>${totalOnlineAmount}</td>
    </tr>
</table>
<form:form modelAttribute="pagedForm" >
<div style="display: flex">

        <div>
            <form:checkbox id="online" value="1" path="online"/> online
        </div>
        <div>
            <form:select path="accountType">
                <form:option value="1">Обычный</form:option>
                <form:option value="2">Уникальный</form:option>
                <form:option value="3">Создатель</form:option>
            </form:select>
        </div>
    <div>
        E-mail: <form:input path="email"/>
    </div>
</div>
</form:form>


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



<script type="text/javascript">
    function page(p) {
        var online = $('#online').is(":checked") ? 1 : 0;

        document.location = "${pageContext.request.getAttribute("javax.servlet.forward.request_uri")}?page=" + p + "&online=" + online;
    }
</script>

<pre>

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