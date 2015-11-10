<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div ng-controller="controller">

	<dialog>
	  <p>This is da dialog!</p>
	  <button id="close">Close</button>
	</dialog>
	<button id="show">Open Dialog!</button>


</div>

<script src="<c:url value="/resources/js/pages/test.js" />"></script>