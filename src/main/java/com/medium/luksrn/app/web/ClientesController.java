package com.medium.luksrn.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medium.luksrn.app.domain.Cliente;
import com.medium.luksrn.app.service.GerenciarCliente;

/**
 * Classe controladora da aplicação (Exemplifica o CRUD)
 * 
 * @author lucas
 *
 */
@Controller
@RequestMapping("/app/cliente")
public class ClientesController {

	@Autowired
	private GerenciarCliente service;
	
	@GetMapping("/listar")
	public String listar(Model model){
		model.addAttribute("clientes", service.listarClientesHibernate() );
		model.addAttribute("clientesJdbc", service.listarClientesJdbc() );
		return "app/cliente/listar";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model){
		model.addAttribute("cliente", new Cliente());
		return "app/cliente/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute Cliente cliente){
		service.salvar(cliente);
		return "redirect:listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Cliente cliente, Model model){
		model.addAttribute("cliente", cliente);
		return "app/cliente/editar";
	}
	
	@PostMapping("/editar")
	public String editar(@ModelAttribute Cliente cliente){
		service.salvar(cliente);
		return "redirect:listar";
	}
	
	@PostMapping("/remover/{id}")
	public String remover(@PathVariable("id") Cliente cliente){
		service.remover(cliente);
		return "redirect:/app/cliente/listar";
	}
}
