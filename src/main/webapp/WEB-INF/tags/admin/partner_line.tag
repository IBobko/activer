<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="partner" required="false" type="ru.todo100.activer.data.PartnerData" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${partner}" type="ru.todo100.activer.data.PartnerData" varName="partner"/>

<tr>
    <td><a href="<c:url value="/profile/id${partner.id}"/>">${partner.name}</a></td>
    <td>${partner.level}</td>
    <td>${partner.inviter} ${partner.inviterLevel}</td>
    <td>${partner.invitedCount}</td>
    <td>${partner.networkCount}</td>
    <td>${partner.earned}</td>
    <td>${partner.profit}</td>
</tr>



