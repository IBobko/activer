<%--suppress HtmlUnknownAnchorTarget --%>
<%--@elvariable id="staticFiles" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    .interlocutor:hover{
        background-color: #f0f2fe;
        cursor: pointer;
    }

    html {
        overflow-y: scroll;
    }

    .modal-open {
        padding-right: 0 !important;
    }
</style>

<h4 style="color: #3F51B5;font-weight:bold;">Мои сообщения</h4>
<br/>
<table>
    <tr>
        <td style="width:300px">
            <input type="text" class="form-control" id="textForSearch" placeholder="Поиск диалога">
        </td>
        <td width="1%">
            <a id="searchDialogButton" style="margin-left:15px" class="std-button btn btn-default"><span
                    class="fa fa-search"></span>&nbsp;Поиск</a>
        </td>
        <td>
            <a data-toggle="modal" data-target="#startDialogWithFriend" style="float:right;"
               class="std-button btn btn-default"><span class="fa fa-plus"></span>&nbsp;Найти друга</a>
        </td>
    </tr>
</table>

<!-- Modal -->
<div class="modal fade" id="startDialogWithFriend" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Поиск друга</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="giftsPopup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Поиск друга</h4>
            </div>
            <div class="modal-body">
                ...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>



<script type="text/javascript">
    $(function () {
        var interlocutors = $('#interlocutors');

        $('#searchDialogButton').click(function () {
            interlocutors.html("<div style='text-align:center'><img src='<c:url value="/resources/img/progress.gif"/>'/></div>");
            var textForSearch = $('#textForSearch').val();
            var data = {
                t: textForSearch
            };
            /**
             * @param response {{owner:{photo60x60,lastName,firstName}}}
             */
            $.get("<c:url value="/message/search"/>", data, function (response) {
                interlocutors.html('');
                for (var index in response) {
                    if (response.hasOwnProperty(index)) {
                        var template = $('#dialogTemplate').html();
                        template = template.replace("#id", response[index].owner.id);
                        template = template.replace("#ownerphoto", response[index].owner.photo60x60);
                        template = template.replace("#ownerlastname", response[index].owner.lastName);
                        template = template.replace("#ownerfirstname", response[index].owner.firstName);
                        template = template.replace("#onoffline", response[index].owner.online ? "online":"offline");
                        template = template.replace("#date", response[index].lastMessage.date);
                        interlocutors.append(template);
                    }
                }
            });
        });
        var startDialogWithFriend = $('#startDialogWithFriend');
        var modalBody = startDialogWithFriend.find(".modal-body");
        startDialogWithFriend.on('show.bs.modal', function (e) {
            modalBody.html("<div style='text-align:center'><img src='<c:url value="/resources/img/progress.gif"/>'/></div>");
            $.get("<c:url value="/friend/ajax"/>", {}, function (response) {
                /*todo здесь необходимо добавить блок, для отображение друга. Он по идее должен быть общий*/
                modalBody.html('');
                console.log(response);
                var friends = response.friends;
                for (var index in friends) {
                    if (friends.hasOwnProperty(index)) {
                        var template = $('#friendTempale').html();
                        template = template.replace(/#id/gi, friends[index].id);
                        template = template.replace("#name", friends[index].firstName + " " + friends[index].lastName);
                        template = template.replace(/#photo/gi, friends[index].photo60x60);
                        modalBody.append(template);
                    }
                }
            });
        });

    });

    function addFried(data) {
        var interlocutors = $('#interlocutors');
        var block = $('#startDialogWithFriend').find(".manBlock[friend-id='" + data + "']");
        var photo = block.find(".friend_photo").attr('filename');
        var name = block.find(".friend_name").html();

        var exists = $("#interlocutors").find("[interlocutor-id='" + data + "']");
        if (exists.length == 0) {
            var template = $('#dialogTemplate').html();
            template = template.replace("#id", data);
            template = template.replace("#ownerphoto", photo);
            template = template.replace("#ownerlastname", "");
            template = template.replace("#ownerfirstname", name);
            template = template.replace("#onoffline", "online");
            template = template.replace("#date", "");
            interlocutors.append(template);
        }
        interlocutor = data;
        window.history.pushState('page2', 'Title', '<c:url value="/message?dialog="/>' + data);
    }


    $("#giftsPopup").on('show.bs.modal', function (e) {
        alert("he");
    });

</script>

<br/>
<table>
    <tr>
        <td valign="top">
            <div id="interlocutors" style="width:300px">
                <c:forEach items="${dialogs}" var="dialog">
                    <div style="overflow: hidden" class="interlocutor" interlocutor-id="${dialog.owner.id}">
                        <img style="margin:10px;float:left" width="60" src="${staticFiles}/${dialog.owner.photo60x60}." height="60"/>
                        <div style="margin:10px">
                            <span style="color:#337ab7">${dialog.owner.lastName} ${dialog.owner.firstName}</span><br/>
                            <span style="font-weight: normal;font-size: 12px">${dialog.lastMessage.date}</span><br/>
                            <span style="font-weight: normal;font-size: 12px">${dialog.owner.online? "online":"offline"}</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </td>
        <td valign="top" width="100%">
        <div id="dialogWindow" style="height:500px;width:100%;overflow-y: scroll">

        </div>
            <div style="margin-top:20px">
                <form id="sendMessageForm">
                    <button type="submit" style="float:right;margin-left:7px" class="std-button btn btn-default"><span class="fa fa-comment"></span>&nbsp;Отправить</button>
                    <a data-toggle="modal" data-target="#giftsPopup" class="std-button btn btn-default" style="background-color:#eb1e63;margin-left:15px;font-size: 14px;width:34px;padding:7px 11px;float:right"><span class="fa fa-gift"></span></a>
                    <div style="overflow: hidden">
                        <input id="text" name="flirtMessage" type="text" class="form-control"/>
                    </div>
                </form>
            </div>
        </td>
    </tr>
</table>
<br/><br/><br/>

<script type="text/javascript">

    var dialog = '${param["dialog"]}';

    var interlocutor = ${not empty param["dialog"]?param["dialog"]:0};

    if (interlocutor!=0) {
        initDialog(interlocutor)
    }

    $('#giftButton').click(function(){

    });

    function initDialog(id) {
        interlocutor = id;
        window.history.pushState('page2', 'Title', '<c:url value="/message?dialog="/>' + interlocutor);
        $('#dialogWindow').html("Загрузка. Подождите...");
        $.get("<c:url value="/message/ajax/"/>" + id,function(data) {
            dialogWindow.html("");
            for (var index in data) {
                if (data.hasOwnProperty(index)) {
                    var template = $('#messageTemplate').html();
                    //noinspection JSUnresolvedVariable
                    template = template.replace("#avatar",'${staticFiles}/' + data[index].sender.photo60x60 +'.');
                    template = template.replace("#sender",data[index].sender.firstName + " "+ data[index].sender.lastName);
                    template = template.replace("#message",data[index].text);
                    template = template.replace("#time",data[index].date);
                    dialogWindow.append(template);
                    console.log(data[index]);
                }
            }
            scrollDialogWindow();
        });
    }


    var dialogWindow = $('#dialogWindow');
    $('.interlocutor').click(function(){
        var id = $(this).attr("interlocutor-id");
        initDialog(id);
    });
</script>

<div style="display:none" id="messageTemplate">
    <table width="100%">
        <tr>
            <td valign="top" style="width:70px"><img src="#avatar" style="width:60px;height:60px;margin:10px"></td>
            <td valign="top" ><div style="margin:10px"><span style="color:gray">#sender</span><br/><span style="font-weight: normal">#message</span></div></td>
            <td valign="top" style="width:150px;font-weight: normal">#time</td>
        </tr>
    </table>
</div>

<div id="friendTempale" style="display:none">
    <div class="manBlock" style="overflow:hidden;margin:10px" friend-id="#id">
        <img filename="#photo" class="friend_photo" src="${staticFiles}/#photo." width="80" style="float:left">
        <div style="margin: 0 100px">
            <a class="friend_name" href="<c:url value="/profile/id"/>#id">#name</a><br>
            <a style="font-weight: normal" href="javascript:addFried('#id')">Написать сообщение</a>
            <span>#onoffline</span><br>
        </div>
    </div>
</div>


<div style="display:none" id="dialogTemplate">
    <div style="overflow: hidden" class="interlocutor" interlocutor-id="#id">
        <img style="margin:10px;float:left" width="60" src="${staticFiles}/#ownerphoto." height="60"/>
        <div style="margin:10px">
            <span style="color:#337ab7">#ownerlastname #ownerfirstname</span><br/>
            <span style="font-weight: normal;font-size: 12px">#date</span><br/>
            <span style="font-weight: normal;font-size: 12px">#onoffline</span>
        </div>
    </div>
</div>


<script type="text/javascript">
    function scrollDialogWindow() {
        var objDiv = document.getElementById('dialogWindow');
        objDiv.scrollTop = objDiv.scrollHeight;
    }

    ACTIVER.Global.handlers["PRIVATE_MESSAGE"] = function (data) {
        var template = $('#messageTemplate').html();
        template = template.replace("#avatar",'${staticFiles}/' + data.from.photo60x60 +'.');
        template = template.replace("#sender",data.from.firstName + " "+ data.from.lastName);
        template = template.replace("#message",data.message);
        $('#dialogWindow').append(template);
        scrollDialogWindow();
    };
    var text = $('#text');

    $('#sendMessageForm').submit(function () {
        var data = ACTIVER.Global.message;
        data.to = interlocutor;
        data.message = text.val();
        data.type = "PRIVATE_MESSAGE";
        text.val('');
        ACTIVER.Global.submit(data);
        return false;
    });
</script>