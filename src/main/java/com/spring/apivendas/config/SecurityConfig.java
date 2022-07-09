package com.spring.apivendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(passwordEncoder())
			.withUser("otavio")
			.password(passwordEncoder().encode("1234"))
			.roles("USER","ADMIN");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.authorizeHttpRequests()
			.antMatchers("/clientes/**")
				.hasAnyRole("USER","ADMIN")
			.antMatchers("/produto/**")
				.hasRole("ADMIN")
			.antMatchers("/pedido/**")
				.hasRole("USER")
			.and()
			.formLogin();
	}
	
}
