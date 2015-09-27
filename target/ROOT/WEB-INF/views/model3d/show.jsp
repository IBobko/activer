<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h3>${model3d.name}</h3>
<div>${model3d.description}</div>

<img src="<c:url value="${picturePath}/${model3d.pictures[0].path}"/>">

<div><strong>Price:</strong> ${model3d.price}</div>
<div><strong>Count of download:</strong> ${model3d.downloadCount}</div>
<div><strong>Marks: </strong>
<c:forEach var="mark" items="${model3d.marks}">
	${mark.name}
</c:forEach>
</div>

<br/>

<sec:authorize access="isAuthenticated()">
<c:choose>
      <c:when test="${model3d.price==0}">
      	<div><a href="<c:url value="/model3d/download/${model3d.id}"/>" class="btn btn-primary">Download</a></div>
      </c:when>
	  <c:otherwise>
      	<div><a href="<c:url value="/model3d/purchase/${model3d.id}"/>" class="btn btn-primary">Buy</a></div>
      </c:otherwise>
</c:choose>
</sec:authorize>
<sec:authorize access="isAnonymous()">
	<div>To download this file you need <a href="<c:url value="/auth"/>">Log in</a> or <a href="<c:url value="/auth/signup"/>">Sign up</a>.</div>
</sec:authorize>


