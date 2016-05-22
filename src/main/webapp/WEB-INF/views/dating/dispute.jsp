<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <div style="overflow: hidden">
        <img src="<c:url value="/resources/img/dispute.jpg"/>" style="float: left;margin: 0 30px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">Знакомство "Спор"</h4>
    </div>

    <div style="text-align: center">
        <h3 style="font-weight: bold; color:orange">${disputeData.themeTitle}</h3>
        <table style="width:300px" align="center">
            <tr>
                <td>
                    ${profile.firstName} ${profile.lastName}<br/>${disputeData.yourPosition}
                </td>
                <td>
                    <img src="http://onoffline.ru/static/upload/files/${photo}.jpg" style="width:117px;margin:10px"/>
                </td>
                <td>
                    <img src="<c:url value="/resources/img/vs.jpg"/>" style="margin:10px"/></td>
                <td>
                    <img src="http://onoffline.ru/static/upload/files/${disputeData.opponentAvatar}.jpg"
                         style="width:117px;margin:10px"/>
                </td>
                <td>
                    ${disputeData.opponentFistName} ${disputeData.opponentLastName}<br/>${disputeData.opponentPosition}
                </td>
            </tr>
        </table>
        <div style="border:#b6beec 3px solid; overflow-y: scroll;height:300px" id="disputeMessageDialog"></div>
    </div>
    <br/>
    <form id="disputeForm">
        <input type="text" name="disputeMessage" class="form-control" style="float:left; width:700px"/>
        <input type="submit" value="Отправить" class="std-button btn btn-default"/>
    </form>
</div>

<script type="text/javascript">
    window.ACTIVER.Global.handlers["DISPUTE_MESSAGE"] = function (data) {
        var message = $('#disputeMessageTemplate').html();
        var dateObj = new Date(data.date);
        message = message.replace("#message", data.message);
        message = message.replace("#name", data.from.firstName + " " + data.from.lastName);
        message = message.replace("#avatar", data.from.photo60x60 + ".jpg");
        message = message.replace("#time", dateObj.getHours() + ":" + dateObj.getMinutes());
        $('#disputeMessageDialog').append(message);
        var objDiv = document.getElementById("disputeMessageDialog");
        objDiv.scrollTop = objDiv.scrollHeight;
    };

    $('#disputeForm').submit(function () {
        var data = window.ACTIVER.Global.message;
        var input = $('[name="disputeMessage"]');
        data.to = ${disputeData.id};
        data.message = input.val();
        data.type = "DISPUTE_MESSAGE";
        input.val('');
        window.ACTIVER.Global.submit(data);
        return false;
    });

    var dateObj = new Date();

</script>
<div style="display:none" id="disputeMessageTemplate">
    <div style="overflow: hidden">
        <img src="http://onoffline.ru/static/upload/files/#avatar" style="float:left;margin:10px;width:50px;"/>
        #name<br/><span style="font-weight: normal">#message</span><span
            style="float:right">#time</span>
    </div>
</div>