<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
<c:forEach var="table" items="${tables}">
	<li>${table}</li>
</c:forEach>
</ul>

