<%--suppress XmlPathReference --%>
<%--@elvariable id="friendsData" type="ru.todo100.activer.data.FriendsData"--%>
<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="partner" required="false" type="ru.todo100.activer.data.PartnerData" %>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/DefaultPartner.tld" %>

<p:defaultPartner partner="${partner}"/>

<tr>
    <td><a href="<c:url value="/profile/id${partner.id}"/>">${partner.name}</a>
    </td>
    <td>2</td>
    <td>Бобко Игорь (1)</td>
    <td>2</td>
    <td>2</td>
    <td>700$</td>
    <td>200$</td>
</tr>



