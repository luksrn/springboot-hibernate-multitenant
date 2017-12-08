package com.medium.luksrn.app.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.medium.luksrn.WebSecurityConfig;

/**
 * Esta classe é responsável por construir o UserDetails do usuário durante o processamento
 * do {@link UsernamePasswordAuthenticationFilter}. A customização deste details é realizada
 * na configuração do {@link WebSecurityConfig}
 * 
 * 
 * @author lucas.oliveira
 *
 */
public class MultitenantWebAuthenticationDetailsSource 
	implements AuthenticationDetailsSource<HttpServletRequest, MultitenantWebAuthenticationDetails> {

	@Override
	public MultitenantWebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new MultitenantWebAuthenticationDetails(context);
	}

}
