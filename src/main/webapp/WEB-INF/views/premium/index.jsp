<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 class="title">Покупка Premium Аккаунта</h3>
Цена покупки составляет: ${premiumPrice} <br/>
Ваш баланс: ${currentProfileData.balance} <br/><br/>
<a href="<c:url value="/premium/buy"/>" class="std-button btn btn-default" >Купить Premium аккаунт</a>