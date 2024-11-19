package fiap.com.br.gs.java.chat;

import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.Usuario;
import fiap.com.br.gs.java.usuario.UsuarioService;
import jakarta.validation.constraints.Email;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;


@Configuration
public class ChatToolsUsuario {

    public record UsuarioRequest(String nome, String email){}
    public record UsuarioForm(String nome, String senha){}

    private final UsuarioService usuarioService;

    public ChatToolsUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    @Description("Busca um usuário por nome ou email")
    public Function<UsuarioRequest, Usuario> findUsuario(){
        return request -> usuarioService.findUsuario(request.nome, request.email);
    }


    @Bean
    @Description("Cadastrar um Usuario no web site SolarSense para poder usar seus recursos")
    public Function<UsuarioForm, Usuario> CadastrarUsuario(){
        return request -> usuarioService.cadastrarUsuario(request.nome, request.senha);
    }


}