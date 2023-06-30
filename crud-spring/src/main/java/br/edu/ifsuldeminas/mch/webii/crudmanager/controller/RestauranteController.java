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
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private EntregadorRepository entregadorRepository;
	
	@GetMapping("/restaurantes")
	public String restaurantes(Model model) {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		model.addAttribute("restaurantes", restaurantes);
		return "restaurantes";
	}
	
	@GetMapping("/restaurantes/form")
	public String restauranteForm(@ModelAttribute("restaurante") Restaurante restaurante, Model model) {
	    List<Entregador> entregadorList = entregadorRepository.findAll();
	    model.addAttribute("entregadorList", entregadorList);
	    return "restaurantes_form";
	}

	
	@PostMapping("/restaurantes/new")
	public String restauranteNew(@ModelAttribute("restaurante") Restaurante restaurante) {
	    List<Entregador> selectedEntregador = restaurante.getEntregadores();
	    List<Entregador> entregadorList = entregadorRepository.findAllById(selectedEntregador.stream().map(Entregador::getId).collect(Collectors.toList()));
	    restaurante.setEntregadores(entregadorList);
	    restauranteRepository.save(restaurante);
	    return "redirect:/restaurantes";
	}


	
	@GetMapping("/restaurantes/update/{id}")
	public String restauranteUpdate(@PathVariable("id") Integer id, Model model)
	{
		
		Optional<Restaurante> optRestaurante = restauranteRepository.findById(id);
		
		if (!optRestaurante.isPresent()) { // Recupera no banco de dados quando clica em alterar
			// Gerar erro
		}
		
		Restaurante restaurante = optRestaurante.get();
		
		model.addAttribute("restaurante", restaurante);
		
		return"restaurantes_form";
	}
	
	@GetMapping("/restaurantes/delete/{id}")
	public String restauranteDelete(@PathVariable("id") Integer id)
	{
		
		Optional<Restaurante> optRestaurante = restauranteRepository.findById(id);
		if(!optRestaurante.isPresent())
		{
			//Gerar erro
		}
		
		Restaurante restaurante = optRestaurante.get(); 
		
		//restaurante.setEntregadores(null);
		
		restauranteRepository.delete(restaurante);
		
		return"redirect:/restaurantes";
	}
}