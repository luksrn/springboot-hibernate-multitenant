package com.medium.luksrn.app.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Esta entidade representa o "usuário", deve ser uma entidade
 * presente no banco "administrativo", comum para todos os sistemas.
 * 
 * A implementação da interface {@link UserDetails} é especifica do 
 * Spring Security, e permite a integração com o framework trazendo
 * ao dominio alguns aspectos interessantes, por exemplo, o usuário
 * está com a conta bloqueada? (isAccountNonLocked)
 * 
 * O banco do produto deve prover estas informações, ou deve ser
 * retornados valores neutros para não bloquear o processo de autenticação
 * já que o Spring Security utiliza estes atributos para processar 
 * regras de autenticação "automáticas", presentes na classe 
 * {@link AbstractUserDetailsAuthenticationProvider}.
 * 
 * 
 * @author lucas.oliveira
 *
 */
@Entity
@Table(schema="public",name="usuario")
public class Usuario implements UserDetails {

	@Id
	private Long id;
	
	private String login;
	
	private String senha;
	
	@ManyToOne
	@JoinColumn(name="id_agencia")
	private Agencia agencia;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Agencia getAgencia() {
		return agencia;
	}
	
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USUARIO"));
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
