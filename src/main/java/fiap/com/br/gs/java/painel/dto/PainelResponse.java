package fiap.com.br.gs.java.painel.dto;

import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.Usuario;

import java.math.BigDecimal;

public record PainelResponse(
        Long id,
        String nome,
        String localizacao,
        BigDecimal capacidadeProducao,
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
