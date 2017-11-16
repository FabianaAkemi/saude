package com.tcc.saude.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.controller.page.PageWrapper;
import com.tcc.saude.model.AtividadeExercida;
import com.tcc.saude.model.Colaborador;
import com.tcc.saude.repository.Colaboradores;
import com.tcc.saude.repository.filter.ColaboradorFilter;
import com.tcc.saude.service.CadastroColaboradorService;
import com.tcc.saude.service.Exception.ImpossivelExcluirEntidadeException;
import com.tcc.saude.service.Exception.RgColaboradorCadastradoException;

@Controller
@RequestMapping("/colaboradores")
public class ColaboradorController {
	
	@Autowired
	private CadastroColaboradorService cadastroColaboradorService;

	@Autowired
	private Colaboradores colaboradores;
	
	@RequestMapping("/novo")
	private ModelAndView novo(Colaborador colaborador) {

		ModelAndView mv = new ModelAndView("cadastros/cadastro-colaborador");
		mv.addObject(colaborador);
        mv.addObject("atividades", AtividadeExercida.values());
		return mv;

	}
	
	@PostMapping({ "/novo", "{\\+d}" })
	private ModelAndView salvar(@Valid Colaborador colaborador, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(colaborador);
		}

		try {
			cadastroColaboradorService.salvar(colaborador);
			
		}catch (RgColaboradorCadastradoException e) {
			result.rejectValue("rg", e.getMessage(), e.getMessage());
			return novo(colaborador);
		}

		attributes.addFlashAttribute("mensagem", "Colaborador, salvo com sucesso!");
		return new ModelAndView("redirect:/colaboradores/novo");
	}
	
    @GetMapping
	public ModelAndView pesquisar(ColaboradorFilter colaboradorFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pesquisas/pesquisa-colaborador");
		
        mv.addObject("atividades", AtividadeExercida.values());
		
		PageWrapper<Colaborador> paginaWrapper = new PageWrapper<>(colaboradores.filtrar(colaboradorFilter, pageable)
				, httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
    
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			cadastroColaboradorService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) { 
		Colaborador colaborador = colaboradores.findOne(id);
		
		ModelAndView mv = novo(colaborador);
		mv.addObject(colaborador);
		return mv;
	}
	

}
