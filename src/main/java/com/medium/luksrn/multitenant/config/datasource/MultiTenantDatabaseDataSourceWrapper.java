package com.medium.luksrn.multitenant.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.medium.luksrn.multitenant.MultitenantHolder;

/**
 * Quando utilizada a abordagem database, esta classe implementa um mecanismo
 * de roteamento de banco de dados de acordo com o tenant.
 * 
 * Ver algoritmo em {@link AbstractRoutingDataSource}
 * 
 * @author lucas.oliveira
 *
 */
public class MultiTenantDatabaseDataSourceWrapper extends AbstractRoutingDataSource {

	public MultiTenantDatabaseDataSourceWrapper(Map<String,DataSource> targetDataSources){
		Map<Object,Object> map = new HashMap<>(targetDataSources.size());
		map.putAll(targetDataSources);
		setTargetDataSources(map);
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		return MultitenantHolder.getTenantId();
	}

}
