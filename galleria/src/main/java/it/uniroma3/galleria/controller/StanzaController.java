package it.uniroma3.galleria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.service.StanzaService;

@Controller
public class StanzaController {
	@Autowired
	StanzaService stanzaService;


	@GetMapping("/stanza")
	public String showForm(Stanza stanza) {
		return "formStanza";	
	}


	@PostMapping("/stanza")
	public String checkArtistaInfo(@Valid @ModelAttribute Stanza stanza, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "formStanza";
		}
		else {
			model.addAttribute(stanza);
			stanzaService.add(stanza);
		}
		return "ritornaStanza";
	}
}
