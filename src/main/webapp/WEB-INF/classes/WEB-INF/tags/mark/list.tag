<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="markType" required="true" type="java.lang.String"%>
<%@ attribute name="marks" required="true" type="java.util.List"%>

<div class="well" style="width:400px">
    <ol>
    <c:forEach items="${marks}" var="mark">
        <li>
        <div>
            ${mark.title} - <a href="<c:url value="/profile/edit_i_${markType}/${mark.id}" />">Редактировать</a>
        </div>
        </li>
        </c:forEach>
    </ol>
</div>