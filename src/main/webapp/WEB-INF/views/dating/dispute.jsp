<%--@elvariable id="staticImages" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dating" tagdir="/WEB-INF/tags/dating" %>

<style type="text/css">
    .gifts-list {
        overflow: hidden;
    }

    .gifts-list li {
        list-style: none;
        float: left;
        font-weight: normal;
        text-align: center;
    }

    .gifts-list img {
        width: 128px;
    }
</style>

<!-- Modal -->
<div class="modal fade" id="giftsPopup" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Подарок. Ваш баланс: <span id="balanceInModal">${profile.balance}</span>$</h4>
            </div>
            <div class="modal-body">
                <ul class="gifts-list">
                    <c:forEach items="${gifts}" var="gift">
                        <li>
                            <a href="#" class="giftForAdd" gift-id="${gift.id}"><img src="<c:url value="${staticImages}/${gift.file}"/>"></a>
                            <br/>
                            Стоимость: ${gift.cost}$
                        </li>
                    </c:forEach>
                </ul>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var interlocutor = ${disputeData.opponentId};
    $('.giftForAdd').click(function(){
        var data = ACTIVER.Global.message;
        data.to = interlocutor;
        data.message = "gift:" + $(this).attr("gift-id");
        data.type = "DISPUTE_MESSAGE";
        ACTIVER.Global.submit(data);
        $('#giftsPopup').modal('hide');
    });
</script>

<div>
    <div style="overflow: hidden">
        <img src="<c:url value="/resources/img/dispute.jpg"/>" style="float: left;margin: 0 30px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">Знакомство "Спор"</h4>Докажите собеседнику, что вы правы на все 100%. Защищайте свою позицию.
    </div>

    <div style="text-align: center">
        <h3 style="font-weight: bold; color:orange">${disputeData.themeTitle}</h3>
        <table style="width:600px;font-weight: normal" align="center">
            <tr>
                <td>
                    <div style="color: #3F51B5;font-weight: bold">${profile.firstName} ${profile.lastName}</div>
                    ${disputeData.yourPosition}
                </td>
                <td>
                    <img src="${staticImages}/${photo}" style="width:117px;margin:10px"/>
                </td>
                <td>
                    <img src="<c:url value="/resources/img/vs.jpg"/>" style="margin:10px"/></td>
                <td>
                    <img src="${staticImages}/${disputeData.opponentAvatar}" style="width:117px;margin:10px"/>
                </td>
                <td>
                    <div style="color: #3F51B5;font-weight: bold">${disputeData.opponentFistName} ${disputeData.opponentLastName}</div>
                    ${disputeData.opponentPosition}
                </td>
            </tr>
        </table>
        <div style="border:#b6beec 3px solid; overflow-y: scroll;height:300px" id="disputeMessageDialog"></div>
    </div>
    <br/>
    <form id="disputeForm">

        <button type="submit" style="float:right;margin-left:7px" class="std-button btn btn-default"><span class="fa fa-comment"></span>&nbsp;Отправить</button>
        <a data-toggle="modal" data-target="#giftsPopup" class="std-button btn btn-default" style="background-color:#eb1e63;margin-left:15px;font-size: 14px;width:34px;padding:7px 11px;float:right"><span class="fa fa-gift"></span></a>
        <div style="overflow: hidden">
            <input type="text" name="disputeMessage" class="form-control"/>
        </div>

    </form>
</div>

<script type="text/javascript">
    var m = new window.ACTIVER.Dialog.Messages('#disputeMessageTemplate',"",function(result){});

    window.ACTIVER.Global.handlers["DISPUTE_MESSAGE"] = function (data) {
        $('#disputeMessageDialog').append(m.getHtmlPost(data));
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
</script>
<textarea style="display:none" id="disputeMessageTemplate">
    <dating:message/>
</textarea>
