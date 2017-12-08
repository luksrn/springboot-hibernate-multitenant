package com.medium.luksrn.multitenant.config.schema;

import org.hibernate.MultiTenancyStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.medium.luksrn.multitenant.config.DataSourceProperties;
import com.medium.luksrn.multitenant.profiles.SchemaProfile;

/**
 * 
 * @author lucas
 *
 */
@SchemaProfile
@ConfigurationProperties(prefix="multitenancy",ignoreUnknownFields=true)
public class MultiTenantSchemaProperties {
	
	private DataSourceProperties dataSource;
	
	/**
	 * Recupera o DataSource que contém o DataSource de autenticação.
	 * 
	 * @return propriedades de autenticação.
	 * 
	 */
	public DataSourceProperties getDataSourceAuth(){
		return dataSource;
	}

	public DataSourceProperties getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSourceProperties dataSource) {
		this.dataSource = dataSource;
	}
}
