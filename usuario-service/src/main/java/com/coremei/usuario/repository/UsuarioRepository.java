package com.coremei.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coremei.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
