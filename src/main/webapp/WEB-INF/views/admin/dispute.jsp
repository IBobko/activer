<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a class="std-button btn btn-default" style="float:right" href="<c:url value="/admin/dispute/add"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить тему спора</a>
<h4 style="color: #3F51B5;font-weight:bold;">Споры</h4>

<pre>
    темы для споров ( не обяз для первой версии, доработаем после 24.04)
список всех добавленных тем для споров + кнопки "удалить" и редактировать
кнопка "добавить"
инпут - название новой темы для споров (должна поддерживаться мультиязычность на N кол-во языков)
инпут "за что в споре сторона А"
инпут "за что в споре сторона Б"
кнопка сохранить
</pre>