package com.medium.luksrn.multitenant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.medium.luksrn.multitenant.config.datasource.MultiTenantDatabaseConfiguration;
import com.medium.luksrn.multitenant.config.datasource.MultiTenantDatabaseProperties;

/**
 * Exemplo de configuração:
 * 
 * @author lucas.oliveira
 *
 */
public class MultiTenantPropertiesTest {
	
	private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

	@Before
	public void init(){
		EnvironmentTestUtils.addEnvironment(ctx, 
				"multitenancy.dataSources[0].bancoAutenticacao: true",
				"multitenancy.dataSources[0].tenantId: tenant_shared",
				"multitenancy.dataSources[0].url: jdbc:postgresql://localhost:5532/db_comum_auth",
				"multitenancy.dataSources[0].username: user_authentication",
				"multitenancy.dataSources[0].password: changeit",
				"multitenancy.dataSources[0].driverClassName: org.postgresql.Driver",
				"multitenancy.dataSources[1].tenantId: tenant_1",
				"multitenancy.dataSources[1].url: jdbc:postgresql://localhost:5532/db_dvdrental",
				"multitenancy.dataSources[1].username: user_dvdrental",
				"multitenancy.dataSources[1].password: changeit",
				"multitenancy.dataSources[1].driverClassName: org.postgresql.Driver",
				"multitenancy.dataSources[2].tenantId: tenant_2",
				"multitenancy.dataSources[2].url: jdbc:postgresql://localhost:5632/db_dvdrental",
				"multitenancy.dataSources[2].username: user_dvdrental",
				"multitenancy.dataSources[2].password: changeit",
				"multitenancy.dataSources[2].driverClassName: org.postgresql.Driver");
	}
	
	@Test
	public void testSeBindsSaoRealizadosComSucesso() {

		ctx.register(MultiTenantDatabaseConfiguration.class);
		ctx.refresh();
		
		MultiTenantDatabaseProperties properties = ctx.getBean(MultiTenantDatabaseProperties.class);
		
		Assert.assertTrue( properties.getDataSources().size() == 3);
		Assert.assertEquals( "tenant_shared", properties.getDataSources().get(0).getTenantId() );
		Assert.assertTrue( properties.getDataSources().get(0).isBancoAutenticacao() );
		
		Assert.assertEquals( "tenant_1", properties.getDataSources().get(1).getTenantId() );
		Assert.assertEquals( "tenant_2", properties.getDataSources().get(2).getTenantId() );
	}

}
