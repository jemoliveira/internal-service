package com.samsung.service.helper;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

public class Email {
	
	private static Logger logger = Logger.getLogger(Email.class);  
	
	public static void sendEmail(String email, String username, String message) {
		
		SimpleEmail simpleEmail = new SimpleEmail();
		
		simpleEmail.setHostName("smtp.w2.samsung.com"); // o servidor SMTP para envio do e-mail
		try {
			simpleEmail.addTo(email, username);
			simpleEmail.setFrom(email, "Service Monitor"); // remetente			
			simpleEmail.setSubject("## ERROR - Service Moonitor ##"); // assunto do e-mail
			simpleEmail.setMsg(message); //conteudo do e-mail
			simpleEmail.send(); //envia o e-mail
			
		} catch (EmailException e) {
			logger.error("## " + e.toString());
		} 		
	}
}
