<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>
    td {
        padding: 4px;
    }
</style>

<h4 style="color:#337ab7;font-weight:bold">Фото профиля</h4>
<form method="post" action="<c:url value="/settings/uploadphoto"/>" enctype="multipart/form-data">
    <input type="file" name="photo">
    <input type="submit" value="Загрузить фото">
</form>

<h4 style="color:#337ab7;font-weight:bold">Основная информация</h4>
<form:form method="post" commandName="mainInfoForm">
    <table>
        <tr>
            <td>
                Дата рождения
            </td>
            <td>
                <form:input type="text" path="birthDate"/>
            </td>
            <td>
                <form:errors path="birthDate"/>
            </td>
        </tr>
        <tr>
            <td>
                Имя
            </td>
            <td>
                <form:input type="text" path="firstName"/>
            </td>
            <td>
                <form:errors path="firstName"/>
            </td>
        </tr>
        <tr>
            <td>
                Фамилия
            </td>
            <td>
                <form:input type="text" path="lastName"/>
            </td>
            <td>
                <form:errors path="lastName"/>
            </td>
        </tr>
        <tr>
            <td>
                Пол
            </td>
            <td>
                <form:select path="sex">
                    <form:option value="">не указано</form:option>
                    <form:option value="0">мужской</form:option>
                    <form:option value="1">женский</form:option>
                </form:select>
            </td>
            <td>
                <form:errors path="sex"/>
            </td>
        </tr>
        <tr>
            <td>
                Семейное положение
            </td>
            <td>
                <form:select path="maritalStatus">
                    <form:option value="">не выбрано</form:option>
                    <form:option value="0">не женат/не замужем</form:option>
                    <form:option value="1">встречаюсь</form:option>
                    <form:option value="2">помолвлен</form:option>
                    <form:option value="3">женат/замужем</form:option>
                    <form:option value="4">влюблён</form:option>
                    <form:option value="5">все сложно</form:option>
                    <form:option value="6">в активном поиске</form:option>
                </form:select>
            </td>
            <td>
                <form:errors path="maritalStatus"/>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="Сохранить"/></td>
            <td></td>
        </tr>
    </table>
</form:form>
<c:url var="advancedPost" value="/settings/advancedPost"/>
<form:form method="post" action="${advancedPost}" commandName="childrenEducationJobForm">
    <h4 style="color:#337ab7;font-weight:bold">Образование</h4>

    <table>
        <tr>
            <td>
                Страна
            </td>
            <td>
                <form:select path="educationForm.country">
                    <form:option value="">не указано</form:option>
                    <c:forEach items="${countries}" var="country">
                        <form:option value="${country.code}">${country.name}</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                Город
            </td>
            <td>
                <form:input path="educationForm.city"/>
            </td>
            <td>
                <form:errors path="educationForm.city"/>
            </td>
        </tr>
        <tr>
            <td>
                Университет / Институт
            </td>
            <td>
                <form:input path="educationForm.university"/>
            </td>
            <td>
                <form:errors path="educationForm.university"/>
            </td>
        </tr>
        <tr>
            <td>
                Факультет
            </td>
            <td>
                <form:input path="educationForm.faculty"/>
            </td>
            <td>
                <form:errors path="educationForm.faculty"/>
            </td>
        </tr>
        <tr>
            <td>
                Год окончания
            </td>
            <td>
                <form:input path="educationForm.year"/>
            </td>
            <td>
                <form:errors path="educationForm.year"/>
            </td>
        </tr>

    </table>

    <h4 style="color:#337ab7;font-weight:bold">Работа</h4>

    <table>
        <tr>
            <td>
                Место работы
            </td>
            <td>
                <form:input path="jobForm.work"/>
            </td>
            <td>
                <form:errors path="jobForm.work"/>
            </td>
        </tr>
        <tr>
            <td>
                Город
            </td>
            <td>
                <form:input path="jobForm.city"/>
            </td>
            <td>
                <form:errors path="jobForm.city"/>
            </td>
        </tr>
        <tr>
            <td>
                Должность
            </td>
            <td>
                <form:input path="jobForm.post"/>
            </td>
            <td>
                <form:errors path="jobForm.post"/>
            </td>
        </tr>

    </table>

    <h4 style="color:#337ab7;font-weight:bold">Дети</h4>
    <table>
        <tr>
            <td>
                Имя
            </td>
            <td>
                <form:input path="childrenForm.name"/>
            </td>
            <td>
                <form:errors path="childrenForm.name"/>
            </td>
        </tr>
        <tr>
            <td>
                Год рождения
            </td>
            <td>
                <form:input path="childrenForm.year"/>
            </td>
            <td>
                <form:errors path="childrenForm.year"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form:form>