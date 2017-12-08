package com.medium.luksrn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.medium.luksrn.multitenant.config.datasource.MultiTenantDatabaseConfiguration;
import com.medium.luksrn.multitenant.config.schema.MultiTenantSchemaConfiguration;
/**
 * Por tratarmos de situações diferenciadas, devemos excluir a auto configuração
 * do componente DataSource do Spring Boot. Vamos prover nossos DataSources
 * baseado no Tenant, quando schema based será utilizado o {@link MultiTenantSchemaConfiguration}
 * já quando database, será utilizado {@link MultiTenantDatabaseConfiguration}.
 *
 */
@SpringBootApplication(exclude={
		DataSourceAutoConfiguration.class
})
@EnableSpringDataWebSupport
public class MultitenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultitenantApplication.class, args);
	}
}
