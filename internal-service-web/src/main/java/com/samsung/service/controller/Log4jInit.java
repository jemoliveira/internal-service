package com.samsung.service.controller;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServlet;
import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jInit extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Log4jInit.class); 

    @Override
    public void init() {
        
    	try {
    		
    		logger.info("\n## Start Application!!!!");
    		
			DOMConfigurator.configure(getServletContext().getResource("/WEB-INF/log4j.xml"));
			logger.info("\n## Log4j was configurated correctly!!!!");
		} catch (MalformedURLException e) {
			logger.error("\n## " + e.getMessage());			
		} catch (FactoryConfigurationError e) {
			logger.error("\n## " + e.getMessage());			
		}    	
    }
    
    public static void main(String[] args) {
		Log4jInit t = new Log4jInit();
		t.init();
	}
}