package com.medium.luksrn.multitenant.config.schema;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medium.luksrn.multitenant.MultitenantHolder;
import com.medium.luksrn.multitenant.profiles.SchemaProfile;

/**
 * Este componente é responsável por recuperar uma conexão do DataSource utilizando
 * a estratégia de tenant ID baseada em Schemas. O schema default é o "public"
 * que define aonde estão todos os schemas.
 * 
 * Para habilitar esta estratégia é necessário definir o profile "schema"
 *  
 * @author lucas.oliveira
 *
 */
@Component
@SchemaProfile
public class SchemaPerTenantConnectionProvider implements MultiTenantConnectionProvider {
	
	@Autowired
	private DataSource dataSource;
	
    @Override
    public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        final Connection connection = this.getAnyConnection();
        try {
            connection.createStatement().execute("SET search_path to " + tenantIdentifier);
        } catch (SQLException e) {
            throw new HibernateException("Não foi possível alterar a conexão JDBC para o schema [" + tenantIdentifier + "]",
                    e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        try {
            connection.createStatement().execute("SET search_path to " + MultitenantHolder.DEFAULT_TENANT);
        } catch (SQLException e) {
            throw new HibernateException("Não foi possível alterar a conexão JDBC para o schema [" + tenantIdentifier + "]",
                    e);
        }

        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        return null;
    }

}
