package com.tcc.saude.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.model.Medico;
import com.tcc.saude.service.CadasatroMedicoService;

@Controller
public class MedicoController {
	
	@Autowired
	private CadasatroMedicoService cadastroMedicoService;

	@RequestMapping("/medicos/novo")
	private ModelAndView novo(Medico medico) {

		ModelAndView mv = new ModelAndView("cadastros/cadastro-medico");
		mv.addObject(medico);
		return mv;

	}
	
	@RequestMapping(value = "/medicos/novo", method = RequestMethod.POST)
	private ModelAndView salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(medico);
		}

		cadastroMedicoService.salvar(medico);
		attributes.addFlashAttribute("mensagem", "Medico salvo com sucesso!");
		return new ModelAndView("redirect:/medicos/novo");
	}
}
