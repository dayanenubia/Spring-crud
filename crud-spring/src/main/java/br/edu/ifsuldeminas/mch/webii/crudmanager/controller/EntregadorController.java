package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.EntregadorRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.dao.RestauranteRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Entregador;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Restaurante;

@Controller
public class EntregadorController {
	
	@Autowired
	private EntregadorRepository entregadorRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/entregadores")
	public String entregadores(Model model) {
		List<Entregador> entregadores = entregadorRepository.findAll();
		model.addAttribute("entregadores", entregadores);
		return "entregadores";
	}
	
	@GetMapping("/entregadores/form")
	public String entregadorForm(@ModelAttribute("entregador") Entregador entregador, Model model) {
		List<Restaurante> restauranteList = restauranteRepository.findAll();
		model.addAttribute("restauranteList", restauranteList);
	    return "entregadores_form";
	}
	
	@PostMapping("/entregadores/new")
	public String entregadorNew(@ModelAttribute("entregador") Entregador entregador) {
		List<Restaurante> selectedRestaurante = entregador.getRestaurantes();
	    List<Restaurante> restauranteList = restauranteRepository.findAllById(selectedRestaurante.stream().map(Restaurante::getId).collect(Collectors.toList()));
	    entregador.setRestaurantes(restauranteList);
	    entregadorRepository.save(entregador);
		return "redirect:/entregadores";
	}
	
	@GetMapping("/entregadores/update/{id}")
	public String entregadorUpdate(@PathVariable("id") Integer id, Model model) {

		Optional<Entregador> optEntregador = entregadorRepository.findById(id);
		
		if (!optEntregador.isPresent()) { // Recupera no banco de dados quando clica em alterar
			// Gerar erro
		}
		
		Entregador entregador = optEntregador.get();
		
		model.addAttribute("entregador", entregador);

		return "entregadores_form";
	}
	
	
	@GetMapping("/entregadores/delete/{id}")
	public String entregadorDelete(@PathVariable("id") Integer id) {
	    Optional<Entregador> optionalEntregador = entregadorRepository.findById(id);
	    if (optionalEntregador.isPresent()) {
	        Entregador entregador = optionalEntregador.get();
	        // Remova o policial das missões existentes
	        for (Restaurante restaurante : entregador.getRestaurantes()) {
	            restaurante.getEntregadores().remove(entregador);
	        }
	        // Exclua o policial
	        entregadorRepository.delete(entregador);
	    }
	    return "redirect:/entregadores";
	}
}