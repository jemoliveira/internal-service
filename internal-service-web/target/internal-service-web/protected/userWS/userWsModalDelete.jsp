<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="deleteUserModal" role="dialog"
		aria-labelledby="deleteUserModal" aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">
			
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">×</button>
				<h4 class="modal-title">
					<spring:message code="label.deleteUser"></spring:message>
				</h4>
			</div>
			<div class="modal-body">
				<p>
					<spring:message code="delete.confirm" />&nbsp;<span style="font-weight: bold;">{{contact.wsUser}}</span>&nbsp;?
				</p>
				<div align="center" hidden id="downloadModalDelete"><img src="<c:url value='/resources/img/ajax-loaders/ajax-loader-1.gif'  />"></div>
			</div>
			<div class="modal-footer">
				<input type="button" id="btnConfirmDeleteUser" class="btn btn-primary" ng-click="deleteUser()" value='<spring:message code="confirm"/>' />
				<button class="btn btn-default" data-dismiss="modal" ng-click="exit('#deleteUserModal');" aria-hidden="true">
					<spring:message code="cancel" />
				</button>
			</div>
			
		</div>
	</div>
</div>