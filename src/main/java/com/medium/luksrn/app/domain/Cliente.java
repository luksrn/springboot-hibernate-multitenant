package com.medium.luksrn.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Esta entidade é específica da aplicação representa um "tenant".
 * Pelo que foi conversado, existe uma base "administraiva" que contém
 * os clientes e usuários. Sendo assim, esta entidade repsenta uma empresa/agência.
 * 
 * @author lucas.oliveira
 *
 */
@Entity
public class Cliente {

	@Id
	@GeneratedValue
	private int id;
	
	private String nome;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
