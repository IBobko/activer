<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><tiles:insertAttribute name="title"/></title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.3.custom.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.nestable.js"/>"></script>
	<script type="text/javascript">
	jQuery(function($){
		 $("#advancedSearchButton").click(function() {
			$('.advanced-search').toggle();
		 });
	});
	</script>
</head>
<body>
	<div style="font-size:20px;margin:20px">
	<spring:message code="label.description"/>
		<sec:authorize access="isAnonymous()">
			<spring:message code="label.youcan"/> <a href="<c:url value="/auth"/>"><spring:message code="label.login"/></a>
			<spring:message code="label.or"/> <a href="<c:url value="/auth/signup"/>"><spring:message code="label.signup"/></a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<spring:message code="label.your"/> <a href="<c:url value="/profile"/>"><spring:message code="label.profile"/></a>
			<spring:message code="label.or"/> <a href="<c:url value="/j_spring_security_logout"/>"><spring:message code="label.logout"/></a>.
			<a href="<c:url value="/model3d/upload"/>"><spring:message code="label.ShareSell"/></a>
		</sec:authorize>
	<div style="float:right">
		<a href="<c:url value="/?lang=en"/>"><img src="<c:url value="/resources/images/eng.png"/>"/></a>
		<a href="<c:url value="/?lang=ru"/>"><img src="<c:url value="/resources/images/rus.png"/>"></a>
  	</div>
	</div>
	</div>
	<nav class="navbar navbar-default" role="navigation">
	  <div class="navbar-header">
	    <a class="navbar-brand" href="<c:url value="/"/>">3DPLENTY</a>
	  </div>
	  <div class="collapse navbar-collapse navbar-ex1-collapse navbar-inverse">
		<ul class="nav navbar-nav">
			<sec:authorize access="isAuthenticated()">
			<li>
				<a href="<c:url value="/model3d/upload"/>">Upload</a>
			</li>
			<li>
				<a href="<c:url value="/wish"/>">Wishlist</a>
			</li>
			</sec:authorize>
			<li>
				<a href="<c:url value="/blog"/>">Blog</a>
			</li>
			<li>
				<a href="<c:url value="/about"/>">About</a>
			</li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li>
				<a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
					<li><a tabindex="-1" href="<c:url value="/admin"/>">Main</a></li>
					<li class="divider"></li>
					<li><a tabindex="-1" href="<c:url value="/admin/accounts"/>">Accounts</a></li>
					<li><a tabindex="-1" href="<c:url value="/admin/model3ds"/>">Models</a></li>
					<li><a tabindex="-1" href="<c:url value="/order"/>">Orders</a></li>
					<li><a tabindex="-1" href="<c:url value="/category/edit-list"/>">Categories</a></li>
					<li><a tabindex="-1" href="<c:url value="/mysql"/>">MySQL</a></li>
				</ul>
			</li>
			</sec:authorize>
		</ul>
	    <form class="navbar-form navbar-right" role="search" action="<c:url value="/model3d/search/"/>">
	      <div class="form-group">
	        <input type="text" class="form-control" placeholder="Search" name="s" value="${search_s}">
	      </div>
	      <div class="btn-group">
	          <button id="advancedSearchButton" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
	            Advanced
	            <span class="caret"></span>
	          </button>
	          <div class="advanced-search">
	            Price from <input type='text' class='form-control' style="width:100px" name="priceFrom" value="${search_priceForm}"/>
	            to <input type='text' class='form-control' style="width:100px" name="priceTo" value="${search_priceTo}"/>
	          </div>
	        </div>
	      <button type="submit" class="btn btn-default">Submit</button>
	    </form>
	  </div>
	</nav>
	
	<div style="float:left;width:200px">
		<tiles:insertTemplate template="/WEB-INF/views/category/item.jsp">
			<tiles:putAttribute name="categories" value="${categories}"/>
		</tiles:insertTemplate>
		
		<div class="alert alert-warning" >
		<div><strong>Marks</strong></div>
		<c:forEach var="mark" items="${marks}" varStatus="loop">
			<a href="<c:url value="/mark?s=${mark.name}"/>" style='font-size:${mark.fontSize}px'>${mark.name}</a>
		</c:forEach>
		</div>

		<div class="well">
		If you didn't find needed model, you can leave a wish in <a href="<c:url value="/wish"/>">wishlist</a>.
		</div>
		
	</div>
	
	<div class="container" style="margin-left:240px">
		<tiles:insertAttribute name="content"/>
	</div>
	<hr/>
	<footer style="text-align:center">
	(c) 2013. 3dplenty.com. <a href="mailto:support@3dplenty.com">support@3dplenty.com</a>. Projected by <a href="http://todo100.ru">http://todo100.ru</a>
	<br/><br/>
	</footer>
</body>
</html>
