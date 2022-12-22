package com.co.andresfot.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.co.andresfot.blog.auth.handler.LoginSuccesHandler;
import com.co.andresfot.blog.model.service.JpaUserDetailsService;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsServiceJpa;
	
	@Bean //autorización
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/uploads/**")
			.permitAll()
		.antMatchers("/listado-posts").hasAnyRole("ADMIN")
		.antMatchers("/editar-post/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar-post/**").hasAnyRole("ADMIN")
		.antMatchers("/categorias/**").hasAnyRole("ADMIN")
		.antMatchers("/comentarios/comentario-post/**").hasAnyRole("USER")
		.antMatchers("/comentarios/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
			.formLogin()
				.successHandler(successHandler)
				.loginPage("/login")
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
        	
        return http.build();
    }
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		build.userDetailsService(userDetailsServiceJpa).passwordEncoder(passwordEncoder);
	}
	
}

