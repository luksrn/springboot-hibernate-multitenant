package com.medium.luksrn.multitenant.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.medium.luksrn.app.domain.Usuario;
import com.medium.luksrn.multitenant.MultitenantHolder;

/**
 * Filtro responsável por setar o tenant do usuário logado no sistema
 * ( ou um tenant default quando não autenticado). O Tenant default deve
 * refletir o banco administrativo/comum. 
 * 
 * @author lucas.oliveira
 *
 */
public class MultitenantHolderFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if( auth == null || auth instanceof AnonymousAuthenticationToken ){
				MultitenantHolder.setTenantId(MultitenantHolder.DEFAULT_TENANT);
			} else {
				Usuario usuario = (Usuario) auth.getPrincipal();
				MultitenantHolder.setTenantId( usuario.getAgencia().getTenantId() );
			}
			
			chain.doFilter(request, response);
			
		} finally {
			MultitenantHolder.clear();
		}
	}

	@Override
	public void destroy() {
	}

}
