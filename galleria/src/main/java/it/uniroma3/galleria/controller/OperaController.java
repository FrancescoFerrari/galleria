package it.uniroma3.galleria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.service.OperaService;

@Controller
public class OperaController  {

	@Autowired
	private OperaService operaService; 

	@GetMapping("/opera")
	public String showForm(Opera opera) {
		return "formOpera";
	}

	@PostMapping("/opera")
	public String checkOperaInfo(@Valid @ModelAttribute Opera opera, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "formOpera";
		}
		else {
			model.addAttribute(opera);
			operaService.add(opera); 
		}
		return "ritornaOpera";
	}

	@GetMapping("/operaList")
     public String ListaOpere(List<Opera> opere){
		return"listaOpere";
}
}

