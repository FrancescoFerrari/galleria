package it.uniroma3.galleria.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.galleria.comparator.ComparatorePerAnno;
import it.uniroma3.galleria.comparator.ComparatorePerAnnoNascita;
import it.uniroma3.galleria.model.Autore;
import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.service.AutoreService;

@Controller
public class AutoreController {
	@Autowired
	private AutoreService autoreService; 


	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}	


	@GetMapping("/autore")
	public String showForm(Autore autore) {
		return "/Autore/formAutore";
	}

	@PostMapping("/autore")
	public String checkArtistaInfo(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/Autore/formAutore";
		}
		else {
			if(autoreService.findbyName(autore.getNomeAutore().toUpperCase())!=null){
				return "/Autore/formAutore";
			}
			else{
				autore.setNomeAutore(autore.getNomeAutore().toUpperCase());
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				String dataNascita = df.format(autore.getAnnoNascita());
				model.addAttribute("dataNascita",dataNascita);
				try {
					String dataMorte = df.format(autore.getAnnoMorte());
					model.addAttribute("dataMorte",dataMorte);
					model.addAttribute(autore);
				} catch (Exception e) {
					model.addAttribute(autore);
				}
				autoreService.add(autore); 
			}
		}
		return "/Autore/ritornaAutore";
	}

	@GetMapping("/autoreList")
	public String showList(Model model){
		List<Autore> autori = (List<Autore>) autoreService.findAll();
		model.addAttribute("autori", autori);
		return "/Autore/listaAutori";
	}

	@GetMapping("/mostraAutore")
	public String showStanza(@RequestParam("id")long id, Model model){
		Autore autore = autoreService.findbyId(id);
		List<Opera> opere= autore.getOpereAutore();
		model.addAttribute("opere", opere);
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dataNascita = df.format(autore.getAnnoNascita());
		String dataMorte= df.format(autore.getAnnoMorte());
		model.addAttribute("dataNascita",dataNascita);
		model.addAttribute("dataMorte",dataMorte);
		model.addAttribute("autore", autore);
		return "/Opera/opereDelAutore";
	}
	@GetMapping("/visualizzaPerAnnoAutore")
	public String showPerAnno(@RequestParam("id")long id, Model model){
		Autore autore = autoreService.findbyId(id);
		List<Opera> opere= autore.getOpereAutore();
		model.addAttribute("opere", opere);
		model.addAttribute("autore", autore);
		ComparatorePerAnno comparatore = new ComparatorePerAnno();
		Collections.sort(opere,comparatore);
		return "/Opera/opereDelAutore";
	}

	@GetMapping("/visualizzaPerTitoloAutore")
	public String showPerTitolo(@RequestParam("id")long id, Model model){
		Autore autore = autoreService.findbyId(id);
		List<Opera> opere=autore.getOpereAutore();
		Collections.sort(opere);
		model.addAttribute("opere", opere);
		model.addAttribute("autore", autore);
		return "/Opera/opereDelAutore";
	}

	@GetMapping("/modAutore")
	public String autoreList(Model model){
		List<Autore> autori = (List<Autore>) autoreService.findAll();
		model.addAttribute("autori", autori);
		return "/Autore/listaAutoriAmministratore";
	}

	@GetMapping("/cancellaAutore")
	public String cancellaAutore(Model model, @RequestParam("id") Long id){
		Autore autore = autoreService.findbyId(id);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dataNascita = df.format(autore.getAnnoNascita());
		model.addAttribute("dataNascita", dataNascita);
		try {
			String dataMorte = df.format(autore.getAnnoMorte());
			model.addAttribute("dataMorte", dataMorte);
			model.addAttribute("autore", autore);

		} catch (Exception e) {
			model.addAttribute("autore", autore);
		}
		return "/Autore/confermaCancellazioneAutore";
	}

	@PostMapping("/confermaCancellazioneAutore")
	public String confermaCancellazioneAutore(Model model, @RequestParam("id") Long id){
		autoreService.delete(id);
		List<Autore> autori = (List<Autore>) autoreService.findAll();
		model.addAttribute("autori", autori);
		return "/Autore/listaAutoriAmministratore";
	}
	@GetMapping("/visualizzaPerAnnoNascitaAutore")
	public String showPerAnnoNascita(Model model){
		List<Autore> autori = (List<Autore>) autoreService.findAll();
		model.addAttribute("autori", autori);
		ComparatorePerAnnoNascita comparatore = new ComparatorePerAnnoNascita();
		Collections.sort(autori,comparatore);
		return "/Autore/listaAutori";
	}

	@GetMapping("/visualizzaPerNomeAutore")
	public String showPerTitolo(Model model){
		List<Autore> autori=(List<Autore>) autoreService.findAll();
		Collections.sort(autori);
		model.addAttribute("autori", autori);
		return "/Autore/listaAutori";
	}
}
