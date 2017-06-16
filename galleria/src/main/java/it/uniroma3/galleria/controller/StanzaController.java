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
import it.uniroma3.galleria.service.OperaService;
import it.uniroma3.galleria.service.StanzaService;

@Controller
public class StanzaController {
	@Autowired
	StanzaService stanzaService;

	@Autowired
	OperaService operaService;


	@GetMapping("/stanza")
	public String showForm(Stanza stanza) {
		return "/Stanza/formStanza";	
	}


	@PostMapping("/stanza")
	public String checkStanzaInfo(@Valid @ModelAttribute Stanza stanza, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/Stanza/formStanza";
		}
		else {
			model.addAttribute(stanza);
			stanzaService.add(stanza);
		}
		return "/Stanza/ritornaStanza";
	}

	@GetMapping("/stanzaList")
	public String showList(Model model){
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		return "/Stanza/listaStanze";
	}

	@GetMapping("/mostraStanza")
	public String showStanza(@RequestParam("id")long id, Model model){
		Stanza stanza = stanzaService.findbyId(id);
		List<Opera> opere= stanza.getOpere();
		model.addAttribute("opere", opere);
		model.addAttribute("stanza", stanza);
		return "/Opera/opereInStanza";
	}

	@GetMapping("/visualizzaPerAnnoStanza")
	public String showPerAnno(@RequestParam("id")long id, Model model){
		Stanza stanza = stanzaService.findbyId(id);
		List<Opera> opere= stanza.getOpere();
		model.addAttribute("opere", opere);
		model.addAttribute("stanza", stanza);
		ComparatorePerAnno comparatore = new ComparatorePerAnno();
		Collections.sort(opere,comparatore);
		return "/Opera/opereInStanza";
	}

	@GetMapping("/visualizzaPerTitoloStanza")
	public String showPerTitolo(@RequestParam("id")long id, Model model){
		Stanza stanza = stanzaService.findbyId(id);
		List<Opera> opere=stanza.getOpere();
		Collections.sort(opere);
		model.addAttribute("opere", opere);
		model.addAttribute("stanza", stanza);
		return "/Opera/opereInStanza";
	}

	@GetMapping("/modStanza")
	public String stanzaList(Model model){
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		return "/Stanza/listaStanzeAmministratore";
	}

	@GetMapping("/cancellaStanza")
	public String cancellaStanza(Model model, @RequestParam("id") Long id){
		Stanza stanza = stanzaService.findbyId(id);
		for(Opera o : stanza.getOpere()){
			o.setStanza(null);
			operaService.add(o);
		}
		stanza.setOpere(null);
		stanzaService.add(stanza);
		model.addAttribute("stanza", stanza);
		return "/Stanza/confermaCancellazioneStanza";
	}

	@PostMapping("/confermaCancellazioneStanza")
	public String confermaCancellazioneStanza(Model model, @RequestParam("id") Long id){
		stanzaService.delete(id);
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		return "/Stanza/listaStanzeAmministratore";
	}

	@GetMapping("/modificaStanza")
	public String modificaStanza(Model model, @RequestParam("id") Long id){
		Stanza stanza = stanzaService.findbyId(id);
		model.addAttribute("stanza", stanza);
		return "/Stanza/modificaStanza";
	}

	@PostMapping("/modificaStanza")
	public String modificaInfo(@Valid @ModelAttribute Stanza stanza, 
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/Stanza/modificaStanza";
		}
		stanzaService.add(stanza); 
		return "/Stanza/ritornaStanza";
	}
}