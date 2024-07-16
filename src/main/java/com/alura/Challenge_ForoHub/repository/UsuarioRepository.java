package com.alura.Challenge_ForoHub.repository;

import com.alura.Challenge_ForoHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
