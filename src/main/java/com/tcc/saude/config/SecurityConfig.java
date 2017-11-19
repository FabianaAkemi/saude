package com.tcc.saude.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tcc.saude.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		http
			.authorizeRequests()
			.antMatchers("/usuarios/novo").hasRole("CADASTRAR_USUARIO")
			.antMatchers("/usuarios").hasRole("PESQUISAR_USUARIO")			
			.antMatchers("/colaboradores/novo").hasRole("CADASTRAR_COLABORADOR")
			.antMatchers("/colaboradores").hasRole("PESQUISAR_COLABORADOR")
			.antMatchers("/pacientes/novo").hasRole("CADASTRAR_PACIENTE")
			.antMatchers("/pacientes").hasRole("PESQUISAR_PACIENTE")
			.antMatchers("/medicos/novo").hasRole("CADASTRAR_MEDICO")
			.antMatchers("/medicos").hasRole("PESQUISAR_MEDICO")
			.antMatchers("/triagens/novo").hasRole("CADASTRAR_TRIAGEM")
			.antMatchers("/triagens").hasRole("PESQUISAR_TRIAGEM")
			.antMatchers("/consultas/novo").hasRole("CADASTRAR_CONSULTA")
			.antMatchers("/consultas").hasRole("PESQUISAR_CONSULTA")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
		.exceptionHandling()
			.accessDeniedPage("/403")
			.and()
		.sessionManagement()
			.invalidSessionUrl("/login");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	

}
