package com.medium.luksrn.multitenant.config.datasource;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.medium.luksrn.multitenant.DataSourceProvider;
import com.medium.luksrn.multitenant.config.DataSourceProperties;
import com.medium.luksrn.multitenant.profiles.DataSourceProfile;

/**
 * Configurações especificas do profile database.
 * 
 * @author lucas
 *
 */
@Configuration
@DataSourceProfile
@EnableConfigurationProperties(MultiTenantDatabaseProperties.class)
public class MultiTenantDatabaseConfiguration {
	
	@Autowired
	private DataSourcePerTenantConnectionProvider multiTenantConnectionProvider;

	@Autowired
	private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;
	
	@Bean
	public Map<String,DataSource> tenantDataSources(DataSourceProvider provider,MultiTenantDatabaseProperties props){
		Map<String,DataSource> dataSources = new HashMap<>();
		for( DataSourceProperties p : props.getDataSources() ){
			dataSources.put( p.getTenantId() , provider.build(p));
		}
		return dataSources;
	}

	@Bean
	public DataSource routingDataSource(Map<String,DataSource> targetDataSources){
		return new MultiTenantDatabaseDataSourceWrapper(targetDataSources);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, 
																				DataSource dataSource) {
	  Map<String, Object> hibernateProps = new LinkedHashMap<>();

	  hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE );
	  hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
	  hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

	  return builder.dataSource(dataSource)
			  .packages("com.medium.luksrn.app")
			  .properties(hibernateProps)
			  .build();
	 }
	
	@Bean
	public JdbcTemplate jdbcTemplate(AbstractRoutingDataSource routingDataSource){
		return new JdbcTemplate(routingDataSource);
	}
	
}
