<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mark" tagdir="/WEB-INF/tags/mark" %>


<script type="text/javascript">
    $(function() {
        var setPadding = function() {
            var padding = $(".markerSelection .selectedMarkers").width();
            $('.markerSelection input[type="text"]').css('padding-left', padding + 20);
        };
        setPadding();

        $('.markerSelection input').keydown(function(e){
            if (e.keyCode == 13) {
                var mark = $('.markerSelection input[type="text"]').val();
                var markTemplate = $("#markTemplate").html().replace("%markText%", mark);
                $(".selectedMarkers").append(markTemplate);
                $('input').val('');
                setPadding();
            }
        });
    });
</script>

<style>
    .selectedMarkers {
        position: relative;
        margin: 5px;
        top: 26px;
    }
</style>


<div style="display:none" id="markTemplate">
	<mark:edit markText="%markText%"/>
</div>

<div class="markerSelection">
    <span class="selectedMarkers">
        <span class="label label-default">Привет мир <input type="hidden" value="${markText}" name="mark[]"><span class="glyphicon glyphicon-remove"></span></span>
        <span class="label label-default">Как дела? <input type="hidden" value="${markText}" name="mark[]"><span class="glyphicon glyphicon-remove"></span></span>
    </span>
    <input type="text" class="form-control"/>
</div>



<div class="well">
    <h3>Я могу</h3>

    <form:form method="post" commandName="iCanForm">
        <form:hidden path="id"/>
        <div>Заголовок <form:input path="title" cssClass="form-control"/></div>
        <div>Описание <form:textarea path="description" cssClass="form-control"/></div>
        <div>Тэги <form:input cssClass="form-control" path="marks"/></div>
        <div><form:button type="submit" class="btn btn-default">Сохранить</form:button></div>
    </form:form>
</div>