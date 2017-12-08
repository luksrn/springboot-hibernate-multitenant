package com.medium.luksrn.app.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.medium.luksrn.app.domain.Cliente;
import com.medium.luksrn.app.repository.ClienteRepository;

/**
 * Exemplo de uma classe de serviço.
 * 
 * @author lucas.oliveira
 *
 */
@Service
public class GerenciarCliente {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void salvar(Cliente c){
		clienteRepository.save(c);
	}
	
	
	public void salvarThrows(Cliente c){
		clienteRepository.save(c);
		
		if(true){
			throw new RuntimeException("rollback");
		}
	}
	
	public Iterable<Cliente> listarClientesHibernate(){
		return clienteRepository.findAll();
	}
	
	/**
	 * Exemplo de consulta JDBC.
	 * @return
	 */
	public Iterable<Cliente> listarClientesJdbc(){
		
	 
		return jdbcTemplate.query("select id, nome from cliente", new RowMapper<Cliente>() {
			
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cliente c = new Cliente();
				c.setId( rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				return c;
			}
		});
	}

	public void remover(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
}
