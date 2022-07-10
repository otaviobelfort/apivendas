package com.spring.apivendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apivendas.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}