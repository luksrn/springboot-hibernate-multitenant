package com.medium.luksrn.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medium.luksrn.app.domain.Agencia;

/**
 * Exemplo de repositório Spring DATA JPA.
 * 
 */
@Repository
public interface AgenciaRepository extends CrudRepository<Agencia, Integer>{

}
