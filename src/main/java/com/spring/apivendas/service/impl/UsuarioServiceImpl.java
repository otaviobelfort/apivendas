package com.spring.apivendas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.apivendas.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    
    private PasswordEncoder encoder;

    public UsuarioServiceImpl(PasswordEncoder encoder) {

		this.encoder = encoder;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!username.equals("otavio")){
            throw new UsernameNotFoundException("Usuário não encontrado na base.");
        }

        return User
                .builder()
                .username("cicrano")
                .password(encoder.encode("123"))
                .roles("USER", "ADMIN")
                .build();
    }

}