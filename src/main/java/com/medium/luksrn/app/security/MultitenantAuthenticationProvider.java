package com.medium.luksrn.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.medium.luksrn.app.domain.Usuario;
import com.medium.luksrn.app.repository.UsuarioRepository;
/**
 * Este é o provedor de autenticação da aplicação, aqui deve ser consultada a base de
 * usuários do sistema, identificando se as credenciais fornecidas pelo usuário 
 * (tenant ID, usuário e senha) são válidas.
 * 
 * Esta classe utiliza os details do {@link UsernamePasswordAuthenticationToken} customizado,
 * criado para manter a informação do TenantID. Veja a implementação de {@link MultitenantWebAuthenticationDetails}
 * e {@link MultitenantWebAuthenticationDetailsSource}.
 * 
 * 
 * @see DaoAuthenticationProvider
 * 
 * @author lucas
 *
 */
@Component
public class MultitenantAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		MultitenantWebAuthenticationDetails details = (MultitenantWebAuthenticationDetails)authentication.getDetails();
		String tenant = details.getTenant();
		
		Usuario usuario = usuarioRepository.findByLoginAndTenantId(username, tenant);
		if( usuario == null ){
			throw new UsernameNotFoundException("Não foi possível encontrar o usuário " + username + " na agência " + tenant);
		}
		
		return usuario;
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
		// Escolhe a implementação mais adequada para a interface PasswordEncoder

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();

		if ( !userDetails.getPassword().equals(presentedPassword) ){
			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
		
		// Com senha OK, processar as permissões específicas do usuário.
	}
}
