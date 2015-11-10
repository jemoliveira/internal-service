<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html lang="pt-BR" id="ng-app" ng-app="">
<head>
<title><spring:message code="project.name" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/bootstrap-theme.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/elegant-icons-style.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/font-awesome.min.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/line-icons.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/widgets.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/style-responsive.css' />" rel="stylesheet" />
<link href="<c:url value='/resources/css/jquery-ui-1.10.4.min.css' />" rel="stylesheet" />
    
<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon"
	href="<c:url value='/resources/img/favicon.ico'  />" />

<%-- <script src="<c:url value='/resources/js/jquery-1.7.2.min.js' />"></script> --%>
<script src="<c:url value='/resources/js/angular.min.js' />"></script>
<%--
<script type="text/javascript">
	window.onload = function () {
			  var timerId = 0;
			  var ctr=0;
			  var max=10;
			  
			  timerId = setInterval(function () {
			    // interval function
			    ctr++;
			    $('#blips > .progress-bar').attr("style","width:" + ctr*max + "%");
			    
			    // max reached?
			    if (ctr==max){
			      clearInterval(timerId);
			    }
			    
			  }, 500);
		};
</script>
--%>
</head>
<meta http-equiv="refresh">
<meta http-equiv="Content-Type" content="text/xhtml; charset=UTF-8" />


<body>

	<tiles:insertAttribute name="header" />
	
	<tiles:insertAttribute name="body" />
	
	<script src="<c:url value='/resources/js/jquery.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.10.4.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-1.8.3.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery-ui-1.9.2.custom.min.js' />"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.scrollTo.min.js' />"></script>
	<script src="<c:url value='/resources/js/jquery.nicescroll.js' />"></script>
    <script src="<c:url value='/resources/js/scripts.js' />"></script>
	<!-- 
	<script src="<c:url value='/resources/js/pages/timer.js' />"></script>  
	 -->

</body>
	<tiles:insertAttribute name="footer" />
</html>