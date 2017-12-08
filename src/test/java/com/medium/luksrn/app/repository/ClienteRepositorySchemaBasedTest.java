package com.medium.luksrn.app.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.medium.luksrn.app.domain.Cliente;
import com.medium.luksrn.multitenant.MultitenantHolder;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.profiles.active:tenancy-schema")
public class ClienteRepositorySchemaBasedTest {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Test
	public void verificaOsDadosDaAgenciaA(){
		
		MultitenantHolder.setTenantId("agencia_a");
		
		Iterable<Cliente> clientes = clienteRepository.findAll();
		
		clientes.forEach(c -> System.out.println("Cliente = " + c.getNome() ) );
	}
	
	@Test
	public void verificaOsDadosDaAgenciaB(){
		
		MultitenantHolder.setTenantId("agencia_b");
		
		Iterable<Cliente> clientes = clienteRepository.findAll();
		
		clientes.forEach(c -> System.out.println("Cliente = " + c.getNome() ) );
	}
	
	@Test
	public void verificaOsDadosDoBancoAdministrativo(){
		usuarioRepository.findAll().forEach( u -> System.out.println(u.getLogin()));
	}
}
