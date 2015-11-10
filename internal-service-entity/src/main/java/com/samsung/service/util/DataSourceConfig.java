package com.samsung.service.util;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class DataSourceConfig {

	private static Logger logger = Logger.getLogger(DataSourceConfig.class);

	private static final String driverClassName = "oracle.jdbc.OracleDriver";	
	private static final String url = "jdbc:oracle:thin:@105.1.11.181:1521:GSPNB";	
	private static final String dbUsername = "sagspn";	
	private static final String dbPassword = "tlwkd12#";
	

	@Bean
	public static DataSource dataSource() {
		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = (DataSource) jndi.lookup("jdbc/gspn");
		} catch (NamingException e) {
			logger.error("NamingException for java:comp/env/jdbc/gspn", e);
		}
		return dataSource;
	}
	
	/*public static DataSource dataSourceTest() {		
		return getDataSource();
	}*/

	public static DriverManagerDataSource getDataSource() { // NO_UCD (unused code)

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
}