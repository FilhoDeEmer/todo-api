package com.filhodeemer.todo_api.controller;

import com.filhodeemer.todo_api.dto.LoginRequest;
import com.filhodeemer.todo_api.model.Usuario;
import com.filhodeemer.todo_api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.filhodeemer.todo_api.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil, UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Usuario usuario = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if(!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        return jwtUtil.gerarToken(usuario.getEmail(), usuario.getRole());

    }
}
