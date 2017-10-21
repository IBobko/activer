<%@ page language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="admin" tagdir="/src/main/webapp/WEB-INF/tags/admin" %>

<h4 style="color: #3F51B5;font-weight:bold;">Баланс</h4>



<div id="balanceCreditWrapper" style="margin-top:40px" >
    <div style="margin:20px 0">Счета</div>
    <table class="table table-striped table-bordered"id="disputeList">
        <thead>
        <tr>
            <td>Аккаунт</td>
            <td>Сумма долга</td>
            <td>Описание</td>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagedData.elements}" var="balance">
            <admin:credit_line balance="${balance}"/>
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
            $.get("<c:url value="/admin/balancePaged"/>", data, function (response) {
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
    <admin:credit_line/>
</textarea>


<div id="balanceDebetWrapper" style="margin-top:40px" >
    <div style="margin:20px 0">Платежи</div>
    <table class="table table-striped table-bordered"id="dispute1List">
        <thead>
        <tr>
            <td>Аккаунт</td>
            <td>Сумма долга</td>
            <td>Описание</td>


        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pagedData.elements}" var="balance">
            <admin:credit_line balance="${balance}"/>
        </c:forEach>
        </tbody>
    </table>
</div>

<admin:paginator paginatorId="disputeListPaged" pagedData="${pagedData}" onClickFunction="page"/>

<script type="text/javascript">


    window.ACTIVER.DebetPayment = {
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
            $.get("<c:url value="/admin/balancePaged"/>", data, function (response) {
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

    window.ACTIVER.DebetPayment.init1('disputeList');

    function page1(p) {
        var data = {
            page: p
        };
        window.ACTIVER.DebetPayment.loadData(data);
        return false;
    }

    function sort1(field) {
        var data = {
            orderField: field,
            orderType: 'desc',
            page:0

        };
        window.ACTIVER.DebetPayment.loadData(data);
        return false;
    }
</script>

<textarea style="display:none" id="disputeList-line">
    <admin:credit_line/>
</textarea>