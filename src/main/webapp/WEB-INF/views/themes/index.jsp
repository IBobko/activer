<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h4 style="color:#337ab7;font-weight:bold">Темы профиля</h4>

Вы можете изменить интерфейс и визуальный вид ваше странички, выбрав понравившуюся тему ниже.


<table>
    <tr>
        <td>
            <a href="<c:url value="/themes/update/standard"/>">
                <img src="<c:url value="/resources/img/onoffline_standard_desktop.png"/>" width="300"/>
            </a>
        </td>
        <td>
            <a href="<c:url value="/themes/update/kitty"/>">
                <img src="<c:url value="/resources/img/onoffline_kity_desktop.png"/>" width="300"/>
            </a>
        </td>
        <td>
            <a href="<c:url value="/themes/update/carbone"/>">
               Карбон
            </a>
        </td>
    </tr>
</table>