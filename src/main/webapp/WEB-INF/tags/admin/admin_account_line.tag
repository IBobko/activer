<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="adminAccount" required="false" type="ru.todo100.activer.data.AdminAccountData" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${adminAccount}" type="ru.todo100.activer.data.AdminAccountData" varName="adminAccount"/>

<tr>
    <td>${adminAccount.lastName}&nbsp;${adminAccount.firstName}</td>
    <td>${adminAccount.email}</td>
    <td>${adminAccount.type}</td>
    <td>${adminAccount.networkCount}</td>
    <td>${adminAccount.inviterName}</td>
    <td>${adminAccount.onoffline}</td>
</tr>



