<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<section class="wrapper">
<div class="row-fluid" ng-controller="controller" >
	
	<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog"
		aria-labelledby="loadingModal" aria-hidden="true">

		<div class="modal-dialog">
			<div class="modal-content">
	
				<table width="100%"
					style="text-align: right; margin-left: auto; margin-right: 0px;"
					border="0" cellpadding="2" cellspacing="2">
					<tr>
						<td style="text-align: center;"><h3>
								<spring:message code="message.loading" />
							</h3></td>
					</tr>
					<tr>
						<td style="text-align: center;"><img
							src="<c:url value='/resources/img/ajax-loaders/ajax-loader-7.gif'  />"></td>
						<link rel="shortcut icon"
							href="<c:url value='/resources/img/ajax-loaders/ajax-loader-7.gif'  />" />
					</tr>
				</table>
				<br>
			</div>
		</div>
	</div>
	
	<div>				
		<!-- start table of user -->
		<div class="row">
			<div class="box col-md-12">
				<div class="box-inner">
					<div class="box-header well" data-original-title="" ng-class="{'text-center': displayCreateContactButton == true, 'none': displayCreateContactButton == false}">
						<h2>
							<a href="#addUserModal" 
				               role="button"
				               ng-click="resetContact();"
				               title="<spring:message code='create'/>&nbsp;<spring:message code='label.createNewUser'/>"
				               class="btn btn-inverse"
				               data-toggle="modal">
				                <i class="icon_profile"></i>
				                &nbsp;<spring:message code="label.createNewUser"/>
				                
				            </a>
						</h2>
					</div>
					<div class="box-content">
						<table class="table table-striped table-bordered responsive">
							<thead>
								<tr>
									<th scope="row">Find:
										<input type="text" ng-model="user" />
									</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="box-content" id="gridContainer"
						ng-class="{'': state == 'list', 'none': state != 'list'}">
						<table class="table table-striped table-bordered responsive">
							<thead>
								<tr>
									<th scope="col"><spring:message code="label.user" /></th>
									<th scope="col"><spring:message code="label.asc" /></th>
									<th scope="col"><spring:message code="monitor.company" /></th>
									<th scope="col"><spring:message code="label.userws.password" /></th>
									<th scope="col"><spring:message code="label.userws.dev" /></th>
									<th scope="col"><spring:message code="label.userws.prd" /></th>
									<th scope="col"><spring:message code="label.userws.token" /></th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="vo in page.source | filter:user | orderBy:'wsUser'">
									<td class="tdContactsCentered">{{vo.wsUser}}</td>
									<td class="tdContactsCentered">{{vo.ascCode}}</td>
									<td class="tdContactsCentered">{{vo.corpCode}}</td>
									<td class="tdContactsCentered">{{vo.wsPwd}}</td>
									<td class="tdContactsCentered">{{vo.dev}}</td>
									<td class="tdContactsCentered">{{vo.prd}}</td>
									<td class="tdContactsCentered">{{vo.token}}</td>
									<td class="center">  &nbsp;
										<a class="btn btn-info" href="#updateUserModal" data-toggle="modal" ng-click="selectedContact(vo)"
											title="<spring:message code='update'/>&nbsp;<spring:message code='label.updateUser'/>"> 
											<i class="icon_plus_alt2"></i> 
										</a> 
										<a class="btn btn-danger" href="#deleteUserModal" data-toggle="modal" ng-click="selectedContact(vo)"
											title="<spring:message code='delete'/>&nbsp;<spring:message code='label.deleteUser'/>"> 
											<i class="icon_close_alt2"></i>
										</a>
									</td>
								</tr>

							</tbody>
						</table>
						<!-- 
						<div class="text-center">
							<button href="#" class="btn btn-inverse"
								ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
								ng-disabled="page.currentPage == 0" ng-click="changePage(0)"
								title='<spring:message code="pagination.first"/>'>
								<spring:message code="pagination.first" />
							</button>
							<button href="#" class="btn btn-inverse"
								ng-class="{'btn-inverse': page.currentPage != 0, 'disabled': page.currentPage == 0}"
								ng-disabled="page.currentPage == 0" class="btn btn-inverse"
								ng-click="changePage(page.currentPage - 1)"
								title='<spring:message code="pagination.back"/>'>&lt;</button>
							<span>{{page.currentPage + 1}} <spring:message
									code="pagination.of" /> {{page.pagesCount}}
							</span>
							<button href="#" class="btn btn-inverse"
								ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
								ng-click="changePage(page.currentPage + 1)"
								ng-disabled="page.pagesCount - 1 == page.currentPage"
								title='<spring:message code="pagination.next"/>'>&gt;</button>
							<button href="#" class="btn btn-inverse"
								ng-class="{'btn-inverse': page.pagesCount - 1 != page.currentPage, 'disabled': page.pagesCount - 1 == page.currentPage}"
								ng-disabled="page.pagesCount - 1 == page.currentPage"
								ng-click="changePage(page.pagesCount - 1)"
								title='<spring:message code="pagination.last"/>'>
								<spring:message code="pagination.last" />
							</button>
						</div>
						-->
					</div>
				</div>
			</div>
		</div>
		<!-- finish table of user -->
		
        <jsp:include page="userWsModal.jsp"/>
        <jsp:include page="userWsModalDelete.jsp" />
        <jsp:include page="userWsModalUpdate.jsp" />
        		
	</div>
</div>
</section>
<script src="<c:url value="/resources/js/pages/userWS.js" />"></script>