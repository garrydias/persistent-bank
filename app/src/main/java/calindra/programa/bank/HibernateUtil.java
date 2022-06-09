package calindra.programa.bank;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import static java.util.Map.entry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() 
	{
		try 
		{
			if (sessionFactory == null) 
			{
				Configuration configuration = new Configuration();

				Properties properties = new Properties();
				properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
				properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/ipretty");
				properties.put("hibernate.connection.username", "ipretty");
				properties.put("hibernate.connection.password", "4321");
				properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
				properties.put("show_sql", "true");
				properties.put("hbm2ddl.auto", "create-drop");

				configuration.setProperties(properties);

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