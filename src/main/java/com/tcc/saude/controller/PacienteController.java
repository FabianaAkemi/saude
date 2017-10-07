package com.tcc.saude.controller;


import com.tcc.saude.model.Paciente;
import com.tcc.saude.service.CadastroPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {


	@Autowired
	private CadastroPacienteService cadastroPacienteService;

	@RequestMapping("/novo")
	private ModelAndView novo(Paciente paciente) {

		ModelAndView mv = new ModelAndView("cadastros/cadastro-paciente");
		mv.addObject(paciente);
		return mv;

	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	private ModelAndView salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(paciente);
		}

		cadastroPacienteService.salvar(paciente);
		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso!");
		return new ModelAndView("redirect:/pacientes/novo");
	}

//	@GetMapping("/pacientes")
//	public ModelAndView pesquisar(PacienteFilter pacienteFilter) {
//
//		ModelAndView mv = new ModelAndView("pesquisas/pesquisa-paciente");
//		mv.addObject("pacientesRepository", pacientes
//				.findByNomeContainingIgnoreCase(Optional.ofNullable(pacienteFilter.getNome()).orElse("%")));
//
//		return mv;
//	}

//	@GetMapping("/pacientes/{id}")
//	public ModelAndView editar(@PathVariable Long id) {
//		Paciente paciente = pacientes.findOne(id);
//		return novo(paciente);
//	}
}
