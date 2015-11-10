package com.samsung.service.util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

// TODO from UCDetector: Change visibility of Class "HibernateUtil" to default - May cause compile errors!
public class HibernateUtil { // NO_UCD (use default)

	private static EntityManager em = Persistence.createEntityManagerFactory(
			"oraclePU").createEntityManager();;

	public static EntityManager getEntityManager() {
		return em;
	}

	public static void shutdown() {
		if (em != null && em.isOpen()) {
			em.flush();
			em.close();
		}
	}
}
