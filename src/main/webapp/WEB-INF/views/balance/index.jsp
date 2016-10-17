<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--suppress XmlDuplicatedId --%>
<style>
    table {
        margin-top:10px;
    }
    table td {
        padding-right:20px;
    }
</style>

<h3 style="color: #3F51B5;font-weight:bold;">Баланс</h3>

<div style="font-size: 24px;color: #3F51B5;font-weight:normal;">
    Текущий баланс: ${balance.sum}
</div>

<div style="color: #3F51B5;margin-top:30px;">
    Вы можете пополнить свой счет используя форму ниже
</div>

<div>

</div>
<form method="post">
    <table>
        <tr>
            <td>Пополнить счет</td>
            <td><input type="text" class="form-control" style="width: 100px" value="50" name="sum"/></td>
            <td><button type="submit" class="std-button btn btn-default">Пополнить через PayPal</button></td>
        </tr>
    </table>




</form>