package br.com.senai.softwareacademia.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.com.senai.softwareacademia.excecoes.handler.AcessoNaoAutorizadoHandler;
import br.com.senai.softwareacademia.service.impl.CredencialDeAcessoServiceImpl;

@Configuration
@EnableWebSecurity
public class ApiSecurityConfig {
	
	@Autowired
	private FiltroDeAutenticacaoJwt filtroDeAutenticacao;
	
	@Autowired
	private CredencialDeAcessoServiceImpl service;
	
	@Autowired
	private AcessoNaoAutorizadoHandler acessoNaoAutorizadoHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(service);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public CorsFilter corsFilter() {
		
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    
	    final CorsConfiguration config = new CorsConfiguration();
	    
	    config.setAllowCredentials(true);
	    
	    config.setAllowedOriginPatterns(Collections.singletonList("*"));
	    
	    config.setAllowedHeaders(Arrays.asList(
	    		"Access-Control-Allow-Headers",
				"Access-Control-Allow-Origin", 				
				"Access-Control-Request-Method", 
	    		"Access-Control-Request-Headers",
	    		"Origin","Cache-Control", 
	    		"Content-Type", "Authorization", 
	    		"Access-Control-Allow-Headers"));
	    
	    config.setExposedHeaders(Arrays.asList(
	    		"Access-Control-Allow-Headers",
	    		"Access-Control-Allow-Origin",
	    		"Access-Control-Allow-Origin",
				"Authorization", "x-xsrf-token", "Access-Control-Allow-Headers",
	    		"Origin","Accept", "X-Requested-With", "Location",
	    		"Content-Type", "Access-Control-Request-Method",
	    		"Access-Control-Request-Headers"));
	    
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    
	    source.registerCorsConfiguration("/**", config);	    
	    
	    return new CorsFilter(source);	    	   
	    
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((request) -> 
				request.
					requestMatchers("/auth/**")
						.permitAll()
					.requestMatchers(HttpMethod.POST, "/exercicio/**")
						.hasAnyAuthority("ADMINISTRADOR")
					.requestMatchers(HttpMethod.PUT, "/exercicio/**")
						.hasAnyAuthority("ADMINISTRADOR")
					.requestMatchers(HttpMethod.PATCH, "/exercicio/**")
						.hasAnyAuthority("ADMINISTRADOR")
					.requestMatchers("/exercicio/**")
						.hasAnyAuthority("CLIENTE", "ADMINISTRADOR")
					.requestMatchers("/treino/**")
						.hasAnyAuthority("ADMINISTRADOR")
					.requestMatchers("/criarconta")	
						.permitAll()
					.anyRequest().authenticated())
			.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider())
				.addFilterBefore(filtroDeAutenticacao, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling((ex) -> {
				ex.accessDeniedHandler(acessoNaoAutorizadoHandler);
			})
			;
		return http.build();
	}
	
}