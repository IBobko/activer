<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="paginatorId" required="true" type="java.lang.String" %>
<%@ attribute name="pagedData" required="true" type="ru.todo100.activer.data.PagedData" %>
<%@ attribute name="onClickFunction" required="true" type="java.lang.String" %>

<nav>
    <ul class="pagination" id="${paginatorId}">
        <li class="disabled">
            <a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
        </li>
        <c:forEach var="i" begin="1" end="${pagedData.count}">
            <li id="${paginatorId}Item${i}" <c:if test="${pagedData.page == i-1}">class="active"</c:if>>
                <a href="javascript:${onClickFunction}(${i-1})">${i}</a>
            </li>
        </c:forEach>
        <li class="disabled">
            <a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
        </li>
    </ul>
</nav>