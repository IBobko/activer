<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="mark" tagdir="/WEB-INF/tags/mark" %>

<div style="overflow:hidden">

    <div style="float:left;margin-right:10px;background-color:#fff;border-radius:4px;">
        <img src="${profile.facePhotoUrl}" style="border-radius: 4px"/>
        <div style="font-weight:600;font-size:16px;text-align: center;line-height:2.2">
            ${profile.firstName} ${profile.lastName}
        </div>
    </div>
</div>


<div><a href="<c:url value="/profile/change" />">Редактировать профиль</a></div>

Я хочу | <a href="<c:url value="/profile/add_i_want" />">Добавить</a>

<mark:list markType="want" marks="${wants}"/>

Я могу | <a href="<c:url value="/profile/add_i_can" />">Добавить</a>

<mark:list markType="can" marks="${cans}"/>


