package com.tcc.saude.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.controller.page.PageWrapper;
import com.tcc.saude.model.Triagem;
import com.tcc.saude.repository.Pacientes;
import com.tcc.saude.repository.Triagens;
import com.tcc.saude.repository.filter.TriagemFilter;
import com.tcc.saude.security.UsuarioLogado;
import com.tcc.saude.service.CadastroTriagemService;
import com.tcc.saude.service.Exception.PacienteNuloException;

@Controller
@RequestMapping("/triagens")
public class TriagemController {

		@Autowired
		private CadastroTriagemService cadastroTriagemService;	
		
		@Autowired 
		private Pacientes pacientes ;
		
		@Autowired
		private Triagens triagens;
		
		@GetMapping("/novo")
		public ModelAndView nova(Triagem triagem) {
			ModelAndView mv = new ModelAndView("cadastros/cadastro-triagem");
			
			return mv;
		}
		
		@PostMapping("/novo")
		public ModelAndView salvar(Triagem triagem, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
									
			triagem.setPaciente(pacientes.findOne(Long.parseLong(triagem.getIdTela())));
			triagem.setUsuario(usuarioLogado.getUsuario());
			
			if (result.hasErrors()) {
				return nova(triagem);
			}
			try{
				cadastroTriagemService.salvar(triagem);

			}catch(PacienteNuloException e) {
				result.rejectValue("paciente", e.getMessage(), e.getMessage());
				return nova(triagem);
				
			}
			
			attributes.addFlashAttribute("mensagem", "Triagem, salva com sucesso!");
			return new ModelAndView("redirect:/triagens/novo");
		}
		
		@GetMapping
		public ModelAndView pesquisar(TriagemFilter triagemFilter,
				@PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
			ModelAndView mv = new ModelAndView("pesquisas/pesquisa-triagem");

			
			PageWrapper<Triagem> paginaWrapper = new PageWrapper<>(triagens.filtrar(triagemFilter, pageable)
					, httpServletRequest);
			mv.addObject("pagina", paginaWrapper);
			return mv;
		}
}
