<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<h2>Wishlist</h2>
<fmt:formatDate value=""/>
<c:forEach var="wish" items="${wishes}">
    ${wish.date}<br/>

    <strong>${wish.account.username}</strong><br/>
    ${wish.text}
    <hr/>
</c:forEach>

<ul class="pagination">
    <li><a href="#">&laquo;</a></li>
    <%
        Integer count = (Integer) request.getAttribute("count");
        for (int i = 0; i < count; i++)
        {
            request.setAttribute("search_i", i); %>
    <c:url value="/wish/" var="pageUri">
        <c:param name="page" value="${search_i}"/>
    </c:url>
    <li><a href='${pageUri}'><%=(i + 1) %>
    </a></li>
    <%} %>
    <li><a href="#">&raquo;</a></li>
</ul>


<div style="width:600px">
    <form method="post">
        <div class="form-group">
            <textarea class="form-control" style="height:100px" name="wish"></textarea>
        </div>
        <div class="form-group">
            <input type="submit" value="Submit" class="btn btn-default"/>
        </div>
    </form>
</div>