<%--suppress XmlPathReference --%>
<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/WEB-INF/tags/admin" %>

<style>
    .table>tbody>tr>td {
        font-weight: normal;
    }
</style>

<a class="std-button btn btn-default" style="float:right" href="<c:url value="/admin/dispute/add"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить тему спора</a>
<h4 style="color: #3F51B5;font-weight:bold;">Споры</h4>
<div id="disputeListWrapper">
<table class="table table-striped table-bordered" style="margin-top:40px" id="disputeList">
    <thead>
    <tr>
        <td>Тема</td>
        <td>Позиция 1</td>
        <td>Позиция 2</td>
        <td>Язык</td>
        <td></td>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pagedData.elements}" var="dispute">
        <tr>
            <td><a href="<c:url value="/admin/dispute/add?id=${dispute.id}"/>">${dispute.name}</a></td>
            <td>${dispute.position1}</td>
            <td>${dispute.position2}</td>
            <td></td>
            <td><a href="<c:url value="/admin/dispute/delete?id=${dispute.id}"/>">Удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    </div>

<admin:paginator paginatorId="disputeListPaged" pagedData="${pagedData}" onClickFunction="page"/>

<script type="text/javascript">


    window.ACTIVER.DisputeTheme = {
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
            $.get("<c:url value="/admin/disputePaged"/>", data, function (response) {
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

    window.ACTIVER.DisputeTheme.init1('disputeList');

    function page(p) {
        var data = {
            page: p
        };
        window.ACTIVER.DisputeTheme.loadData(data);
        return false;
    }

    function sort(field) {
        var data = {
            orderField: field,
            orderType: 'desc',
            page:0

        };
        window.ACTIVER.DisputeTheme.loadData(data);
        return false;
    }
</script>

<textarea style="display:none" id="disputeList-line">
    <admin:dispute-line/>
</textarea>