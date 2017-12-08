package com.medium.luksrn.multitenant.config.schema;

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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.medium.luksrn.multitenant.DataSourceProvider;
import com.medium.luksrn.multitenant.HikariCPDataSourceProvider;
import com.medium.luksrn.multitenant.MultitenantHolder;
import com.medium.luksrn.multitenant.MultitenantHolderCurrentIdentifierResolver;
import com.medium.luksrn.multitenant.profiles.SchemaProfile;

/**
 * Esta classe é a configuração da arquitetura multitenat baseada
 * em schemas.
 * 
 * @author lucas.oliveira
 *
 */
@Configuration
@SchemaProfile
@EnableConfigurationProperties(MultiTenantSchemaProperties.class)
public class MultiTenantSchemaConfiguration {

	@Autowired
	private SchemaPerTenantConnectionProvider multiTenantConnectionProvider;

	/**
	 * @see MultitenantHolderCurrentIdentifierResolver
	 */
	@Autowired
	private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;
	
	/**
	 * Este bean é responsável por receber as propriedades de conexão de base de dados
	 * (Nesta abordagem, sendo recebidas via properties). As propriedades são passadas para o
	 * uma instância do {@link DataSourceProvider} que será responsável por instanciar o pool de 
	 * conexões de banco de dados.
	 * 
	 * @see HikariCPDataSourceProvider
	 * 
	 * @param provider - Provider configurado na aplicação.
	 * @param props - Propriedades do banco de dados.
	 * @return
	 */
	@Bean
	public DataSource dataSource(
				DataSourceProvider provider,
				MultiTenantSchemaProperties props){
		return provider.build(props.getDataSource());
	}

	/**
	 * Instancia o FactoryBean do Spring responsável por instanciar o EntityManagerFactory.
	 * Este método inicializa as propriedades de {@link Environment.MULTI_TENANT*} do Hibernate
	 * para utilizar as instâncias schema based.
	 * 
	 * @param builder
	 * 
	 * @param ds - DataSource instanciado em {@link #dataSource(DataSourceProvider, MultiTenantSchemaProperties)}
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, 
																				DataSource dataSource) {
	  Map<String, Object> hibernateProps = new LinkedHashMap<>();

	  hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
	  hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
	  hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

	  return builder.dataSource(dataSource)
			  .packages("com.medium.luksrn.app")
			  .properties(hibernateProps)
			  .build();
	 }
	
	/**
	 * Uma referência a um JDBCTemplate que pode ser injetado nos beans do Spring
	 * que irá trabalhar integrado com o mecanismo multi tenant. Ou seja, automaticamente
	 * a conexão será roteada para o schema do cliente logado.
	 * 
	 * Caso seja necessário um Bean JDBCTemplate que será conectado no banco administrativo,
	 * basta instanciar um novo JdbcTemplate fornecendo como parâmetro o {@link DataSource}
	 * que irá por padrão no schema "public", convencionado aqui como sendo o banco "shared".
	 * Para customizar esta convenção, alterar {@link MultitenantHolder};
	 * 
	 * @param ds - DataSource instanciado em {@link #dataSource(DataSourceProvider, MultiTenantSchemaProperties)}
	 * 
	 * 
	 * @return uma instância Jdbc para ser utilizada na base do produto.
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds){
		return new JdbcTemplate(new MultiTenantSchemaDataSourceWrapper(ds));
	}
	
}
