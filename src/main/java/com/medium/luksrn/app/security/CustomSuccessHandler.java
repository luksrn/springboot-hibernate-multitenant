package com.medium.luksrn.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Este manipulador irá redirecionar o usuário para uma tela após o login,
 * ou seja, ele poderá analisar as permissões de um usuário para decidir se encaminha
 * ele para uma tela de "gestor" ou "usuário comum".
 * 
 * Neste exemplo, está encaminhando simplesmente para tela de listagem de clientes.
 * 
 * @author lucas.oliveira
 *
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		response.sendRedirect("/app/cliente/listar");
	}

}
