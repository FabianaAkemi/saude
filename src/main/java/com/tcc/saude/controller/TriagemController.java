package com.tcc.saude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TriagemController {

	 @RequestMapping("/triagens/novo")
	    public String home(){
	        return "cadastros/cadastro-triagem";       
	    }
}
