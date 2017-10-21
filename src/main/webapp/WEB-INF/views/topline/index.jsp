<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4 style="color:#337ab7;font-weight:bold">TOP LINE</h4>

<form action="<c:url value="/top-line/buy"/>" method="post">
    <label for="message_for_topline">Cообщение</label>
    <textarea name="message" id="message_for_topline" class="form-control" style="height:100px" maxlength="200" ></textarea>
    <br/>
    <input type="submit" class="btn btn-default std-button" value="Разместиться"/>
</form>
