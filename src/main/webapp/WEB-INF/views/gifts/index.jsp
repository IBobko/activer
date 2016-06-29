<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4 style="color: #3F51B5;font-weight:bold;">Мои подарки</h4>

<c:forEach items="${gifts}" var="gift">
    <div style="margin: 10px 0;overflow: hidden" class="yoxview">
        <p class="status-line" style="margin-bottom:0">
            <a href="<c:url value="/profile/id${gift.from.id}"/>">${gift.from.firstName}&nbsp;${gift.from.lastName}</a>
        </p>
        <a href="<c:url value="/profile/id${gift.from.id}"/>"><img src="${staticFiles}/${gift.from.photo60x60}." style="margin-top:10px;float:left"/></a>
        <div style="margin: 10px 70px;">
                Отправил вам подарок ${gift.givenDate}
                <br/><br/>
                <span style="font-weight: normal">${gift.message}</span>
            <br/><br/>
                    <img src="${staticFiles}/${gift.fileName}."/>
        </div>
    </div>
</c:forEach>




