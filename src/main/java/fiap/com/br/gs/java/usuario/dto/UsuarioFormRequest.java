package fiap.com.br.gs.java.usuario.dto;

import fiap.com.br.gs.java.usuario.Usuario;

import java.time.LocalDateTime;

public record UsuarioFormRequest(
        String nome,
        String email,
        String senha,
        char status

) {
    public Usuario toModel(){
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .dataCadastro(LocalDateTime.now())
                .status(status)
                .build();


    }


    }



