package com.tcc.saude.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.controller.page.PageWrapper;
import com.tcc.saude.model.Paciente;
import com.tcc.saude.repository.Pacientes;
import com.tcc.saude.repository.filter.PacienteFilter;
import com.tcc.saude.service.CadastroPacienteService;
import com.tcc.saude.service.Exception.CpfPacienteCadastradoException;
import com.tcc.saude.service.Exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {


	@Autowired
	private CadastroPacienteService cadastroPacienteService;
	
	@Autowired
	private Pacientes pacientes;

	@RequestMapping("/novo")
	private ModelAndView novo(Paciente paciente) {

		ModelAndView mv = new ModelAndView("cadastros/cadastro-paciente");
		mv.addObject(paciente);
		return mv;

	}
	
	@PostMapping({ "/novo", "{\\+d}" })
	private ModelAndView salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(paciente);
		}
		
		try {
			cadastroPacienteService.salvar(paciente);

		}catch (CpfPacienteCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(paciente);
		}

		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso!");
		return new ModelAndView("redirect:/pacientes/novo");
	}

	
	  
    @GetMapping
	public ModelAndView pesquisar(PacienteFilter pacienteFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pesquisas/pesquisa-paciente");
		
		PageWrapper<Paciente> paginaWrapper = new PageWrapper<>(pacientes.filtrar(pacienteFilter, pageable)
				, httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
    
    @RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Paciente> pesquisar(String nome) {
    	validarTamanhoNome(nome);
		return pacientes.findByNomeStartingWithIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroPacienteService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) { 
		Paciente paciente = pacientes.findOne(id);
		
		ModelAndView mv = novo(paciente);
		mv.addObject(paciente);
		return mv;
	}
}
