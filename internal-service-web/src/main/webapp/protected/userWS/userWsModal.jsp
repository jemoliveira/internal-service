<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="addUserModal" class="modal fade" role="dialog"
	aria-labelledby="createUserModal" aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">
			<form name="newContactForm" novalidate>
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>
						<spring:message code="label.createNewUser"></spring:message>
					</h3>
				</div>

				<div class="modal-body">

					<div class="form-group">
						<div class="input-append">
							<label>* <spring:message code="label.user" />:
							</label>
						</div>
						<div class="input-append">
							<input type="text" required autofocus ng-model="bean.wsUser" ng-minlength="11" ng-maxlength="11"
								class="form-control" name="wsUser" />
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && newContactForm.wsUser.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrWsUser">
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
							<input type="number" required ng-model="bean.ascCode" class="form-control" ng-minlength="7" ng-maxlength="7"
								name="ascCode"/>
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && newContactForm.ascCode.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrAscCode">
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
							<input type="text" required ng-model="bean.corpCode"  class="form-control" ng-minlength="4" ng-maxlength="4"
								name="corpCode"/>
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && newContactForm.corpCode.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrCorpCode">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong>Company</strong> must have 4 characters.							
						</div>
					</div>
					
					<div class="form-group">
						<div class="input-append">
							<label>* <spring:message code="label.userws.password" />: <button class="btn btn-primary btn-xs" data-ng-click="createPasswordForNewUser()">Generate Password</button>
							</label>
						</div>
						<div class="input-append">
							<input type="text" required class="form-control" data-ng-model="bean.wsPwd" id="wsPwd" name="wsPwd" ng-minlength="10" ng-maxlength="10" 
								placeholder="The new strong password will appear here" disabled="disabled">					
						</div>
						<div class="alert alert-danger" ng-show="displayValidationError && newContactForm.wsPwd.$error.required">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong><spring:message code="required" /></strong>							
						</div>
						<div class="alert alert-danger" hidden id="divErrWsPwd">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
                    		<strong>Password</strong> must have 10 characters.							
						</div>
					</div>
										
					<div class="form-group">
						 <label class="checkbox-inline"><input type="checkbox" ng-model="bean.dev" name="dev"/>
						 <spring:message
									code="label.userws.dev"></spring:message>
						 </label>
							<label class="checkbox-inline"><input type="checkbox" ng-model="bean.prd" name="prd"/>
							<spring:message
									code="label.userws.prd"></spring:message>
							</label>
							<label class="checkbox-inline"><input type="checkbox" ng-model="bean.token" name="token"/>
							<spring:message
									code="label.userws.token"></spring:message>
							</label>
					</div>
					
					<div align="center" hidden id="downloadModal"><img src="<c:url value='/resources/img/ajax-loaders/ajax-loader-1.gif'  />"></div>
					
					<div class="alert alert-danger" align="center" id="returnMsgError" ng-show="returnMsg" >
	                    <button type="button" class="close" data-dismiss="alert">&times;</button>
	                    <strong>{{returnMsg}}</strong>	                    
	                </div>										
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary" ng-click="create(newContactForm);" value='<spring:message code="create"/>' />
					<button class="btn btn-default" data-dismiss="modal" ng-click="exit('#createUserModal');" aria-hidden="true">
						<spring:message code="cancel" />
					</button>
				</div>				
			</form>
		</div>
	</div>

</div>