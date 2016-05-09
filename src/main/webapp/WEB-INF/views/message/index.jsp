<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    .interlocutor:hover{
        background-color: gray;
        cursor: pointer;
    }
</style>

<h4 style="color: #3F51B5;font-weight:bold;">Мои сообщения</h4>
<br/>
<table>
    <tr>
        <td style="width:300px"><input type="text" class="form-control" placeholder="Поиск диалога"></td>
        <td width="1%">                    <input type="submit"  style="margin-left:15px" class="std-button btn btn-default"
                                       value="Поиск"/></td>
        <td>                    <input type="submit"  style="float:right;margin-left:15px" class="std-button btn btn-default"
                                       value="Найти друга"/></td>
    </tr>
</table>
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
                            <span style="font-weight: normal;font-size: 12px">01.05.2015</span><br/>
                            <span style="font-weight: normal;font-size: 12px">Online</span>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </td>
        <td valign="top" width="100%">
        <div id="dialogWindow" style="height:500px;width:100%;overflow-y: scroll">

        </div>
            <div style="margin-top:20px">
                <form id="flirtForm">
                    <input type="submit"  style="float:right;margin-left:15px" class="std-button btn btn-default"
                           value="Отправить"/>
                    <div style="overflow: hidden">
                        <input id="text" name="flirtMessage" type="text" class="form-control"/>&nbsp;
                    </div>
                </form>
            </div>
        </td>
    </tr>
</table>

<script>
    var interlocutor = 0;
    $('.interlocutor').click(function(){
        var id = $(this).attr("interlocutor-id");
        interlocutor = id;
        $('#dialogWindow').html("Загрузка. Подождите...");
        $.get("<c:url value="/message/ajax/"/>" + id,function(data) {
            $('#dialogWindow').html("");
            for (index in data) {

                if (data.hasOwnProperty(index)) {
                    var template = $('#messageTemplate').html();
                    template = template.replace("#avatar",'${staticFiles}/' + data[index].sender.photo60x60 +'.');
                    template = template.replace("#sender",data[index].sender.firstName + " "+ data[index].sender.lastName);
                    template = template.replace("#message",data[index].text);
                    $('#dialogWindow').append(template);
                    console.log(data[index]);
                }
            }
            var objDiv = document.getElementById('dialogWindow');
            objDiv.scrollTop = objDiv.scrollHeight;
        });
    });
</script>

<div style="display:none" id="messageTemplate">
    <table>
        <tr>
            <td valign="top"><img src="#avatar" style="width:60px;height:60px;margin:10px"></td>
            <td valign="top"><div style="margin:10px">#sender<br/><span style="font-weight: normal">#message</span></div></td>
        </tr>
    </table>
</div>



<script type="text/javascript">
    window.ACTIVER.Global.onPRIVATE_MESSAGE = function(data){
        var template = $('#messageTemplate').html();
        template = template.replace("#avatar",'${staticFiles}/' + data.from.photo60x60 +'.');
        template = template.replace("#sender",data.from.firstName + " "+ data.from.lastName);
        template = template.replace("#message",data.message);
        $('#dialogWindow').append(template);
        var objDiv = document.getElementById('dialogWindow');
        objDiv.scrollTop = objDiv.scrollHeight;
    };
    $('#flirtForm').submit(function(){
        var data = window.ACTIVER.Global.message;
        data.to = interlocutor;
        data.message = $('#text').val();
        data.type = "PRIVATE_MESSAGE";
        $('#text').val('');
        window.ACTIVER.Global.submit(data);
        return false;
    });
    var objDiv = document.getElementById('dialogWindow');
    objDiv.scrollTop = objDiv.scrollHeight;
</script>