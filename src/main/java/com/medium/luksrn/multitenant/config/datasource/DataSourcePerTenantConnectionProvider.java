package com.medium.luksrn.multitenant.config.datasource;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.medium.luksrn.multitenant.config.DataSourceProperties;
import com.medium.luksrn.multitenant.profiles.DataSourceProfile;

/**
 * Este componente define como será realizada a resolução de Tenant ID da aplicação.
 * 
 * 
 * Para habilitar esta estratégia é necessário definir o profile "datasource".
 * 
 * @author lucas.oliveira
 *
 */
@Component
@DataSourceProfile
public class DataSourcePerTenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	@Autowired
	private MultiTenantDatabaseProperties multiTenantProperties;
	
	@Autowired
	@Qualifier("tenantDataSources")
	private Map<String, DataSource> dataSources;

	@Override
	protected DataSource selectAnyDataSource() {
		DataSourceProperties dsProps = multiTenantProperties.getDataSourceAuth();
		return this.dataSources.get( dsProps.getTenantId() );
	
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		return this.dataSources.get(tenantIdentifier);
	}
}
