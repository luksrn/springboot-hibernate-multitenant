package com.medium.luksrn.multitenant;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.medium.luksrn.multitenant.config.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Implementação da conexão DataSource baseada no {@link HikariDataSource}.
 * 
 * É recomendado analisar as configurações desejadas do pool na documentação
 * do HikariCP, e aplicalas baseadas nas configurações de properties, aplicando
 * de forma igual para cada datasource criado.
 * 
 * Ex:
 * No arquivo application.properties
 * 
 * spring.datasource.hikari.*=  contém propriedades específicas.
 * 
 * @sse https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
 * 
 * @author lucas
 *
 */
@Component
public class HikariCPDataSourceProvider implements DataSourceProvider {

	@Override
	public DataSource build(DataSourceProperties props) {
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(props.getUrl());
		ds.setUsername(props.getUsername());
		ds.setPassword(props.getPassword());
		ds.setDriverClassName(props.getDriverClassName());
		// Outras propriedades podem ser setadas (Veja javadoc).
		return ds;
	}
}
