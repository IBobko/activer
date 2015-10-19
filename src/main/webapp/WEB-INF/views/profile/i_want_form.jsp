<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="well">
    <h3>Я могу</h3>

    <form:form method="post" commandName="iWantForm">
        <table>
            <tr>
                <td>Заголовок:</td>
                <td><form:input path="title" cssClass="form-control"/></td>
            </tr>
            <tr>
                <td>Описание:</td>
                <td><form:textarea path="description" cssClass="form-control"/></td>
            </tr>
            <tr>
                <td>Тэги:</td>
                <td><form:input cssClass="form-control" path="marks"/></td>
            </tr>
            <tr>
                <td colspan="2"><form:button type="submit" class="btn btn-default">Сохранить</form:button></td>
            </tr>
        </table>
    </form:form>
</div>