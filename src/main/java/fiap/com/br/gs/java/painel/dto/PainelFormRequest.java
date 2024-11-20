package fiap.com.br.gs.java.painel.dto;

import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.Usuario;


public record PainelFormRequest(
        String nome,
        String localizacao,
        Double capacidadeProducao,
        Long idUsuario,
        char status
) {
    public PainelSolar toModel(Usuario usuario) {
        return PainelSolar.builder()
                .nome(nome)
                .localizacao(localizacao)
                .capacidadeProducao(capacidadeProducao)
                .usuario(usuario)
                .status(status)
                .build();
    }
}
