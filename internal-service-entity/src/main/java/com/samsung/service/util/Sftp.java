package com.samsung.service.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Sftp {


	public static void main(String[] args) {
		send();
	}

	public static String send() {
		String SFTPHOST = "105.103.1.235";
		int SFTPPORT = 22;
		String SFTPUSER = "jem.oliveira";
		String SFTPPASS = "Samsung356";

		Session session = null;
		
		System.out.println("preparing the host information for sftp.");
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("Host connected.");
			
		} catch (Exception ex) {
			return "stop";
		} finally {
			session.disconnect();
			System.out.println("Host Session disconnected.");
		}
		return "run";
	}   
}