<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="balance" required="false" type="ru.todo100.activer.data.BalanceData" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${balance}" type="ru.todo100.activer.data.BalanceData" varName="balance"/>

<tr>
    <td>${balance.accountName}</td>
    <td>${balance.sum}</td>
    <td>${balance.description}</td>
</tr>



