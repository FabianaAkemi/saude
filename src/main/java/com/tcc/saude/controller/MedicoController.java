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
import com.tcc.saude.model.Medico;
import com.tcc.saude.model.TipoEspecialista;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.repository.filter.MedicoFilter;
import com.tcc.saude.service.CadastroMedicoService;
import com.tcc.saude.service.Exception.CrmMedicoCadastradoException;
import com.tcc.saude.service.Exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/medicos")
public class MedicoController {
	
	@Autowired
	private CadastroMedicoService cadastroMedicoService;

	@Autowired
	private Medicos medicos;
	
	@RequestMapping("/novo")
	private ModelAndView novo(Medico medico) {

		ModelAndView mv = new ModelAndView("cadastros/cadastro-medico");
		mv.addObject(medico);
        mv.addObject("especialistas", TipoEspecialista.values());
		return mv;

	}
	
	@PostMapping({ "/novo", "{\\+d}" })
	private ModelAndView salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return novo(medico);
		}

		try {
			cadastroMedicoService.salvar(medico);
			
		}catch (CrmMedicoCadastradoException e) {
			result.rejectValue("crm", e.getMessage(), e.getMessage());
			return novo(medico);
		}

		attributes.addFlashAttribute("mensagem", "Médico salvo com sucesso!");
		return new ModelAndView("redirect:/medicos/novo");
	}
	
    @GetMapping
	public ModelAndView pesquisar(MedicoFilter medicoFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("pesquisas/pesquisa-medico");
		
        mv.addObject("especialistas", TipoEspecialista.values());
		
		PageWrapper<Medico> paginaWrapper = new PageWrapper<>(medicos.filtrar(medicoFilter, pageable)
				, httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
    
    @RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
  	public @ResponseBody List<Medico> pesquisar(String nome) {
      	validarTamanhoNome(nome);
  		return medicos.findByNomeStartingWithIgnoreCase(nome);
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
			cadastroMedicoService.excluir(id);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
  	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) { 
		Medico medico = medicos.findOne(id);
		
		ModelAndView mv = novo(medico);
		mv.addObject(medico);
		return mv;
	}

}
