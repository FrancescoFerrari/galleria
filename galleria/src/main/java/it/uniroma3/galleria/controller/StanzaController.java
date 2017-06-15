package it.uniroma3.galleria.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.galleria.comparator.ComparatorePerAnno;
import it.uniroma3.galleria.model.Opera;
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

	@GetMapping("/stanzaList")
	public String showList(Model model){
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		return "listaStanze";
	}

	@GetMapping("/mostraStanza")
	public String showStanza(@RequestParam("id")long id, Model model){
		Stanza stanza = stanzaService.findbyId(id);
		List<Opera> opere= stanza.getOpere();
		model.addAttribute("opere", opere);
		model.addAttribute("stanza", stanza);
		return "opereInStanza";
	}
	
	@GetMapping("/visualizzaPerAnnoStanza")
	public String showPerAnno(@RequestParam("id")long id, Model model){
		Stanza stanza = stanzaService.findbyId(id);
		List<Opera> opere= stanza.getOpere();
		model.addAttribute("opere", opere);
		model.addAttribute("stanza", stanza);
		ComparatorePerAnno comparatore = new ComparatorePerAnno();
		Collections.sort(opere,comparatore);
		return "opereInStanza";
	}
	
	@GetMapping("/visualizzaPerTitoloStanza")
	public String showPerTitolo(@RequestParam("id")long id, Model model){
		Stanza stanza = stanzaService.findbyId(id);
		List<Opera> opere=stanza.getOpere();
		Collections.sort(opere);
		model.addAttribute("opere", opere);
		model.addAttribute("stanza", stanza);
		return "opereInStanza";
	}
}