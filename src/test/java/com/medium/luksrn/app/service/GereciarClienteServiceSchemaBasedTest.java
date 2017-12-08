package com.medium.luksrn.app.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.medium.luksrn.app.domain.Cliente;
import com.medium.luksrn.app.repository.ClienteRepository;
import com.medium.luksrn.multitenant.MultitenantHolder;

@RunWith(SpringRunner.class)
@SpringBootTest(properties="spring.profiles.active:tenancy-schema")
public class GereciarClienteServiceSchemaBasedTest {

	@Autowired
	private GerenciarCliente service;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	public void testCadastroComSucesso(){

		MultitenantHolder.setTenantId("agencia_a");
		
		Cliente c = new Cliente();
		c.setNome("Lucas Farias de Oliveira");
		
		service.salvar(c);
		
	
		Cliente cdb = clienteRepository.findByNome("Lucas Farias de Oliveira");
		
		Assert.assertNotNull(cdb);
	}
	
	@Test
	public void testCadastroComRollback(){

		MultitenantHolder.setTenantId("agencia_a");
		
		Cliente c = new Cliente();
		c.setNome("Usuário com erro");
		
		try {
			service.salvarThrows(c);
		} catch (Exception e){}
		
	
		Cliente cdb = clienteRepository.findByNome("Usuário com erro");
		
		Assert.assertNull(cdb);
	}
}
