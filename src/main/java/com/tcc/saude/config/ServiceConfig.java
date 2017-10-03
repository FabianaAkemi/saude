package com.tcc.saude.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.tcc.saude.service.CadastroPacienteService;

@Configuration
@ComponentScan(basePackageClasses = CadastroPacienteService.class)
public class ServiceConfig {

}
