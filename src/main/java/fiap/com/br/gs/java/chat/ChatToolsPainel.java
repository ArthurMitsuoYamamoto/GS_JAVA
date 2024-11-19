package fiap.com.br.gs.java.chat;

import fiap.com.br.gs.java.painel.PainelService;
import fiap.com.br.gs.java.painel.PainelSolar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;


@Configuration
public class ChatToolsPainel {

    public record PainelRequest(String nome){}
    public record PainelForm(String nome){}

    private final PainelService PainelService;


    public ChatToolsPainel(PainelService PainelService) {
        this.PainelService = PainelService;
    }

    @Bean
    @Description("Busca um usuário por nome")
    public Function<PainelRequest, PainelSolar> findPainel(){
        return request -> PainelService.findPainel(request.nome);
    }


    @Bean
    @Description("Cadastrar um Painel no web site SolarSense para poder usar seus recursos")
    public Function<PainelForm, PainelSolar> CadastrarPainelSolar(){
        return request -> PainelService.cadastrarPainelSolar(request.nome);
    }

}