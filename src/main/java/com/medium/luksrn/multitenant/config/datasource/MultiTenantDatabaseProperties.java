package com.medium.luksrn.multitenant.config.datasource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.medium.luksrn.multitenant.config.DataSourceProperties;
import com.medium.luksrn.multitenant.profiles.DataSourceProfile;

/**
 * <p>
 * Esta classe é responsável por manter todas as informações relacionadas aos tenants e definição
 * da estratégia de tenant ID. (Nesta consultoria implementado apenas o tenant Schema e DataSource.
 * </p>
 * 
 * <p>
 * Em uma aplicação real ela deve ser substituida por uma implementação que consulte a
 * base de dados de empresas e crie instâncias de data sources para cada banco de dados.
 * </p>
 * 
 * <p>Neste exemplo, elas são carregas diretamente de um arquivo properties:</p>
 * 
 * <pre>
	  multitenancy:
	  strategy: DATABASE
	  dataSources:
		-
		  tenantId: tenant_1
		  url: jdbc:postgresql://localhost:5432/db_dvdrental
		  username: user_dvdrental
		  password: changeit
		  driverClassName: org.postgresql.Driver
		-
		  tenantId: tenant_2
		  url: jdbc:postgresql://localhost:5532/db_dvdrental
		  username: user_dvdrental
		  password: changeit
		  driverClassName: org.postgresql.Driver
	</pre>
	<p>
	Em uma representação:
	</p>
	<pre>
	 multitenancy.strategy:SCHEMA
	 multitenancy.dataSources[0].tenantId: tenant_1
	 multitenancy.dataSources[0].url: jdbc:postgresql://localhost:5532/db_dvdrental
	 multitenancy.dataSources[0].username: user_dvdrental
	 multitenancy.dataSources[0].password: changeit
	 multitenancy.dataSources[0].driverClassName: org.postgresql.Driver
	 
	 multitenancy.dataSources[1].tenantId: tenant_1
	 multitenancy.dataSources[1].url: jdbc:postgresql://localhost:5532/db_dvdrental
	 multitenancy.dataSources[1].username: user_dvdrental
	 multitenancy.dataSources[1].password: changeit
	 multitenancy.dataSources[1].driverClassName: org.postgresql.Driver
	 
	</pre>
	
 * @author lucas.oliveira
 *
 */
@DataSourceProfile
@ConfigurationProperties(prefix="multitenancy",ignoreUnknownFields=true)
public class MultiTenantDatabaseProperties {
	
	private List<DataSourceProperties> dataSourcesProps;
	
	/**
	 * Recupera o DataSource que contém o DataSource de autenticação.
	 * 
	 * @return propriedades de autenticação.
	 * 
	 */
	public DataSourceProperties getDataSourceAuth(){
		
		List<DataSourceProperties> dsAuth = dataSourcesProps.stream()
				.filter( DataSourceProperties::isBancoAutenticacao )
				.collect(Collectors.toList());
		
		if( dsAuth.isEmpty() ){
			throw new RuntimeException("Você precisa definir um DataSource como sendo o banco de autenticação.");
		}
		
		if( dsAuth.size() > 1 ){
			throw new RuntimeException("Não é possível possuir mais de um DataSource como sendo o banco de autenticação.");
		}
		
		return dsAuth.get(0);
	}

	public List<DataSourceProperties> getDataSources() {
		return this.dataSourcesProps;
	}

	public void setDataSources(List<DataSourceProperties> dataSourcesProps) {
		this.dataSourcesProps = dataSourcesProps;
	}
}
