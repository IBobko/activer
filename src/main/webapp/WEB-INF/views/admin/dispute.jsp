<%--suppress XmlPathReference --%>
<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
    .table>tbody>tr>td {
        font-weight: normal;
    }
</style>

<a class="std-button btn btn-default" style="float:right" href="<c:url value="/admin/dispute/add"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить тему спора</a>
<h4 style="color: #3F51B5;font-weight:bold;">Споры</h4>

<table class="table table-striped table-bordered" style="margin-top:40px">
    <thead>
    <tr>
        <td>Тема</td>
        <td>Позиция 1</td>
        <td>Позиция 2</td>
        <td>Язык</td>
        <td></td>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagedData.elements}" var="dispute">
        <tr>
            <td><a href="<c:url value="/admin/dispute/add?id=${dispute.id}"/>">${dispute.name}</a></td>
            <td>${dispute.position1}</td>
            <td>${dispute.position2}</td>
            <td></td>
            <td><a href="<c:url value="/admin/dispute/delete?id=${dispute.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
