<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

 <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
            <a href="#" class="logo">Internal <span class="lite">Service System</span></a>
            <!--logo end-->

             <div class="top-nav notification-row">                
                <ul class="nav pull-right top-menu">
                    <li id="alert_notificatoin_bar" class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class=icon-form-l></i>
                        </a>
                        <ul class="dropdown-menu extended notification">
                            <div class="notify-arrow notify-arrow-blue"></div>
                            <li>
                                <p class="blue">Samsung SDS</p>
                            </li>
                            <li>
                                <a href="<c:url value='/protected/userWs' />" title='<spring:message code="label.userWS"/>'>
			                    	 <span class="label label-primary"><i class="icon_pin"></i></span> 
										<spring:message code="label.userWS" />								
								</a>
                            </li>
                            <li>
                            	<a href="<c:url value='/monitor' />" title='<spring:message code="header.monitor"/>'>
									<span class="label label-success"><i class="icon_book_alt"></i></span> 
										<spring:message code="header.monitor" />
								</a>
                            </li>
                        </ul>
                    </li>
                     <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                           <span class="username"><spring:message code="label.user" /> - ${user.name}</span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li class="eborder-top">
                                <a href="<c:url value='/logout' />" title='<spring:message code="header.logout"/>'>
                                	<i class="icon_key_alt"></i>
									<spring:message code="header.logout" />									
								</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
</header>  