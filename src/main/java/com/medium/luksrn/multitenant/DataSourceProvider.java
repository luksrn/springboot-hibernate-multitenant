package com.medium.luksrn.multitenant;

import javax.sql.DataSource;

import com.medium.luksrn.multitenant.config.DataSourceProperties;

/**
 * Esta interface é uma abstração para criação do DataSource pela aplicação.
 *  
 * @author lucas.oliveira
 *
 */
public interface DataSourceProvider {

	/**
	 * Recebe as propriedades de banco de dados e instancia o DataSource.
	 * 
	 * @param props
	 * @return
	 */
	DataSource build(DataSourceProperties props);
}
