package fiap.com.br.gs.java.painel.dto;

import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.Usuario;

public record PainelResponse(
        Long id,
        String nome,
        String localizacao,
        Double capacidadeProducao,
        Usuario usuario,
        char status
) {
    public static PainelResponse from(PainelSolar painelSolar){
        return new PainelResponse(
                painelSolar.getId(),
                painelSolar.getNome(),
                painelSolar.getLocalizacao(),
                painelSolar.getCapacidadeProducao(),
                painelSolar.getUsuario(),
                painelSolar.getStatus()
        );
    }
}
