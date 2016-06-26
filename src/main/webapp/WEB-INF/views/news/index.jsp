<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="news" tagdir="/WEB-INF/tags/news" %>


<link href="<c:url value="/resources/yoxview/yoxview.css"/>" rel="stylesheet"/>




<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"/>"></script>



<%--<ul id="photos" class="thumbnails yoxview">--%>
<%--<c:forEach items="${photos}" var="photo">--%>
<%--<li>--%>
<%--<a href="${staticFiles}/${photo.path.trim()}.jpg"><img photo-id="${photo.id}"--%>
<%--style="width:200px; height:130px"--%>
<%--src="${staticFiles}/${photo.middlePath.trim()}.jpg"--%>
<%--alt="${photo.description}"--%>
<%--title="${photo.description}"/></a>--%>
<%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>



<h3 class="title">Новости</h3>

<div id="newsBand" style="overflow-y: scroll; height:600px">
    <c:forEach var="new" items="${news}">
        <news:item news="${new}"/>
    </c:forEach>
</div>

<textarea style="display:none" id="news-template">
    <news:item />
</textarea>

<script type="text/javascript">


    var m = new window.ACTIVER.Dialog.Messages('#news-template',"<c:url value="/wall/publish"/>",function(result){

    });

    var end = false;
    var already_downloading = false;
    var loaded = 1;
    var lastScrollTop = 0;
    $('#newsBand').scroll(function (e) {
        var st = $(this).scrollTop();
        if (st < lastScrollTop){
            return;
        }
        lastScrollTop = st;
        if (end) return;
        if (already_downloading) return;
        var scroll = $(this).scrollTop() + $(this).height();

        if (scroll > this.scrollHeight - 200) {
            already_downloading = true;
            $.ajax({
                url: "<c:url value="/news/ajax"/>",
                data: {
                    page: loaded
                }
            }).done(function (data) {
                already_downloading = false;
                if (data.length == 0){
                    end = true;
                }
                console.log(data);
                for (var index in data) {

                    var template = m.getHtmlPost(data[index]);

                    $("#newsBand").append(template);
                }
                loaded++;
            });
        }
    });
</script>


<script type="text/javascript">
    jQuery.noConflict();
    jQuery(document).ready(function () {


        jQuery(".yoxview").yoxview(
                {
                    backgroundColor: '#000000',
                    backgroundOpacity: 0.8,
                    lang: 'ru',
                });

    });
</script>
<script type="text/javascript" src="<c:url value="/resources/yoxview/yoxview-init.js"/>"></script>