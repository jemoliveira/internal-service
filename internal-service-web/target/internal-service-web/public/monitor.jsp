<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<section class="wrapper" style="padding-top: 10px;" >
	<div class="row-fluid" style="background-color: white;">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">

			<table width="100%"
				style="text-align: right; margin-left: auto; margin-right: 0px;"
				border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td style="text-align: center;"><h3><spring:message code="message.loading" /></h3></td>
				</tr>
				<tr>
					<td>
						<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0;">
							<span class="sr-only">0% Complete</span>
						  </div>
						</div>
						</td>
				</tr>
			</table>
			<br>
		</div>
	</div>
</div>

<%-- <div class="modal fade" id="loadingModal">
	<table width="100%"
		style="text-align: right; margin-left: auto; margin-right: 0px;"
		border="0" cellpadding="2" cellspacing="2">
		<tr>
			<td style="text-align: center;"><h3>Aguarde</h3></td>
		</tr>
		<tr>
			<td style="text-align: center;"><img
				src="<c:url value='/resources/img/ajax-loaders/ajax-loader-7.gif'  />"></td>
			<link rel="shortcut icon"
				href="<c:url value='/resources/img/ajax-loaders/ajax-loader-7.gif'  />" />
		</tr>
	</table>
	<br>
</div> --%>
				<div id="c-slide" class="carousel slide auto panel-body" style="margin-left: 30px">
                              <div class="carousel-inner">
                                  <div class="item text-center active">
                                     <table style="text-align: center;"	border="0"	cellpadding="5"	cellspacing="5"	ng-controller="monitorController" >
                                     	<tr class="row" >
                                     		<td ng-repeat="batLog in page.sourceSaw" style="height: 195px;">
												<div class="info-box blue-bg" style="width: 300px; height: auto" >
													<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
													    <h3 style="font-weight: bold;">{{batLog.name}}</h3>	
															<br />	
																<div ng-switch on="batLog.situation">
																	<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																	<img name="nok" width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																	<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																</div>
															<br />
															<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>			
															<br /><br />
															<h3 style="font-weight: bold">{{batLog.company}}</h3>
												</div>	
											</td>	
										</tr>
										<tr class="row" >
                                     		<td ng-repeat="batLog in page.sourceSaw2" style="height: 195px;">
												<div class="info-box blue-bg" style="width: 300px; height: auto" >
													<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
														<h3 style="font-weight: bold;">{{batLog.name}}</h3>	
															<br />	
																<div ng-switch on="batLog.situation">
																	<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																	<img name="nok" width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																	<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																</div>
															<br />
															<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>			
															<br /><br />
															<h3 style="font-weight: bold">{{batLog.company}}</h3>
												</div>	
											</td>	
										</tr>										
									</table>	
                                  </div>
                                  <div class="item text-center">
                                  	 <table style="text-align: center;"	border="0"	cellpadding="5"	cellspacing="5"	ng-controller="monitorController" >
                                     	<tr class="row">
                                     		<td ng-repeat="batLog in page.source"  style="height: 195px">
												<div class="info-box blue-bg" style="width: 300px; height: auto">
													<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
														<h3 style="font-weight: bold">{{batLog.name}}</h3>	
															<br />	
																<div ng-switch on="batLog.situation">
																	<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																	<img name="nok" width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																	<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																</div>
															<br />
															<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>			
															<br /><br />
															<h3 style="font-weight: bold">{{batLog.company}}</h3>	
												</div>
											</td>	
										</tr>	
										<tr class="row">
                                     		<td ng-repeat="batLog in page.source2"  style="height: 195px">
												<div class="info-box blue-bg" style="width: 300px; height: auto">
													<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
														<h3 style="font-weight: bold">{{batLog.name}}</h3>	
															<br />	
																<div ng-switch on="batLog.situation">
																	<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																	<img name="nok" id="nok"  width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																	<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																</div>
															<br />
															<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>			
															<br /><br />
															<h3 style="font-weight: bold">{{batLog.company}}</h3>
												</div>
											</td>	
										</tr> 
									</table>
								</div>
									<div class="item text-center">
                                  		<table style="text-align: center;" border="0" cellpadding="5" cellspacing="5" ng-controller="monitorController">
	                                  		<tr class="row">
	                                     		<td ng-repeat="batLog in page.sourceAllTrans" style="height: 195px">
													<div class="info-box blue-bg" style="width: 300px; height: auto">
														<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
															<h3 style="font-weight: bold">{{batLog.name}}</h3>		
																<br />	
																	<div ng-switch on="batLog.situation">
																		<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																		<img name="nok"  width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																		<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																	</div>
																<br />
																<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>
																<div style="font-weight: bold;  font-size:16px" >{{batLog.space}}</div>
																<div style="font-weight: bold;  font-size:16px">{{batLog.rowNum}}</div>	
																<div ng-show="batLog.space == null"><br /></div>
																<div ng-show="batLog.rowNum == null"><br /></div>		
																<h3 style="font-weight: bold">{{batLog.company}}</h3>
													</div>
												</td>	
											</tr>	
											<tr class="row">
	                                     		<td ng-repeat="batLog in page.sourceAllTrans2" style="height: 195px">
													<div class="info-box blue-bg" style="width: 300px; height: auto">
														<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
															<h3 style="font-weight: bold">{{batLog.name}}</h3>		
																<br />	
																	<div ng-switch on="batLog.situation">
																		<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																		<img name="nok"  width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																		<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																		<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																	</div>
																<br />
																<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>
																<div style="font-weight: bold;  font-size:16px" >{{batLog.space}}</div>
																<div style="font-weight: bold;  font-size:16px">{{batLog.rowNum}}</div>
																<div ng-show="batLog.space == null"><br /></div>
																<div ng-show="batLog.rowNum == null"><br /></div>			
																<h3 style="font-weight: bold">{{batLog.company}}</h3>
													</div>
												</td>	
											</tr>				
                                     </table>
                                     
                                  </div>
                                  <div class="item text-center">
                                  	<table style="text-align: center;" border="0" cellpadding="5" cellspacing="5" ng-controller="monitorController">
                                    	<tr class="row">
                                     		<td ng-repeat="batLog in page.sourceTms" style="height: 195px">
												<div class="info-box blue-bg" style="width: 300px; height: auto">
													<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
														<h3 style="font-weight: bold">{{batLog.name}}</h3>		
															<br />	
																<div ng-switch on="batLog.situation">
																	<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																	<img name="nok" width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																	<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																</div>
															<br />
															<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>
															<div style="font-weight: bold" ng-show="batLog.space != null">{{batLog.space}}</div>
															<div style="font-weight: bold" ng-show="batLog.rowNum != null">{{batLog.rowNum}}</div>
															<div ng-show="batLog.space == null"><br /></div>
															<div ng-show="batLog.rowNum == null"><br /></div>				
															<h3 style="font-weight: bold">{{batLog.company}}</h3>
												</div>
											</td>	
										</tr>
										<tr class="row">
                                     		<td ng-repeat="batLog in page.sourceTms2" style="height: 195px">
												<div class="info-box blue-bg" style="width: 300px; height: auto">
													<a data-rel="tooltip" title="{{batLog.success}} - {{batLog.message}} - {{batLog.user}}" href="#" ></a>
														<h3 style="font-weight: bold">{{batLog.name}}</h3>		
															<br />	
																<div ng-switch on="batLog.situation">
																	<img width="70px" height="70px" ng-switch-when="run" src="<c:url value='/resources/img/ok.png'  />">
																	<img name="nok" width="70px" height="70px" ng-switch-when="stop" src="<c:url value='/resources/img/nok.png'  />">		
																	<img width="70px" height="70px" ng-switch-when="fail" src="<c:url value='/resources/img/fail.png'  />">
																	<img width="70px" height="70px" ng-switch-when="warn" src="<c:url value='/resources/img/warn.png'  />">
																</div>
															<br />
															<div style="font-weight: bold;  font-size:16px" class="title" class="title">{{batLog.lUpdate }}</div>
															<div style="font-weight: bold" ng-show="batLog.space != null">{{batLog.space}}</div>
															<div style="font-weight: bold" ng-show="batLog.rowNum != null">{{batLog.rowNum}}</div>
															<div ng-show="batLog.space == null"><br /></div>
															<div ng-show="batLog.rowNum == null"><br /></div>				
															<h3 style="font-weight: bold">{{batLog.company}}</h3>
												</div>
											</td>	
										</tr>		
									</table>			
                                  </div>
                              </div>
                          </div>
                              <a data-slide="prev" href="#c-slide" class="left carousel-control">
                                  <i class="arrow_carrot-left_alt2"></i>
                              </a>
                              <a data-slide="next" href="#c-slide" class="right carousel-control">
                                  <i class="arrow_carrot-right_alt2"></i>
                              </a>

</div>
</section>
			
<script src="<c:url value="/resources/js/pages/monitor.js" />"></script>
<script src="<c:url value='/resources/js/pages/timer.js' />"></script>
<script>
	window.onload = function () {
		var stops = [25,35,55,70,85,100];
		$.each(stops, function(index, value){
		    setTimeout(function(){
		        $( ".progress-bar" ).css( "width", value + "%" ).attr( "aria-valuenow", value ); 
		    }, index * 10700);
		});
};
setInterval(function(){blink()}, 1000);
                
              
    function blink() {
        $("[name=nok]").fadeTo(100, 0.1).fadeTo(200, 1.0);
    };
</script>