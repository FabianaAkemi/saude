package com.tcc.saude.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcc.saude.controller.page.PageWrapper;
import com.tcc.saude.model.Medico;
import com.tcc.saude.model.TipoEspecialista;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.repository.filter.MedicoFilter;
import com.tcc.saude.service.CadastroMedicoService;
import com.tcc.saude.service.Exception.CrmMedicoCadastradoException;

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
	
	@PostMapping("/novo")
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

		attributes.addFlashAttribute("mensagem", "Medico, salvo com sucesso!");
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
}
