<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="dispute" required="false" type="ru.todo100.activer.data.DisputeThemeData" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${dispute}" type="ru.todo100.activer.data.DisputeThemeData" varName="dispute"/>

<tr>
    <td><a href="<c:url value="/admin/dispute/add?id=${dispute.id}"/>">${dispute.name}</a></td>
    <td>${dispute.position1}</td>
    <td>${dispute.position2}</td>
    <td></td>
    <td><a href="<c:url value="/admin/dispute/delete?id=${dispute.id}"/>">Удалить</a></td>
</tr>


