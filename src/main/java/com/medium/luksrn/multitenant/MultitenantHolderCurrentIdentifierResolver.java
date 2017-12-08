package com.medium.luksrn.multitenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

/**
 * Essa classe é responsável por identificar o que a aplicação considera ser o tenant ID
 * atual. Essa classe é passada para as configurações do Hibernate e utilizada pelo SessionFactory
 * para resolução do tenant ID de forma automática.
 * 
 * @author lucas.oliveira
 *
 */
@Component
public class MultitenantHolderCurrentIdentifierResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		return MultitenantHolder.getTenantId();
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}
