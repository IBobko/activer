<%--suppress ELValidationInJSP --%>
<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<style type="text/css">
    td {
        padding: 4px;
    }

    #page-content-wrapper {
        font-weight: normal;
    }
</style>

<table>
    <tr>
        <td valign="top" width="250">
            <img src="${staticFiles}/${photo.photoThumbnail}."/>
        </td>
        <td>

            <h4 style="color:#337ab7;font-weight:bold">Фото профиля</h4>
            Данное фото будет отображаться на главной странице профиля.
            <br/>
            Максимальный размер фото 10Mb.
            <form method="post" action="<c:url value="/settings/uploadphoto"/>" enctype="multipart/form-data">
                <input id="choosePhoto" name="photo" type="file"
                       style="cursor:pointer;position:absolute;height:34px;opacity: 0;overflow: hidden;width:165px">
                <div style="margin:20px 0">
                    <a id="choosePhotoButton" href="#" class="std-button btn btn-default"><span
                            class="fa fa-camera"></span>&nbsp;Выбрать
                        фото</a>

                    <a class="std-button btn btn-default" type="submit">Выбрать из галереи</a>
                </div>
            </form>

            <script>
                $("#choosePhoto").change(function () {
                    alert('changed!');
                });
            </script>
            <div style="margin:40px 0">
                <h4 style="color:#337ab7;font-weight:bold">Основная информация</h4>
                <form:form method="post" commandName="mainInfoForm">
                    <table>
                        <tr>
                            <td width="300">
                                Дата рождения
                            </td>
                            <td>
                                <div style="width:202px" class="input-group date form_date col-md-5" data-date=""
                                     data-date-format="dd/mm/yyyy" data-link-format="yyyy-mm-dd">
                                    <form:input type="text" path="birthDate" cssClass="form-control"
                                                cssStyle="background-color: white" readonly="true"/>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                </div>
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
                                <form:input cssClass="form-control" path="firstName"/>
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
                                <form:input cssClass="form-control" path="lastName"/>
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
                                <form:select path="sex" cssClass="form-control">
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
                                <form:select path="maritalStatus" cssClass="form-control">
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
                            <td colspan="2" style="text-align: center">
                                <input type="submit"
                                       class="std-button btn btn-default"
                                       value="Сохранить"/></td>
                            <td></td>
                        </tr>
                    </table>
                </form:form>
            </div>

            <div style="margin: 30px 0">
            <c:url var="advancedPost" value="/settings/advancedPost"/>
            <form:form method="post" action="${advancedPost}" commandName="childrenEducationJobForm">
                <h4 style="color:#337ab7;font-weight:bold">Образование</h4>

                <table>
                    <tr>
                        <td width="300">
                            Страна
                        </td>
                        <td>
                            <form:select path="educationForm.country" cssClass="form-control">
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
                            <form:input path="educationForm.city" cssClass="form-control"/>
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
                            <form:input path="educationForm.university" cssClass="form-control"/>
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
                            <form:input path="educationForm.faculty" cssClass="form-control"/>
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
                            <form:input path="educationForm.year" cssClass="form-control"   />
                        </td>
                        <td>
                            <form:errors path="educationForm.year"/>
                        </td>
                    </tr>

                </table>
            </div>
            <div style="margin: 30px 0">
                <h4 style="color:#337ab7;font-weight:bold">Работа</h4>

                <table>
                    <tr>
                        <td width="300">
                            Место работы
                        </td>
                        <td>
                            <form:input path="jobForm.work" cssClass="form-control"/>
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
                            <form:input path="jobForm.city" cssClass="form-control"/>
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
                            <form:input path="jobForm.post" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="jobForm.post"/>
                        </td>
                    </tr>

                </table>
            <div style="margin: 30px 0">
                <h4 style="color:#337ab7;font-weight:bold">Дети</h4>
                <table>
                    <tr>
                        <td width="300">
                            Имя
                        </td>
                        <td>
                            <form:input path="childrenForm.name" cssClass="form-control"/>
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
                            <form:input path="childrenForm.year" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="childrenForm.year"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" value="Сохранить" class="std-button btn btn-default"/></td>
                    </tr>
                </table>
            </form:form>
            </div>

            <h4 style="color:#337ab7;font-weight:bold">Привязка к PayPal</h4>
            <form:form method="post" commandName="payPalForm">
                <table>
                    <tr>
                        <td width="300">
                            PayPal account
                        </td>
                        <td>
                            <form:input type="text" path="login" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="login"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" class="std-button btn btn-default" value="Привязать"/></td>
                        <td></td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>

</table>