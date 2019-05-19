package it.linksacademy.registrazionedb.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.linksacademy.registrazionedb.models.EmailPassword;
import it.linksacademy.registrazionedb.models.Utente;
import it.linksacademy.registrazionedb.repositories.UtenteRepository;

@Controller
@RequestMapping("utente")
public class UtenteController {
	
	
	@Autowired //Dependency Injection
	UtenteRepository utenteRepository;
	
	
	@GetMapping(path="/")
	public String welcome() {
		
		return "index";
	}
	
	@GetMapping(path="")
	public String welcome2() {
		
		return "index";
	}
	
	//questo metodo restituisce la lista di tutti gli utenti presenti nel database
	@GetMapping(path = "/all")
	public String listaUtenti(Model model) {
		
		model.addAttribute("utenti", utenteRepository.findAll());
		
		return "listautenti";
	}
	
	@GetMapping(path="/registration")
	public String registrazioneUtenti(Model model) {
		
		model.addAttribute("utente", new Utente());
		
		return "form";
	}
	
	
	@PostMapping(path="/registration")
	public String submitForm(@ModelAttribute Utente utente) {
		
		utenteRepository.save(utente);
		
		return "result";
	}
	
	
	//restituisce la lista di tutti gli utenti presenti nel database in formato JSON
	@RequestMapping("/listajson")
	public @ResponseBody Iterable<Utente> listaUtentiJson() {
		
		return utenteRepository.findAll();
	}
	
	
	//elimina un utente e restituisce una pagina che conferma l' eliminazione dell' utente
	@GetMapping(path="/all/{id}")
	public String cancellaUtente(@PathVariable int id) {
		
		utenteRepository.deleteById(id);
		
		return "utenteeliminato";
	}
	
	
	@GetMapping(path="/loginuser")
	public String login (Model model) {
		
		model.addAttribute("emailPassword", new EmailPassword());
		model.addAttribute("utente", new Utente());
		
		return "login";
	}
	
	//aggiustare il metodo bachecaUtente esso deve ricevere la mail e la password da login.html
	@PostMapping(path="/loginuser")
	public String bachecaUtente(@ModelAttribute EmailPassword emailPass, @ModelAttribute Utente utente, Model model, BindingResult result){ 
		
		Integer id;
		
		if (result.hasErrors()) {
		    return "login";
		  }
		
		id = utenteRepository.findIdByEmail(emailPass.getEmail());
		
		Optional<Utente> utenteBacheca;
		
		utenteBacheca = utenteRepository.findById(id);
		
		model.addAttribute("utente", utenteBacheca);
		
		return "bacheca";
	}
}
