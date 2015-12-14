package com.dirtroadsoftware.rds4a.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(value = { "com.dirtroadsoftware.rds4a.core.repositories" })
@EnableTransactionManagement
public class ApplicationConfig {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);
	
	private static final String JPA_ENTITIES_PACKAGE = "com.dirtroadsoftware.rds4a.core.models.entities";
	
	@Autowired
    private DatabaseSettings databaseSettings;
	
	@Bean
	public Object getA(){
		LOG.info("#######################");
		LOG.info(databaseSettings.getDriverClassName());
		LOG.info(databaseSettings.getJdbcUrl());
		LOG.info(databaseSettings.getLogin());
		LOG.info(databaseSettings.getPassword());
		return new Object();
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("org.h2.Driver");
		config.setJdbcUrl("jdbc:h2:file:~/local;DB_CLOSE_DELAY=-1");
		config.setUsername("sa");
		config.setPassword("");
		config.setMaximumPoolSize(100);
		config.addDataSourceProperty("cachePrepStmts", true);
		config.addDataSourceProperty("prepStmtCacheSize", 250);
		config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		config.addDataSourceProperty("useServerPrepStmts", true);
		return new HikariDataSource(config);
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		vendorAdapter.getJpaPropertyMap().put("hibernate.connection.provider_class",
				"com.zaxxer.hikari.hibernate.HikariConnectionProvider");

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(JPA_ENTITIES_PACKAGE);
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
	
//	@Bean(destroyMethod = "close")
//	public HikariDataSource dataSource() {
//	    HikariDataSource ds = new HikariDataSource();
//	    ds.setMaximumPoolSize(100);
//	    ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
//	    ds.addDataSourceProperty("url", "jdbc:mysql://127.0.0.1:3306/mydb?useUnicode=true&characterEncoding=UTF-8&transformedBitIsBoolean=true");
//	    ds.addDataSourceProperty("user", "usr");
//	    ds.addDataSourceProperty("password", "pwd");
//	    ds.addDataSourceProperty("cachePrepStmts", true);
//	    ds.addDataSourceProperty("prepStmtCacheSize", 250);
//	    ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//	    ds.addDataSourceProperty("useServerPrepStmts", true);
//	    return ds;
//	}
}
