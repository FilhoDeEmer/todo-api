package com.filhodeemer.todo_api.repository;

import com.filhodeemer.todo_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}