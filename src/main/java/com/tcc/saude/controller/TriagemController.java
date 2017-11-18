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
import com.tcc.saude.controller.validation.TriagemValidator;
import com.tcc.saude.model.Triagem;
import com.tcc.saude.repository.Pacientes;
import com.tcc.saude.repository.Triagens;
import com.tcc.saude.repository.filter.TriagemFilter;
import com.tcc.saude.security.UsuarioLogado;
import com.tcc.saude.service.CadastroTriagemService;

@Controller
@RequestMapping("/triagens")
public class TriagemController {

		@Autowired
		private CadastroTriagemService cadastroTriagemService;	
		
		@Autowired 
		private Pacientes pacientes ;
		
		@Autowired
		private Triagens triagens;
		
		@Autowired
		private TriagemValidator triagemValidator;
		
		@InitBinder
		public void iniciaValidator(WebDataBinder binder){
			binder.setValidator(triagemValidator);
		}
		
		@GetMapping("/novo")
		public ModelAndView nova(Triagem triagem) {
			ModelAndView mv = new ModelAndView("cadastros/cadastro-triagem");
			
			return mv;
		}
		
		@PostMapping("/novo")
		public ModelAndView salvar(@Valid Triagem triagem, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioLogado usuarioLogado) {
			
			if (result.hasErrors()) {
				return nova(triagem);
			}
			
			triagem.setPaciente(pacientes.findOne(Long.parseLong(triagem.getIdTela())));
			triagem.setUsuario(usuarioLogado.getUsuario());
			
			
			
			cadastroTriagemService.salvar(triagem);			
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
		
		@GetMapping("/{id}")
		public ModelAndView pesquisaCompleta(@PathVariable("id") Long id) { 
			Triagem triagem = triagens.findOne(id);
			
			ModelAndView mv = nova(triagem);
			mv.addObject(triagem);
			return mv;
		}
}
