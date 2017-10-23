package com.tcc.saude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrosController {
	
	
	@GetMapping("/403")
	public String permissaoNegada(){
		return "errors/403";
	}
	
	@GetMapping("/404")
	public String paginaNaoLocalizada() {
		return "errors/404";
	}
	
	@RequestMapping("/500")
	public String erroServidor() {
		return "errors/500";
	}
	
	
}
