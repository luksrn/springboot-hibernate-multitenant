package com.medium.luksrn.multitenant.config.schema;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.medium.luksrn.multitenant.MultitenantHolder;

/**
 * Para ser utilizado em conjunto com o JDBC Template quando o modo 
 * de Tenant for Schema based.
 * 
 * @author lucas.oliveira
 *
 */
public class MultiTenantSchemaDataSourceWrapper implements DataSource {

	private DataSource delegate;

	public MultiTenantSchemaDataSourceWrapper(DataSource delegate) {
		super();
		this.delegate = delegate;
	}

	public DataSource getDelegate() {
		return delegate;
	}

	public PrintWriter getLogWriter() throws SQLException {
		return delegate.getLogWriter();
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return delegate.unwrap(iface);
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		delegate.setLogWriter(out);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return delegate.isWrapperFor(iface);
	}

	public Connection getConnection() throws SQLException {
		Connection c = delegate.getConnection();
		c.setSchema( MultitenantHolder.getTenantId() );
		return c;
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		delegate.setLoginTimeout(seconds);
	}

	public Connection getConnection(String username, String password) throws SQLException {
		return delegate.getConnection(username, password);
	}

	public int getLoginTimeout() throws SQLException {
		return delegate.getLoginTimeout();
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return delegate.getParentLogger();
	}
	
}
