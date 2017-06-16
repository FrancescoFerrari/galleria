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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.galleria.model.Ruolo;
import it.uniroma3.galleria.model.Amministratore;
import it.uniroma3.galleria.service.RuoloService;
import it.uniroma3.galleria.service.AmministratoreService;

@Controller
public class WebController {

	@Autowired
	private AmministratoreService amministratoreService;
	@Autowired
	private RuoloService ruoloService;



	@RequestMapping(value={"/signUp"})
	public String signUp(@Valid @ModelAttribute Amministratore user, 
			BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			return "signUp";
		}
		else {
			model.addAttribute(user);
			amministratoreService.add(user); 
			ruoloService.add(new Ruolo(user.getUsername()));
		}
		return "datiUtente";
	}
	@RequestMapping(value={"/welcome"})
	public String welcome(){
		return "welcome";
	}

	@RequestMapping(value="/admin")
	public String admin(){
		return "admin";
	}

	@RequestMapping(value={"/login"})
	public String login(){
		return "login";
	}


	@RequestMapping(value="/403")
	public String Error403(){
		return "403";
	}
	
	@RequestMapping(value={"/opAvanzate"})
	public String ShowOperazioniAvanzate(){
		return "operazioniAvanzate";
	}
	

	@GetMapping("/insAmministratore")
	public String showForm(Amministratore amministratore) {
		return "formAmministratore";	
	}


	@PostMapping("/mostraAmministratore")
	public String checkAmministratoreInfo(@Valid @ModelAttribute Amministratore amministratore,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors() || amministratoreService.findByUsername(amministratore.getUsername())!=null) {
			return "formAmministratore";
		}
		else {
			model.addAttribute(amministratore);
			amministratoreService.add(amministratore); 
			ruoloService.add(new Ruolo(amministratore.getUsername()));
		}
		return "mostraAmministratore";
	}
	
	@GetMapping("/amministratoreList")
	public String showList(Model model){
		List<Amministratore> amministratori = (List<Amministratore>) amministratoreService.findAll();
		model.addAttribute("amministratori", amministratori);
		return "listaAmministratori";
	}
	
	@GetMapping("stampaAmministratore")
	public String mostraAmministratore(Amministratore amministratore, Model model,@RequestParam("id")Long id){
		amministratore=amministratoreService.findbyId(id);
		model.addAttribute("amministratore",amministratore);
		return "mostraAmministratore";
		
	}


}