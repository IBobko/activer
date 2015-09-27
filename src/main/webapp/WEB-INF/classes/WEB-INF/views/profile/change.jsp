<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    <% if (request.getAttribute("ie") != null)
    { %>
    <div class="alert alert-danger">
        <ul>
            <c:forEach items="${ie.errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
    <% } %>
    <% if (request.getAttribute("success") != null)
    { %>
    <div class="alert alert-success">
        Password is changed
        </ul>
    </div>
    <% } %>
    <form method="post">

        <tiles:insertTemplate template="/WEB-INF/views/auth/form.jsp"/>

        <div class="form-group">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" placeholder="Enter the password" name="password" id="inputPassword">
        </div>
        <div class="form-group">
            <label for="inputRepeatPassword">Repeat the password</label>
            <input type="password" class="form-control" placeholder="Repeat the password" name="repeat_password"
                   id="inputRepeatPassword">
        </div>
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Change"/>
        </div>
    </form>
    <a href="<%=request.getContextPath() %>/profile/">Go to profile</a>
</div>