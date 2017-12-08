package com.medium.luksrn.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medium.luksrn.app.domain.Cliente;

/**
 * Exemplo de repositório Spring DATA JPA.
 * 
 */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Cliente findByNome(String string);

}
