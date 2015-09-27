<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1>Blog</h1>

<c:forEach items="${blogs}" var='blog'>
	<div>
		<h3>${blog.title}</h3>
		<div>${blog.text}</div>
	</div>
</c:forEach>
