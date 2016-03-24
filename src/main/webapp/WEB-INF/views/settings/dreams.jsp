<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h4 style="color:#337ab7;font-weight:bold">Добавить мечту</h4>

<c:url value="/settings/dreams/upload" var="uploadUrl"/>
<form:form commandName="dreamForm" action="${uploadUrl}" enctype="multipart/form-data">
    <form:input path="id" type="hidden"/>
    <form:input path="photo" type="file"/>
    <form:textarea path="text"></form:textarea>
    <input type="submit" value="Сохранить"/>
</form:form>


<h4 style="color:#337ab7;font-weight:bold">Мои мечты</h4>

<script>
    var edit = function(e){
        $('#text').text($(e).find('span').text());
        $('#id').val($(e).attr('dream-id'));
    }
</script>

<c:forEach items="${dreams}" var="dream">
    <div dream-id="${dream.id}" onclick="edit(this)">
        <img style="width:100px;"class="media-object" src="http://todo100.ru:18080/static/upload/files/${dream.photo}.png">
        <span>${dream.text}</span>
        <a href="<c:url value="/settings/dreams/remove?dream=${dream.id}"/>">Удалить</a>
    </div>
    <hr/>
</c:forEach>
