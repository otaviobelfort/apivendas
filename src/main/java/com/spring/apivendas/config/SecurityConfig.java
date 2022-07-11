package com.spring.apivendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.apivendas.service.impl.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UsuarioServiceImpl usuarioService;

    public SecurityConfig(UsuarioServiceImpl usuarioService) {
		this.usuarioService = usuarioService;
	}

//	@Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
	
	@Bean(name = "passwordEncoder")
	public static PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/clientes/**")
                    .hasAnyRole("ADMIN")
                .antMatchers("/pedido/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/produto/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/usuario/**")
                	.permitAll()
                .anyRequest().authenticated()
            .and()
                .httpBasic();
        ;
    }

}
