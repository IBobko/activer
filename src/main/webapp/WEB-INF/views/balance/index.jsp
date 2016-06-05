<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--suppress XmlDuplicatedId --%>

<h3 style="color: #3F51B5;font-weight:bold;">Баланс</h3>

Текущий баланс: ${balance.sum}

<form  method="post">
    Пополнить счет
    <input type="text" name="sum"/>
    <input type="submit"/>
</form>