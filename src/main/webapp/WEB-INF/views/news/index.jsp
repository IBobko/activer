<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 class="title">Новости</h3>

<c:forEach var="new" items="${news}">
    <div style="margin: 10px 0;overflow: hidden">
        <p class="status-line" style="margin-bottom:0px">${new.accountData.firstName} ${new.accountData.lastName}</p>
        <img src="${staticFiles}/${new.accountData.photo60x60}." style="width:60px;float:left"/>
        <div style="margin: 10px 70px;">
            <c:if test="${new.type == 'WALL'}">
                <strong>написал:</strong><br/><span style="font-weight: normal;">${new.text}</span>
            </c:if>
            <c:if test="${new.type == 'AVATAR'}">
                обновил аватар<br/>
                <img style="width:200px" src="${staticFiles}/${new.text}."/>
            </c:if>
            <c:if test="${new.type == 'PHOTO'}">
                добавил фото
                <br/>
                <img style="width:200px" src="${staticFiles}/${new.text}."/>
            </c:if>
            <c:if test="${new.type == 'BOUGHT_PREMIUM'}">
                купил премиум аккаунт
            </c:if>
        </div>
    </div>
</c:forEach>
