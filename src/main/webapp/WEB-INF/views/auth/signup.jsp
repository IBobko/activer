<%--suppress XmlDuplicatedId,CssUnusedSymbol --%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    button[type="submit"] {
        font-size: 10px;
        font-weight: bold;
        color: #fff;
        background-color: #2f40a0;
        text-transform: uppercase;
        border-radius: 30px;
        border: none;
        padding: 10px 15px;
        letter-spacing: 3px;
    }

    #page-content-wrapper{
        font-weight: normal;
    }

</style>

<h4 style="color: #3F51B5;font-weight:bold; text-align: center">Добро пожаловать</h4>
<div style="text-align: center; margin:30px;">Предлагаем Вам оценить преимущества нашей соц.сети и зарегистрировать свой
    профиль!
</div>

<div style="text-align: center;margin:20px">
    <video poster="http://onoffline.ru/static/video/first-cadr.png" src="http://onoffline.ru/static/video/on-off-long.mp4"  width="640" height="480" controls></video>
</div>

<h4 style="color: #3F51B5;font-weight:bold; text-align: center">Мы меняем взгляд на социальные сети</h4>

<form:form method="post" modelAttribute="registerForm">
    <table>
        <tr>
            <td style="width:100%">
                <div style="width:350px;">
                        <%--@elvariable id="ie" type="ru.todo100.activer.util.InputError"--%>
                    <c:if test="${ie != null}">
                        <div class="alert alert-danger">
                            <ul>

                                <c:forEach items="${ie.errors}" var="error">
                                    <li>${error}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                        <%--@elvariable id="referAccount" type="ru.todo100.activer.model.AccountItem"--%>
                    <c:if test="${referAccount != null}">
                        Вы пришли сюда по ссылке ${referAccount}
                        <form:input path="refer" type="hidden"/>
                    </c:if>

                    <div class="form-group">
                        <form:label for="promoCode" path="promo">Промо-код</form:label>
                        <form:errors path="promo"/>
                        <form:input type="text" class="form-control" id="promoCode" placeholder="Код" path="promo"/>
                    </div>
                    <div class="form-group">
                        <form:label for="firstNameInput" path="firstName">Имя</form:label>
                        <form:errors path="firstName"/>
                        <form:input type="text" class="form-control" id="firstNameInput" placeholder="Введите имя"
                                    path="firstName"/>
                    </div>
                    <div class="form-group">
                        <form:label for="lastNameInput" path="lastName">Фамилия</form:label>
                        <form:errors path="lastName"/>
                        <form:input type="text" class="form-control" id="lastNameInput" placeholder="Введите фамилию"
                                    path="lastName"/>
                    </div>
                    <div class="form-group">
                        <form:label for="emailInput" path="email">E-mail</form:label>
                        <form:errors path="email"/>
                        <form:input type="email" class="form-control" id="emailInput" placeholder="Введите email"
                                    path="email"/>
                    </div>
                    <br/>
                            <div style="text-align: center">
                                <form:button type="submit" class="btn btn-default"><span
                                        class="glyphicon glyphicon-edit">&nbsp;</span>Зарегистрироваться
                                </form:button>
                            </div>
                </div>

            </td>
            <td><img src="<c:url value="/resources/img/1_Stranitsa_registratsii.png"/>"/></td>
        </tr>
    </table>
    <br/>
    <h3 style="color: #3F51B5;font-weight:bold; text-align: center">ON/OFF LINE ЭТО:</h3>

    <table>
        <tr>
            <td><img src="<c:url value="/resources/img/meet.png"/>"/></td>
            <td style="padding-left:50px;">
                <table>
                    <tr>
                        <td style="background: url(<c:url
                                value="/resources/img/rhombus.png"/>);color:white;width:21px;height:24px;text-align: center">
                            1
                        </td>
                        <td style="font-size:16px;color: #3F51B5;font-weight:bold; letter-spacing: 3px">
                            &nbsp;Знакомства
                        </td>
                    </tr>
                </table>
                <div style="margin:20px 0">Быстрые знакомства и уникальные способы найти вторую половинку</div>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <tr>
                        <td style="background: url(<c:url
                                value="/resources/img/rhombus.png"/>);color:white;width:21px;height:24px;text-align: center">
                            2
                        </td>
                        <td style="font-size:16px;color: #3F51B5;font-weight:bold; letter-spacing: 3px">&nbsp;Подарки и
                            сюрпризы
                        </td>
                    </tr>
                </table>
                <div style="margin:20px 0">Приятный интерфейс, подарки и сюрпризы.</div>
            </td>
            <td><img src="<c:url value="/resources/img/gifts.png"/>"/></td>
        </tr>
        <tr>
            <td><img src="<c:url value="/resources/img/earn.png"/>"/></td>
            <td style="padding-left:50px;">
                <table>
                    <tr>
                        <td style="background: url(<c:url
                                value="/resources/img/rhombus.png"/>);color:white;width:21px;height:24px;text-align: center">
                            3
                        </td>
                        <td style="font-size:16px;color: #3F51B5;font-weight:bold; letter-spacing: 3px">&nbsp;Рефералы и
                            заработок
                        </td>
                    </tr>
                </table>
                <div style="margin:20px 0">Премиум аккаунт, возможность заработка по реферальной системе</div>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <tr>
                        <td style="background: url(<c:url
                                value="/resources/img/rhombus.png"/>);color:white;width:21px;height:24px;text-align: center">
                            4
                        </td>
                        <td style="font-size:16px;color: #3F51B5;font-weight:bold; letter-spacing: 3px">&nbsp;Розыгрыши
                            и призы
                        </td>
                    </tr>
                </table>
                <div style="margin:20px 0">Участие в ежегодных розыгрышах компании</div>
            </td>
            <td><img src="<c:url value="/resources/img/successful.png"/>"/></td>
        </tr>
        <tr>
            <td><img src="<c:url value="/resources/img/communication.png"/>"/></td>
            <td style="padding-left:50px;">
                <table>
                    <tr>
                        <td style="background: url(<c:url
                                value="/resources/img/rhombus.png"/>);color:white;width:21px;height:24px;text-align: center">
                            5
                        </td>
                        <td style="font-size:16px;color: #3F51B5;font-weight:bold; letter-spacing: 3px">&nbsp;Бизнес-съезды</td>
                    </tr>
                </table>
                <div style="margin:20px 0">Бесплатное участие в ежегодных бизнес-съездах</div>
            </td>
        </tr>
    </table>

    <div style="text-align: center; margin:30px;">Переверни мир, сделай шаг к мечте!
        <br/><br/>
        <form:button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-edit">&nbsp;</span>Зарегистрироваться
        </form:button>

    </div>
</form:form>
