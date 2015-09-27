<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.todo100.activer.model.AccountItem" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1>Profile</h1>

<div class="well" style="width:400px">
	<div><strong>First name:</strong> ${account.firstName}</div>
	<div><strong>Last name:</strong> ${account.lastName}</div>
	<div><strong>Login:</strong> ${account.username}</div>
	<div><strong>E-mail:</strong> ${account.email}</div>
	<div><strong>Birthday:</strong> <%
 	String[] months = (String[])request.getAttribute("months");
  	java.util.Calendar birthday = ((AccountItem)request.getAttribute("account")).getBirthday();
  	out.print(birthday.get(java.util.Calendar.DATE) + " " + months[birthday.get(java.util.Calendar.MONTH)] + " " + birthday.get(java.util.Calendar.YEAR));
 %></div>
	<div><strong>Country:</strong> ${countries[account.country]}</div>
</div>
<div><a href="<%=request.getContextPath()%>/profile/change">Change password</a></div>

<h2>Your goods</h2>
<div style="height:400px;overflow-y:scroll;width:900px;" class="blank-well">
<ul class="model3d">
<c:forEach items='${goods}' var="good">
	<li>
		<tiles:insertTemplate template="/WEB-INF/views/model3d/model3dWidget.jsp">
			<tiles:putAttribute name="model3d" value="${good}"/>
		</tiles:insertTemplate>
	</li>
</c:forEach>
</ul>
</div>

<h2>Your purchases</h2>
<div style="height:400px;overflow-y:scroll;width:900px" class="blank-well">
<ul class="model3d">
<c:forEach items='${purchases}' var="purchase">
	<li>
		<tiles:insertTemplate template="/WEB-INF/views/model3d/model3dWidget.jsp">
			<tiles:putAttribute name="model3d" value="${purchase.model3d}"/>
		</tiles:insertTemplate>
	</li>
</c:forEach>
</ul>
</div>
