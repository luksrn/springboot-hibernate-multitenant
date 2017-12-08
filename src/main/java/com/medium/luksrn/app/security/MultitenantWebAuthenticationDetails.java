package com.medium.luksrn.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Customização do User Details para conter as informações de Tenant ID de um
 * usuário no sistema. A classe, basicamente, estrai informações do parâmetro de request
 * chamado "tenant". 
 * 
 * 
 * @see MultitenantAuthenticationProvider
 * @see MultitenantWebAuthenticationDetailsSource
 * 
 * @author lucas.oliveira
 *
 */
public class MultitenantWebAuthenticationDetails extends WebAuthenticationDetails {
	
	private String tenant;
	
	public MultitenantWebAuthenticationDetails(HttpServletRequest context) {
		super(context);
		tenant = (String)context.getParameter("tenant");
	}
	
	public String getTenant() {
		return tenant;
	}
}
