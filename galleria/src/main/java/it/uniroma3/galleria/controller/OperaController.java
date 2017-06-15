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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.galleria.model.Autore;
import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.service.AutoreService;
import it.uniroma3.galleria.service.OperaService;
import it.uniroma3.galleria.service.StanzaService;

@Controller
public class OperaController  {
	@Autowired
	private AutoreService autoreService;
	@Autowired
	private OperaService operaService; 
	@Autowired
	private StanzaService stanzaService; 

	@GetMapping("/opera")
	public String showForm(Model model, Opera opera) {
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		return "/Opera/formOpera";
	}

	@PostMapping("/opera")
	public String checkOperaInfo(@Valid @ModelAttribute Opera opera, 
			BindingResult bindingResult, Model model , @RequestParam("nomeAutore") String nomeAutore) {

		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);

		if (bindingResult.hasErrors() || autoreService.findbyName(nomeAutore.toUpperCase())==null ) {
			return "/Opera/formOpera";
		}
		else {
			Autore autore = autoreService.findbyName(nomeAutore.toUpperCase());
			opera.setAutore(autore);
			autore.getOpereAutore().add(opera);
			model.addAttribute(autore);
			model.addAttribute(opera);
			opera.getStanza().getOpere().add(opera);
			operaService.add(opera); 
		}
		return "/Opera/ritornaOpera";
	}

	@GetMapping("/operaList")
	public String ListaOpere(List<Opera> opere){
		return"/Opera/listaOpere";
	}

	@GetMapping("/mostraOpera")
	public String showOpera(Model model ,@RequestParam("id") Long id ){
		Opera opera = operaService.findbyId(id);
		model.addAttribute("opera", opera);
		return "/Opera/ritornaOpera";
	}
	
	@GetMapping("/modOpera")
	public String operaList(Model model){
		List<Opera> opere = (List<Opera>) operaService.findAll();
		model.addAttribute("opere", opere);
		return "/Opera/listaOpere";
	}

	@GetMapping("/modificaOpera")
	public String modificaOpera(Model model, @RequestParam("id") Long id){
		List<Stanza> stanze = (List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		Opera opera = operaService.findbyId(id);
		Autore autore =opera.getAutore();
		model.addAttribute("autore",autore);
		model.addAttribute("opera", opera);
		return "/Opera/modificaOpera";
	}

	@PostMapping("/modificaOpera")
	public String modificaInfo(@Valid @ModelAttribute Opera opera, 
			BindingResult bindingResult, Model model, @RequestParam("nomeAutore") String nomeAutore) {
		
		List<Stanza> stanze =(List<Stanza>) stanzaService.findAll();
		model.addAttribute("stanze", stanze);
		
		if (bindingResult.hasErrors()|| autoreService.findbyName(nomeAutore.toUpperCase())==null) {
			return "/Opera/formOpera";
		}
		else {
			Autore autore= autoreService.findbyName(nomeAutore.toUpperCase());
			opera.setAutore(autore);
			autore.getOpereAutore().add(opera);
		    opera.getStanza().getOpere().add(opera);
			model.addAttribute(autore);
			model.addAttribute(opera);
			try {
				operaService.add(opera); 
			} catch (Exception e) {
				return "ritornaOpera";
			}
		}
		return "/Opera/ritornaOpera";
	}
	@GetMapping("/cancellaOpera")
	public String cancellaOpera(Model model, @RequestParam("id") Long id){
		Opera opera = operaService.findbyId(id);
		model.addAttribute("opera", opera);
		return "/Opera/confermaCancellazioneOpera";
	}
	
	@PostMapping("/confermaCancellazione")
	public String confermaCancellazioneOpera(Model model, @RequestParam("id") Long id){
		operaService.delete(id);
		List<Opera> opere = (List<Opera>) operaService.findAll();
		model.addAttribute("opere", opere);
		return "/Opera/listaOpere";
	}
}

