package persistent.bank.repository;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import persistent.bank.model.entity.Operation;
import persistent.bank.model.entity.User;

import static java.util.Map.entry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() 
	{
		try 
		{
			if (sessionFactory == null || sessionFactory.isClosed()) 
			{
				Configuration configuration = new Configuration();

				Properties properties = new Properties();
				properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				properties.put(Environment.URL, "jdbc:mysql://localhost:3306/persistent-bank");
				properties.put(Environment.USER, "calindra");
				properties.put(Environment.PASS, "4321");
				properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				properties.put(Environment.SHOW_SQL, "false");
				properties.put(Environment.HBM2DDL_AUTO, "update");

				
				configuration.setProperties(properties);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Operation.class);

				ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.build();
								
				sessionFactory = configuration.buildSessionFactory(standardRegistry);
			}
			return sessionFactory;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}