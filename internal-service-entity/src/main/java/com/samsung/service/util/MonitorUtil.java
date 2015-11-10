package com.samsung.service.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MonitorUtil {

	public static List<String> getCompanys(List<String> list) {

		list.add("C390");
		list.add("C810");
		list.add("C811");
		list.add("C813");
		list.add("C820");
		list.add("C830");
		list.add("C850");
		list.add("C860");

		return list;		
	}
	
	public static List<String> getSchedules() {
		
		List<String> list = new ArrayList<String>();

		list.add("1:10");
		list.add("1:11");
		list.add("1:12");
		list.add("1:13");
		list.add("1:14");
		list.add("1:15");
		
		list.add("5:10");
		list.add("5:11");
		list.add("5:12");
		list.add("5:13");
		list.add("5:14");
		list.add("5:15");
		
		list.add("9:10");
		list.add("9:11");
		list.add("9:12");
		list.add("9:13");
		list.add("9:14");
		list.add("9:15");
		
		list.add("13:10");
		list.add("13:11");
		list.add("13:12");
		list.add("13:13");
		list.add("13:14");
		list.add("13:15");
		
		list.add("17:10");
		list.add("17:11");
		list.add("17:12");
		list.add("17:13");
		list.add("17:14");
		list.add("17:15");
		
		list.add("21:10");
		list.add("21:11");
		list.add("21:12");
		list.add("21:13");
		list.add("21:14");
		list.add("21:15");
		
		return list;		
	}

	private static EntityManager getEntityManager() {
		EntityManagerFactory factory = null;
		EntityManager entityManager = null;
		try {
			//Obt?m o factory a partir da unidade de persist?ncia.
			factory = Persistence.createEntityManagerFactory("uaiContactsPU");
			//Cria um entity manager.
			entityManager = factory.createEntityManager();
			//Fecha o factory para liberar os recursos utilizado.
		} finally {
			//factory.close();
		}
		return entityManager;
	}

}
