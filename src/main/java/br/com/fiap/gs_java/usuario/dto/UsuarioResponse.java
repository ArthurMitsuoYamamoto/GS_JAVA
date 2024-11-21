package br.com.fiap.gs_java.usuario.dto;

import br.com.fiap.gs_java.usuario.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String senha



) {
    public static UsuarioResponse from(Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha()


        );
    }
}
