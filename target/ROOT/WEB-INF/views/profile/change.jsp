<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<% if (request.getAttribute("ie") != null) { %>
<div class="alert alert-danger">
<ul>
	<c:forEach items="${ie.errors}" var="error">
		<li>${error}</li>		
	</c:forEach>
</ul>
</div>
<% } %>
<% if (request.getAttribute("success") != null) { %>
<div class="alert alert-success">
	Password is changed
</ul>
</div>
<% } %>
<form method="post">
 <div class="form-group">
 	<label for="inputFirstName">First name</label>
	<input type="password" class="form-control" placeholder="Enter the first name" name="password" id="inputFirstName" value="${account.firstName}"/>
</div>
 <div class="form-group">
 	<label for="inputLastName">Last name</label>
	<input type="password" class="form-control" placeholder="Enter the last name" name="password" id="inputLastName" value="${account.lastName}">
</div>
 <div class="form-group">
 	<label for="inputFirstName">Country</label>
	<input type="password" class="form-control" placeholder="Enter the first name" name="password" id="inputFirstName" value="${account.firstName}"/>
</div>
 <div class="form-group" style="overflow:hidden">
   <label style="width:100%">Your birthday</label>
   <select class="form-control"  name="birthDay" style="width:70px;float:left">
   	<% for (int i = 1; i < 31; i++) {%>
   		<option value='<%=i%>'><%=i%></option>
   	<% }%>
   </select>
   <select class="form-control"  name="birthMonth" style="margin: 0 5px; width:120px;float:left">
<%
String[] months = new java.text.DateFormatSymbols().getMonths();
for (int i = 0; i < months.length; i++) {
	if (!months[i].equals("")) {
		out.println("<option value='"+i+"'>"+months[i]+"</option>");
	}
}
%>
   </select>
   <select class="form-control"  name="birthYear" style="width:90px;">
   	<% for (int i = 2005; i > 1930; i--) {%>
   		<option value='<%=i%>'><%=i%></option>
   	<% }%>
   </select>
 </div>	    
 <div class="form-group">
 	<label for="inputPassword">Password</label>
	<input type="password" class="form-control" placeholder="Enter the password" name="password" id="inputPassword">
</div>
<div class="form-group">
  <label for="inputRepeatPassword">Repeat the password</label>
  <input type="password" class="form-control" placeholder="Repeat the password" name="repeat_password" id="inputRepeatPassword">
</div>
<div class="form-group">
	<input type="submit" class="btn btn-primary" value="Change"/>
</div>
</form>
<a href="<%=request.getContextPath() %>/profile/">Go to profile</a>
</div>