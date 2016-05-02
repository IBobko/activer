<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4 style="color: #3F51B5;font-weight:bold;">Мои подарки</h4>

<style type="text/css">
    .gifts {
        list-style: none;
    }
</style>

<ul class="gifts">
<c:forEach items="${gifts}" var="gift">
    <li> От ${gift.senderFirstName} ${gift.senderLastName} (${gift.givenDate})<br/>
        <img src="http://todo100.ru:18080/static/upload/files/${gift.fileName}.jpg"/><br/>
            ${gift.message}<br/><br/><br/>
    </li>
</c:forEach>
</ul>