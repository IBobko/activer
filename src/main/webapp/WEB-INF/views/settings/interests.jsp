<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value="/resources/js/inputTags.jquery.js"/>"></script>

<h4 style="color:#337ab7;font-weight:bold">Интересы</h4>

Интересы будут отображаться у вас на странице.
По ним другие пользователи смогут определить ваши общие интересы,
а сайт искать наиболее подходящие знакомства.


<link rel="stylesheet" href="<c:url value="/resources/css/inputTags.css"/>">

<br/>
<br/>
<input type="text" id="tags" value="" />

<script>
    $(function($) {
        window.tags = $('#tags').inputTags({
            tags: [<c:forEach var="interest" items="${interests}" varStatus="loop">
                    '${interest.name}'<c:if test="${!loop.last}">,</c:if>
                </c:forEach>],
            autocomplete: {
                //values: ['Pellentesque', 'habitant', 'morbi', 'tristique', 'senectus', 'netus', 'malesuada', 'fames', 'turpis', 'egestas', 'Vestibulum']
            },
            init: function(elem) {
                //$('span', '#events').text('init');
                //$('<p class="results">').html('<strong>Tags:</strong> ' + elem.tags.join(' - ')).insertAfter(elem.$list);
            },
            create: function() {
                //$('span', '#events').text('create');
            },
            update: function() {
                //$('span', '#events').text('update');
            },
            destroy: function() {
                //$('span', '#events').text('destroy');
            },
            selected: function() {
                //$('span', '#events').text('selected');
            },
            unselected: function() {
                //$('span', '#events').text('unselected');
            },
            change: function(elem) {
                //$('.results').empty().html('<strong>Tags:</strong> ' + elem.tags.join(' - '));
            },
            autocompleteTagSelect: function(elem) {
                //console.info('autocompleteTagSelect');
            }
        });
    });

    var saveTags = function(){
        var data = {};
        $('.inputTags-list span[data-tag]').each(function(index,e) {
            data["tags[" + index +"]"] = $(e).attr('data-tag');
        });
        $.post("<c:url value="/settings/interests"/>",data,function(){

        });
    }

    </script>

<input type="button" value="Сохранить" onclick="saveTags()"/>



