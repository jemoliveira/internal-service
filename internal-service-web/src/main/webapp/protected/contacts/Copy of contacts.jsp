<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row-fluid" ng-controller="contactsController">

	<div class="row">
		<div class="box col-md-12">
			<div class="box-inner">
				<div class="box-header well" data-original-title="">
					<h2>
						<i class="glyphicon glyphicon-user"></i> Responsive, Swipable
						Table
					</h2>

					<div class="box-icon">
						<a href="#" class="btn btn-minimize btn-round btn-default"><i
							class="glyphicon glyphicon-chevron-up"></i></a> <a href="#"
							class="btn btn-close btn-round btn-default"><i
							class="glyphicon glyphicon-remove"></i></a>
					</div>
				</div>
				<div class="box-content">
					<table class="table table-striped table-bordered responsive">
						<thead>
							<tr>
								<th>Username</th>
								<th>Date registered</th>
								<th>Role</th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="contact in page.source">
								<td class="center">{{contact.id}}</td>
								<td>{{contact.wsUser}}</td>
								<td class="center">{{contact.ascCode}}</td>
								<td class="center">{{contact.corpCode}}</td>
								<td class="center"><a class="btn btn-success" href="#">
										<i class="glyphicon glyphicon-zoom-in icon-white"></i> View
								</a> <a class="btn btn-info" href="#"> <i
										class="glyphicon glyphicon-edit icon-white"></i> Edit
								</a> <a class="btn btn-danger" href="#"> <i
										class="glyphicon glyphicon-trash icon-white"></i> Delete
								</a></td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>
		</div>
		<!--/span-->

	</div>
	<!--/row-->

	<!-- content ends -->
</div>

<script src="<c:url value="/resources/js/pages/contacts.js" />"></script>