package fiap.com.br.gs.java.painel.dto;
import fiap.com.br.gs.java.usuario.Usuario;

import java.time.LocalDateTime;

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
