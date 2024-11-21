package br.com.fiap.gs_java.usuario.dto;

import java.time.LocalDateTime;

public record UsuarioFormRequest(
        String nome,
        String email,
        String senha


) { }



