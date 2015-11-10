package com.samsung.service.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
 
public class GenericsUtil {
	
	   public static String getPasswordHash(String passwordBeforeHash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		   
		   MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		   byte messageDigestSenhaAdmin[] = algorithm.digest(passwordBeforeHash.getBytes("UTF-8"));
		    
		   StringBuilder hexStringSenhaAdmin = new StringBuilder();
		   for (byte b : messageDigestSenhaAdmin) {
		            hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
		   }
		   String passwordAfterHash = hexStringSenhaAdmin.toString();
		   
		   return passwordAfterHash;
	   } 
	   
	   
	   
	   public static void main(String args []) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		   List<String> lista = new ArrayList<String>(); // Digite os passwords aqui que será printado os hashs deles
		   lista.add("admin");
   
		   for (String passwordBeforeHash : lista) {
			   
			   MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			   byte messageDigestSenhaAdmin[] = algorithm.digest(passwordBeforeHash.getBytes("UTF-8"));
			    
			   StringBuilder hexStringSenhaAdmin = new StringBuilder();
			   for (byte b : messageDigestSenhaAdmin) {
			            hexStringSenhaAdmin.append(String.format("%02X", 0xFF & b));
			   }
			   String passwordAfterHash = hexStringSenhaAdmin.toString();
			   
			   System.out.println(passwordBeforeHash + "\t\t-\t\t" + passwordAfterHash);
		   }
		   
	   }
	   
}