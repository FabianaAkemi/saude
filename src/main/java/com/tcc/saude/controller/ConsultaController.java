package com.tcc.saude.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.controller.page.PageWrapper;
import com.tcc.saude.controller.validation.ConsultaValidator;
import com.tcc.saude.model.Consulta;
import com.tcc.saude.repository.Consultas;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.repository.Pacientes;
import com.tcc.saude.repository.filter.ConsultaFilter;
import com.tcc.saude.security.UsuarioLogado;
import com.tcc.saude.service.CadastroConsultaService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private CadastroConsultaService cadastroConsultaService;
	
	@Autowired 
	private Pacientes pacientes;
	
	@Autowired
	private Medicos medicos;
	
	@Autowired
	private Consultas consultas;
	
	@Autowired
	private ConsultaValidator consultaValidator;
	
	@InitBinder
	public void iniciaValidator(WebDataBinder binder){
		binder.setValidator(consultaValidator);
	}
	
	@GetMapping("/novo")
	public ModelAndView nova(Consulta consulta) {
		ModelAndView mv = new ModelAndView("cadastros/cadastro-consulta");
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
			
		if (result.hasErrors()) {
			return nova(consulta);
		}
		
		consulta.setPaciente(pacientes.findOne(Long.parseLong(consulta.getIdPaciente())));
		consulta.setMedico(medicos.findOne(Long.parseLong(consulta.getIdMedico())));
		consulta.setUsuario(usuarioLogado.getUsuario());
		
		
		cadastroConsultaService.salvar(consulta);
		
		attributes.addFlashAttribute("mensagem", "Consulta, salva com sucesso!");
		return new ModelAndView("redirect:/consultas/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ConsultaFilter consultaFilter,
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pesquisas/pesquisa-consulta");

		
		PageWrapper<Consulta> paginaWrapper = new PageWrapper<>(consultas.filtrar(consultaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView pesquisaCompleta(@PathVariable("id") Long id) { 
		Consulta consulta = consultas.findOne(id);
		
		ModelAndView mv = nova(consulta);
		mv.addObject(consulta);
		return mv;
	}
}
