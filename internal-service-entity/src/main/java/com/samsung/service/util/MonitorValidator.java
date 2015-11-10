package com.samsung.service.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;

public class MonitorValidator {

	public static String monitorValidate(String finalDate, int right) {

		int hours = Hours.hoursBetween(calculatesDate(finalDate), 
				calculatesDate(GenericsDateUtil.convertDateToString())).getHours();
		
		if (hours > right) {			
			return "stop";
		}
		return "run";
	}
	public static String monitorValidateMinute(String finalDate, int right) {
		int minutes = Minutes.minutesBetween(calculatesDate(finalDate), calculatesDate(GenericsDateUtil.convertDateToString())).getMinutes();
		
		if (minutes > right) {			
			return "stop";
		}
		return "run";
	}
	public static DateTime calculatesDate(String dateString) {

		int year = Integer.parseInt(dateString.substring(0, 4)); 
		int month = Integer.parseInt(dateString.substring(5, 7));
		int day = Integer.parseInt(dateString.substring(8, 10));
		int hour = Integer.parseInt(dateString.substring(11, 13));
		int minute = Integer.parseInt(dateString.substring(14, 16)); 
		int second = Integer.parseInt(dateString.substring(17, 19));

		return new DateTime(year, month, day, hour, minute, second);

	}

	public static String checkWebService(String wsdlPath, String serviceName) {

		String situation = "run";

		URL url = null;

		try {
			url = new URL(wsdlPath);

			//Nome qualifcado do servi?o  
			//Primeiro argumento ? o URI do servi?o  
			//Segundo ? o nome do servi?o publicado no WSDL  
			QName qname = new QName("latin.samsungasc.com", serviceName);  
			//Cria, de fato, uma f?brica para o servi?o  
			Service.create(url, qname);

		} catch (MalformedURLException e) {  
			situation = "stop";
		} catch (WebServiceException e) {  
			situation = "stop";
		} catch (Exception e) {  
			situation = "stop";
		}    
		return situation;
	}
	
	/*private static String command = "java weblogic.Admin -url 105.1.12.156:8001 -username system -password wpwnehsa CLUSTERSTATE -clustername saws_Cluster";

	public static List<MonitorVO> checkClusterLog(List<MonitorVO> empList, String sawsApp, SkpUserService skpUserService) {

		MonitorVO log = new MonitorVO();
		String situation = "";

		UserNotifiesBean skpUser = skpUserService.findOne();

		if (skpUser.isCorrect()) {
			situation = MonitorValidator.checkCluster(situation, sawsApp, skpUser);
		} else {
			situation = "fail";
		}

		if (situation.equals("fail")) {
			log.setSuccess("Password has changed, please contact administrator");
			skpUserService.updateById(true);			
		}

		log.setName(sawsApp.substring(0, 9).toUpperCase());
		log.setlUpdate(DateUtil.convertDateToString());
		log.setSituation(situation);				
		log.setCompany("CLUSTER " + sawsApp.substring(8, 9));

		empList.add(log);

		return empList;		
	}
	
	public static String checkCluster(String situation, String sawsApp, UserNotifiesBean skpUser) {

	try {	

		if (!TelnetClientHelper.isConnect()) {
			logger.info("## Needs to connect in telnet");
			TelnetClientHelper.connect("105.1.12.156", skpUser.getUser_skp(), skpUser.getPassword_skp());				
		}
		situation = TelnetClientHelper.sendCommand(command, sawsApp);
		//TelnetClientHelper.disconnect();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return situation;
}*/
}
