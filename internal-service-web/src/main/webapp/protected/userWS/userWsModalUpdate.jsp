<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="updateUserModal" class="modal fade" role="dialog"
	aria-labelledby="updateUserModal" aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">
			<form name="updateUserForm" novalidate>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>
						<spring:message code="label.updateUser"></spring:message>
					</h3>
				</div>

				<div class="modal-body">

					<div class="form-group">
						<div class="input-append">
							<label>* <spring:message code="label.user" />:
							</label>
						</div>
						<div class="input-append">
							<input type="text" required autofocus ng-model="contact.wsUser" ng-minlength="11" ng-maxlength="11"
								class="form-control" name="wsUserUpdate" disabled="true"/>
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && updateUserForm.wsUserUpdate.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrWsUserUpdate">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong>User</strong> must have 11 characters.							
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-append">
							<label>* <spring:message code="label.asc" />:
							</label>
						</div>
						<div class="input-append">
							<input type="number" required ng-model="contact.ascCode" class="form-control" ng-minlength="7" ng-maxlength="7"
								name="ascCodeUpdate"/>
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && updateUserForm.ascCodeUpdate.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrAscCodeUpdate">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong>Asc Code</strong> must have 7 characters.							
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-append">
							<label>* <spring:message code="monitor.company" />:
							</label>
						</div>
						<div class="input-append">
							<input type="text" required ng-model="contact.corpCode"  class="form-control" ng-minlength="4" ng-maxlength="4"
								name="corpCodeUpdate"/>
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && updateUserForm.corpCodeUpdate.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrCorpCodeUpdate">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong>Company</strong> must have 4 characters.							
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-append">
							<label>* <spring:message code="label.userws.password" />: <button class="btn btn-primary btn-xs" data-ng-click="createPasswordForUpdatedUser()">Generate Password</button>
							</label>
						</div>
						<div class="input-append">
							<input type="text" required class="form-control" data-ng-model="contact.wsPwd" id="wsPwdUpdate" name="wsPwdUpdate" ng-minlength="10" ng-maxlength="10" 
								placeholder="The new strong password will appear here" disabled="disabled">					
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && updateUserForm.wsPwdUpdate.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrWsPwdUpdate">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong>Password</strong> must have 10 characters.							
						</div>
					</div>
										
					<div class="form-group">
						 <label class="checkbox-inline"><input type="checkbox" ng-model="contact.dev" name="devUpdate"/>
						 <spring:message
									code="label.userws.dev"></spring:message>
						 </label>
							<label class="checkbox-inline"><input type="checkbox" ng-model="contact.prd" name="prdUpdate"/>
							<spring:message
									code="label.userws.prd"></spring:message>
							</label>
							<label class="checkbox-inline"><input type="checkbox" ng-model="contact.token" name="tokenUpdate"/>
							<spring:message
									code="label.userws.token"></spring:message>
							</label>
					</div>
					<div align="center" hidden id="downloadModalUpdate"><img src="<c:url value='/resources/img/ajax-loaders/ajax-loader-1.gif'  />"></div>
					
					<div class="alert alert-danger" align="center" id="returnMsgErrorUpdate" ng-show="returnMsg" >
	                    <button type="button" class="close" data-dismiss="alert">&times;</button>
	                    <strong>{{returnMsg}}</strong>	                    
	                </div>										
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary" ng-click="update(updateUserForm);" value='<spring:message code="update"/>' />
					<button class="btn btn-default" data-dismiss="modal" ng-click="exit('#updateUserModal');" aria-hidden="true">
						<spring:message code="cancel" />
					</button>
				</div>				
			</form>
		</div>
	</div>

</div>