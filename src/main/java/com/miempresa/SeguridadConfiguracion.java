package com.miempresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.miempresa.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SeguridadConfiguracion {
    
	@ControllerAdvice
	public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	    @ExceptionHandler(Exception.class)
	    public ModelAndView handleException(Exception ex) {
	        ModelAndView modelAndView = new ModelAndView("error");
	        modelAndView.addObject("errorMessage", "Se ha producido un error inesperado.");
	        return modelAndView;
	    }
	}
    @Autowired
    private UsuarioServicio userDet; 
    
    @Autowired
    private BCryptPasswordEncoder bcryp;
    
    @Bean
    public BCryptPasswordEncoder passEncoder() {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
        return bcpe;
    }
    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDet).passwordEncoder(bcryp);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (authz) -> authz

              
                .requestMatchers("/citas/list").permitAll()
                
                
                .requestMatchers("/citas/form").hasRole("ADMIN")
                .requestMatchers("/citas/guardar").hasRole("ADMIN")
                .requestMatchers("/citas/editar/**").hasRole("ADMIN")
                .requestMatchers("/citas/eliminar/**").hasRole("ADMIN")

                
                .requestMatchers("/clientes/list").permitAll()
                
               
                .requestMatchers("/clientes/form").hasRole("ADMIN")
                .requestMatchers("/clientes/guardar").hasRole("ADMIN")
                .requestMatchers("/clientes/editar/**").hasRole("ADMIN")
                .requestMatchers("/clientes/eliminar/**").hasRole("ADMIN")
                
              
                .requestMatchers("/mascotas/list").permitAll()
                
       
                .requestMatchers("/mascotas/form").hasRole("ADMIN")
                .requestMatchers("/mascotas/guardar").hasRole("ADMIN")
                .requestMatchers("/mascotas/editar/**").hasRole("ADMIN")
                .requestMatchers("/mascotas/eliminar/**").hasRole("ADMIN")

                
                .anyRequest().authenticated())
               
              	.csrf((csrf)-> csrf.disable())
        		.formLogin((form) -> form

        	    .permitAll()
        	   
        	    .usernameParameter("username")
    	        .passwordParameter("password")
        	)

                .logout((logout) -> logout.permitAll());
        
        return http.build();
    }
}
