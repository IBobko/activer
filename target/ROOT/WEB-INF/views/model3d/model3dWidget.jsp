<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:useAttribute name="model3d"/>
<div>
	<a href="<c:url value="/model3d/show/${model3d.id}"/>">${model3d.name}</a>
	<div><img src="<c:url value="/model3d/pictures/${model3d.pictures[0].thumbnail}"/>"></div>
	<div><strong>Price: </strong>${model3d.price}</div>
</div>
