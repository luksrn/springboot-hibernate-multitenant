package com.medium.luksrn.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medium.luksrn.app.domain.Usuario;
import com.medium.luksrn.app.security.MultitenantAuthenticationProvider;

/**
 * Exemplo de repositório Spring DATA JPA.
 * 
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	/**
	 * Método utilizado no login.
	 * 
	 * @see MultitenantAuthenticationProvider
	 * 
	 * @param username
	 * @param tenantId
	 * @return
	 */
	@Query("select u from Usuario u join u.agencia a where u.login = ?1 and a.tenantId = ?2")
	Usuario findByLoginAndTenantId(String username, String tenantId);
}
