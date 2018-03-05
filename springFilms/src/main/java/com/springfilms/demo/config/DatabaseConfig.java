package com.springfilms.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuracion de la capa de persistencia de la app
 * @author acosanchez
 *
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	/*
	 * Definición del Datasource para la conexión a nuestra base de datos. Las
	 * propiedas son establecidas desde el fichero application.properties y
	 * asignadas usando el objeto env.
	 * 
	 */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource myDataSource = new DriverManagerDataSource();
		myDataSource.setDriverClassName(env.getProperty("db.driver"));
		myDataSource.setUrl(env.getProperty("db.url"));
		myDataSource.setUsername(env.getProperty("db.username"));
		myDataSource.setPassword(env.getProperty("db.password"));
		return myDataSource;
	}

	/*
	 * Creamos e inicializamos un EntityManagerFactory de JPA
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean myEntityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		myEntityManagerFactory.setDataSource(dataSource);

		// Indicamos la ruta donde tiene que buscar las clases con anotaciones
		myEntityManagerFactory.setPackagesToScan(env.getProperty("entityManager.packagesToScan"));

		// Unimos JPA con Hibernate
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		myEntityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		// Propiedades de Hibernate
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		myEntityManagerFactory.setJpaProperties(additionalProperties);

		return myEntityManagerFactory;
	}

	/*
	 * Crear e inicializar un gestor de transacciones
	 */
	@Bean
	public JpaTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

		return transactionManager;

	}

	/*
	 * Creamos un bean que va a ser un postprocessor que ayuda a relanzar
	 * excepciones específicas de cada plataforma en aquellas clases que vamos a
	 * anotar con @Repository
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
}
