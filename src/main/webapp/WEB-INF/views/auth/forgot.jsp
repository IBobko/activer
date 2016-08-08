<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Восстановление пароля</h1>
<hr/>
<div style="width:300px;">
    <%--&lt;%&ndash;@elvariable id="ie" type="ru.todo100.activer.util.InputError"&ndash;%&gt;--%>
    <%--<c:if test="${ie != null}">--%>
        <%--<div class="alert alert-danger">--%>
            <%--<ul>--%>
                <%--<c:forEach items="${ie.errors}" var="error">--%>
                    <%--<li>${error}</li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</c:if>--%>
    <c:if test="${account != null}">
        <div class="alert alert-success">Please check your mail.</div>
    </c:if>
    <form:form method="post" commandName="accountEmailForm">
        <form:errors path="email" cssClass="alert alert-danger"/>
        <br/>        <br/>
        <div class="form-group">
            <label for="exampleInputEmail">Email address</label>
            <form:input path="email" type="text" class="form-control" id="exampleInputEmail" placeholder="Enter email" name="email"/>
        </div>
        <button type="submit" class="btn btn-default">Recovery</button>
    </form:form>
</div>