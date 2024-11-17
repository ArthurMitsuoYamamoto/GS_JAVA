package fiap.com.br.gs.java.painel.dto;
import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.Usuario;
import org.stringtemplate.v4.ST;

public record PainelResponse(
        Long id,
        Usuario usuario,
        String nome,
        String localizacao,
        Double capacidadeProducao
        ) {
    public static PainelResponse from(PainelSolar painelSolar){
        return new PainelResponse(
                painelSolar.getId(),
                painelSolar.getUsuario(),
                painelSolar.getNome(),
                painelSolar.getLocalizacao(),
                painelSolar.getCapacidadeProducao()
        );
    }

}
