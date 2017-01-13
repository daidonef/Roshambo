package com.daidone.roshambo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DAOAccount {
	
	private static SessionFactory factory;

	private static void setupFactory() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			;
		}
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		configuration.addResource("account.hbm.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		factory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static int addAccount(Account a) {
		if (factory == null)
			setupFactory();
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		int i = (Integer) hibernateSession.save(a);
		hibernateSession.getTransaction().commit(); 
		hibernateSession.close();
		return i;
	}
	
	public static List<Account> getAccount(String query) { 
		if (factory == null)
			setupFactory();
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		List<Account> accounts = hibernateSession.createQuery(query).list();
		hibernateSession.getTransaction().commit();
		hibernateSession.close();
		return accounts;
	}
	
	public static void updateAccount(Account a) {
		if (factory == null)
			setupFactory();	
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		hibernateSession.merge(a);
		hibernateSession.getTransaction().commit();
		hibernateSession.close();
	}
	
	public static Account deleteAccount(int i) {
		if (factory == null)
			setupFactory();	
		Session hibernateSession = factory.openSession();
		hibernateSession.getTransaction().begin();
		Account account = hibernateSession.get(Account.class, i);
		hibernateSession.delete(account);
		hibernateSession.getTransaction().commit();
		hibernateSession.clear();
		return account;
	}

}
