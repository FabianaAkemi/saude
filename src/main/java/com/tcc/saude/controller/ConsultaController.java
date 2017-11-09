package com.tcc.saude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.model.Consulta;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.repository.Pacientes;
import com.tcc.saude.security.UsuarioLogado;
import com.tcc.saude.service.CadastroConsultaService;
import com.tcc.saude.service.Exception.PacienteNuloException;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private CadastroConsultaService cadastroConsultaService;
	
	@Autowired 
	private Pacientes pacientes;
	
	@Autowired
	private Medicos medicos;
	
/*	@Autowired
	private Consultas consultas;*/
	
	@GetMapping("/novo")
	public ModelAndView nova(Consulta consulta) {
		ModelAndView mv = new ModelAndView("cadastros/cadastro-consulta");
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(Consulta consulta, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
								
		consulta.setPaciente(pacientes.findOne(Long.parseLong(consulta.getIdPaciente())));
		consulta.setMedico(medicos.findOne(Long.parseLong(consulta.getIdMedico())));
		consulta.setUsuario(usuarioLogado.getUsuario());
		
		if (result.hasErrors()) {
			return nova(consulta);
		}
		try{
			cadastroConsultaService.salvar(consulta);

		} catch (PacienteNuloException e) {
			result.rejectValue("paciente", e.getMessage(), e.getMessage());
			return nova(consulta);
			
		}
		
		
		attributes.addFlashAttribute("mensagem", "Consulta, salva com sucesso!");
		return new ModelAndView("redirect:/consultas/novo");
	}
	
	/*@GetMapping
	public ModelAndView pesquisar(ConsultaFilter consultaFilter,
			@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pesquisas/pesquisa-consulta");

		
		PageWrapper<Consulta> paginaWrapper = new PageWrapper<>(consultas.filtrar(consultaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}*/
}
