package com.medium.luksrn.multitenant.config;

/**
 * Abstração das propriedades de conexão da aplicação. Esta classe pode
 * ser preenchida com informações de quaisquer lugar, nesta caso, está sendo
 * populada por um arquivo properties com finalidade de ilustração.
 * 
 * @author lucas.oliveira
 *
 */
public class DataSourceProperties {
	private String tenantId;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private String driverClassName;
	
	private boolean bancoAutenticacao;
	
	
	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	
	public boolean isBancoAutenticacao() {
		return bancoAutenticacao;
	}
	
	public void setBancoAutenticacao(boolean bancoAutenticacao) {
		this.bancoAutenticacao = bancoAutenticacao;
	}
}
