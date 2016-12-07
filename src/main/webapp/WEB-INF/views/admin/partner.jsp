<%--suppress CssUnusedSymbol --%>
<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>

<style type="text/css">
    html {
        overflow-y: scroll;
    }

    .modal-open {
        padding-right: 0 !important;
    }

    .filter td {
        padding: 5px;
    }
</style>

<button class="std-button btn btn-default" style="float:right" data-toggle="modal"
        data-target="#inviteNewParnersWindow"><span
        class="glyphicon glyphicon-plus"></span> Пригласить новых партнеров
</button>

<div class="modal fade" id="inviteNewParnersWindow" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Приглашение</h4>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="myReferLink" readonly
                       value="http://onoffline.ru/auth/signup?referCode=${currentProfileData.referCode}"/>
                <div style="line-height: 50px">
                    Введи E-mail друга, чтобы он тоже смог присоедениться к нам
                    <input class="form-control" type="email" id="myFriendEmail">

                    <button type="submit" class="std-button btn btn-default" id="mySubmitButton"><span
                            class="glyphicon glyphicon-send"></span> Отправить
                    </button>
                </div>
            </div>
            <div class="modal-footer">
                <div style="float:left">Поделиться в социальных сетях:</div>
                <script type="text/javascript" src="//yastatic.net/es5-shims/0.0.2/es5-shims.min.js"
                        charset="utf-8"></script>
                <script type="text/javascript" src="//yastatic.net/share2/share.js" charset="utf-8"></script>
                <div class="ya-share2" data-services="vkontakte,facebook,odnoklassniki,moimir"
                     data-title="Моя ссылка на супер сайте. <a href='http://onoffline/auth/signup?referCode=${currentProfileData.referCode}'>http://onoffline/auth/signup?referCode=${currentProfileData.referCode}</a>"
                     data-description="Моя ссылка на супер сайте. <a href='http://onoffline/auth/signup?referCode=${currentProfileData.referCode}'>http://onoffline/auth/signup?referCode=${currentProfileData.referCode}</a> "></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#inviteNewParnersWindow').on('shown.bs.modal', function () {
            $("#inviteNewParnersWindow #myReferLink").select();
        });

        $('#mySubmitButton').click(function(){
            var data = {
                'email': $("#myFriendEmail").val()
            };
            $.post("<c:url value="/admin/partner/send_email"/>",data,function(response){
                alert(response);
            });
        });
    });
</script>

<br/><br/><br/><br/>

<table class="table table-hover">
    <tr>
        <td>Количество партнеров в каждом уровне</td>
        <td>сколько денег заработал каждый уровень</td>
        <td>сколько денег я получил с каждого уровня</td>
        <td>сколько всего у меня партнеров в сети (сумма всех партнеров</td>
        <td>сколько всего денег я заработал</td>
    </tr>
    <tr>
        <td>400$</td>
    </tr>
</table>

<table style="background-color:#FAFAFA" class="filter">
    <tr>
        <td>уровень партнера</td>
        <td>
            <select>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
            </select>
        </td>
        <td>уровень тех кто приглашает </td>
        <td>
            <select>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </td>
        <td><input type="button" class="std-button btn btn-default" value="Показать"/></td>
    </tr>
</table>

<div id="partnerListWrapper">
    <table class="table table-hover" id="partnerList">
        <thead>
        <tr>
            <td style="width:226px" data-order="accountName"><a href="javascript:sort('accountName')">Имя</a></td>
            <td data-order="accountLevel"><a href="javascript:sort('accountLevel')">Уровень</a></td>
            <td data-order="inviter"><a href="javascript:sort('inviter')">Пригласивший</a></td>
            <td data-order="inviterLevel"><a href="javascript:sort('inviterLevel')">Его уровень</a></td>
            <td data-order="invited"><a href="javascript:sort('invited')">Приглашенных</a></td>
            <td data-order="networkCount"><a href="javascript:sort('networkCount')">Колчиество людей в сети</a></td>
            <td data-order="earned"><a href="javascript:sort('earned')">Заработано</a></td>
            <td data-order="profit"><a href="javascript:sort('profit')">Моя прибыль</a></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagedData.elements}" var="partner">
            <tr>
                <admin:partner_line partner="${partner}"/>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<admin:paginator paginatorId="partnerListPaged" pagedData="${pagedData}" onClickFunction="page"/>

<script type="text/javascript">


    window.ACTIVER.AdminPartner = {
        list: null,
        currentPage: ${pagedData.page},
        init1: function (list_id) {
            this.list = list_id;
            $('#' + this.list + " [data-order]").on('click', function () {
                sort($(this).attr('data-order'))
            });
        },

        processResponse: function (response) {
            var line = $('#' + this.list + "-line");
            var listObject = $('#' + this.list + " tbody");
            listObject.html('');
            for (index in response.elements) {
                var lineBody = line.val();
                for (key in response.elements[index]) {
                    lineBody = lineBody.replace("#" + key, response.elements[index][key]);
                }
                listObject.append(lineBody);
            }
        },

        loadData: function (data) {
            this.updatePaginator(data.page);
            var wrapper = $('#' + this.list + 'Wrapper');
            wrapper.css('height', wrapper.height() + "px");
            var listObject = $('#' + this.list + " tbody");
            listObject.html('');
            var that = this;
            $.get("<c:url value="/admin/partnerPaged"/>", data, function (response) {
                that.processResponse(response);
            });
        },

        updatePaginator: function (page) {
            var paginator = $('#' + this.list + "Paged");
            var paginatorItem = $('#' + this.list + "PagedItem");
            paginator.find("[class='active']").removeClass('active');
            $('#' + this.list + "PagedItem" + (page + 1)).addClass('active');
        }
    };

    window.ACTIVER.AdminPartner.init1('partnerList');

    function page(p) {
        var data = {
            page: p
        };
        window.ACTIVER.AdminPartner.loadData(data);
        return false;
    }

    function sort(field) {
        var data = {
            orderField: field,
            orderType: 'desc',
            page:0

        };
        window.ACTIVER.AdminPartner.loadData(data);
        return false;
    }
</script>

<textarea style="display:none" id="partnerList-line">
    <admin:partner_line/>
</textarea>

