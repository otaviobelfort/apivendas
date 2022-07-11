package com.spring.apivendas.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.apivendas.entity.Usuario;
import com.spring.apivendas.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
    private UsuarioRepository usuarioRepository;
    
//    private PasswordEncoder encoder;
//
//    public UsuarioServiceImpl(PasswordEncoder encoder) {
//		this.encoder = encoder;
//	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Usuario usuario =  usuarioRepository.findByLogin(username)
        				 .orElseThrow(() -> new UsernameNotFoundException("Usuario não válido!"))	;
      String[] roles = usuario.isAdmin() ?
              new String[]{"ADMIN", "USER"} : new String[]{"USER"};

      return User
              .builder()
              .username(usuario.getLogin())
              .password(usuario.getSenha())
              .roles(roles)
              .build();
	}
	
	@Transactional
    public Usuario salvarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}