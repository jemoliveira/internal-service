<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- The styles -->
	  <!-- Bootstrap CSS -->    
	<link href="<c:url value='/resources/css/bootstrap.min.css'  />" rel="stylesheet" />
	<!-- bootstrap theme -->
	<link href="<c:url value='/resources/css/bootstrap-theme.css'  />" rel="stylesheet" />
	<!--external css-->
	<!-- font icon -->
	<link href="<c:url value='/resources/css/elegant-icons-style.css'  />" rel="stylesheet" />
	<link href="<c:url value='/resources/css/font-awesome.css'  />" rel="stylesheet" />
	<!-- Custom styles -->
	<link href="<c:url value='/resources/css/style.css'  />" rel="stylesheet" />
	<link href="<c:url value='/resources/css/style-responsive.css'  />" rel="stylesheet" />
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>

  <body class="login-img3-body">
<div class="container" ng-controller="loginController" >
		<div style="margin-left: auto; margin-right: auto; text-align: center; margin-top: 30px">
	  		<img alt="samsung" src="<c:url value='/resources/img/sgs.png'/>">
		</div>
      <form class="login-form" action="j_spring_security_check" style="margin-top: 30px"
					method="post">       
		<div>
				<h2 style="text-align:center;font-weight: bold">
					<spring:message code='project.name'/>
				</h2>
		</div> 
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input type="text" class="form-control" placeholder="Username" name="j_username"
								id="j_username" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" class="form-control" placeholder="Password" name="j_password"
								id="j_password">
            </div>
            <div class="alert alert-danger" ng-show="displayLoginError">
				<strong><spring:message code="login.error" /></strong>
			</div>
            <button class="btn btn-info btn-lg btn-block" type="submit">
            <spring:message code="login.signIn" /></button>
        </div>
      </form>

    </div>
<script src="<c:url value='/resources/js/pages/login.js' />"></script>


  </body>
</html>
