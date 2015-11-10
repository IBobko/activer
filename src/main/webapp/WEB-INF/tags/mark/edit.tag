<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="markText" required="true" type="java.lang.String" %>
<span class="label label-default">
    ${markText}&nbsp;
    <input type="hidden" value="${markText}" name="mark[]">
    <span class="glyphicon glyphicon-remove"></span>
</span>