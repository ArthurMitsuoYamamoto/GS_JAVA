package br.com.fiap.gs_java.chat;

import br.com.fiap.gs_java.painel.PainelService;
import br.com.fiap.gs_java.painel.PainelSolar;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class ChatToolsPainel {

    private final PainelService painelService;

    public record PainelRequest(String nome){}
    public record PainelForm(String nome){}

    @Bean
    @Description("Busca um painel pelo nome")
    public Function<PainelRequest, PainelSolar> findPainel(){
        return request -> painelService.findPainel(request.nome);
    }

    @Bean
    @Description("Cadastrar um Painel no web site SolarSense para poder usar seus recursos")
    public Function<PainelForm, PainelSolar> CadastrarPainelSolar(){
        return request -> painelService.cadastrarPainelSolar(request.nome);
    }

}