<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form method="post" id="payeer_form" action="https://payeer.com/merchant/">
    <input type="hidden" name="m_shop" value="${payeer.shop}">
    <input type="hidden" name="m_orderid" value="${payeer.order}">
    <input type="hidden" name="m_amount" value="${payeer.amount}">
    <input type="hidden" name="m_curr" value="${payeer.curr}">
    <input type="hidden" name="m_desc" value="${payeer.desc}">
    <input type="hidden" name="m_sign" value="${payeer.sign}">
    <input type="hidden" name="form[ps]" value="2609">
    <input type="hidden" name="form[curr[2609]]" value="${payeer.curr}">
</form>

<script type="text/javascript">
    document.getElementById('payeer_form').submit();
</script>