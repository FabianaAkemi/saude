package com.tcc.saude.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tcc.saude.dto.PeriodoRelatorio;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@GetMapping("/consultasPacientes")
	public ModelAndView relatorioConsultas() {
		ModelAndView mv = new ModelAndView("relatorio/relatorio-consulta-paciente");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@PostMapping("/consultasPacientes")
	public ModelAndView gerarRelatorioConsultas(PeriodoRelatorio periodoRelatorio) {
		Map<String, Object> parametros = new HashMap<>();
		
		
		Long pacienteId  = Long.parseLong(periodoRelatorio.getIdPaciente());
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("paciente_id", pacienteId );
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		
		return new ModelAndView("relatorio_consulta", parametros);
	}

}
