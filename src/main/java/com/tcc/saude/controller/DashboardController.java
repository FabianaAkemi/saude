package com.tcc.saude.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcc.saude.repository.Colaboradores;
import com.tcc.saude.repository.Medicos;
import com.tcc.saude.repository.Pacientes;

@Controller
@RequestMapping("/")
public class DashboardController {
	
		@Autowired
		private Pacientes pacientes;
		
		@Autowired 
		private Medicos medicos;
		
		@Autowired
		private Colaboradores colaboradores;

		@GetMapping
		public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("totalPacientes", pacientes.count());
		mv.addObject("totalMedicos",medicos.count());
		mv.addObject("totalColaboradores",colaboradores.count());
		
		return mv;
	}
	
}
