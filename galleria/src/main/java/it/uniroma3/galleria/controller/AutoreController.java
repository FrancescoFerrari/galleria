package it.uniroma3.galleria.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import it.uniroma3.galleria.model.Autore;
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
	        return "formAutore";
	    }
	 
	    @PostMapping("/autore")
	    public String checkArtistaInfo(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {
	    	if (bindingResult.hasErrors()) {
	            return "formAutore";
	        }
	        else {
	        	autore.setNomeAutore(autore.getNomeAutore().toUpperCase());
	        	model.addAttribute(autore);
	            autoreService.add(autore); 
	        }
	        return "ritornaAutore";
	    }
}
