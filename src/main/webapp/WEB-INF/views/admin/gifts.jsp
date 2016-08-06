<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="std-button btn btn-default" style="float:right" href="<c:url value="/admin/gifts/add"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить подарок</a>
<a class="std-button btn btn-default" style="float:right" href="<c:url value="/admin/gifts/category"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить категорию</a>
<h4 style="color: #3F51B5;font-weight:bold;">Администрирование подарков</h4>
<br/><br/>
<%--<pre>--%>
<%--Загрузка подарков--%>
<%--вывод всех загруженых подарков + чек бокс "активен" (определяет доступность для покупки данного попдарка)--%>
<%--добавление подарков--%>
<%--даунлодер картинки (подарка)--%>
<%--селект бокс - выбор категории подарков (если уже созданы категории)--%>
<%--добавить новую категрию--%>
<%--инпут - цена за данных подарок (допускается 0)--%>
<%--чекбокс "акьтивен" (определяет доступность для покупки данного попдарка)--%>
<%--</pre>--%>


<style type="text/css">
    .gifts{
        list-style: none;
    }
    .gifts li {
        float: left;
        width:256px;
        height:256px;
    }
</style>

<ul class="gifts">
    <c:forEach items="${pagedData.elements}" var="gift">
        <li>
            <a href="<c:url value="/admin/gifts/remove"/>?id=${gift.id}">Удалить</a>
            <a href="<c:url value="/admin/gifts/add?id=${gift.id}"/>"><img
                    width="256" height="256" src="${staticFiles}/${gift.file}.jpg"/><br/>${gift.name}</a>
        </li>
    </c:forEach>
</ul>