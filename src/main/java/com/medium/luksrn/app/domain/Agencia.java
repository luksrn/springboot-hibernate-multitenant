package com.medium.luksrn.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Esta entidade representa o "tenant", deve ser uma entidade
 * presente no banco "administrativo", comum para todos os sistemas
 * 
 * @author lucas.oliveira
 *
 */
@Entity
@Table(schema="public",name="agencia")
public class Agencia {
	
	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	
	@Column(name="tenant_id")
	private String tenantId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
}
