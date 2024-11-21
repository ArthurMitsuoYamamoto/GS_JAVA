package fiap.com.br.gs.java.usuario;

import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Injeção de dependências via construtor
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para listar todos os usuários
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();  // Usando o usuarioRepository corretamente
    }

    // Método para buscar usuário por ID
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);  // Usando o usuarioRepository corretamente
    }

    // Método para criar um novo usuário
    public Usuario save(Usuario usuario) {
        // Codificando a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setDataCadastro(LocalDateTime.now());  // Definindo a data de cadastro

        return usuarioRepository.save(usuario);  // Salvando no usuarioRepository
    }

    // Método para atualizar as informações de um usuário
    public Usuario update(Long id, Usuario usuarioDetails) {
        // Verifica se o usuário existe
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        // Atualizando os dados do usuário
        usuario.setNome(usuarioDetails.getNome());
        usuario.setEmail(usuarioDetails.getEmail());

        // Se a senha foi modificada, ela será re-codificada
        if (!passwordEncoder.matches(usuarioDetails.getSenha(), usuario.getSenha())) {
            usuario.setSenha(passwordEncoder.encode(usuarioDetails.getSenha()));
        }

        usuario.setStatus(usuarioDetails.getStatus());
        usuario.setDataCadastro(usuarioDetails.getDataCadastro());

        return usuarioRepository.save(usuario);  // Salvando as alterações no usuarioRepository
    }

    // Método para excluir um usuário pelo ID
    public void deleteById(Long id) {
        // Verifica se o usuário existe antes de excluir
        usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        usuarioRepository.deleteById(id);  // Excluindo o usuário no usuarioRepository
    }

    // Método para buscar usuário por nome (com filtro)
    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);  // Usando o usuarioRepository corretamente
    }


    // Método para buscar usuário por nome ou email (IA - chatbot)
    public Usuario findUsuario(String nome, String email) {
        log.info("Buscando usuário com nome: {} ou email: {}", nome, email);

        if (nome != null && !nome.isEmpty()) {
            return usuarioRepository.findByNome(nome)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o nome: " + nome));
        }

        if (email != null && !email.isEmpty()) {
            return usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o email: " + email));
        }

        throw new IllegalArgumentException("Nome e email não podem ser ambos nulos ou vazios");
    }


    // Método para cadastrar usuário (IA - chatbot)
    public Usuario cadastrarUsuario(String nome, String senha) {
        log.info("Cadastrando usuário com nome: {}", nome);

        // Construção do objeto Usuario utilizando o padrão Builder
        Usuario novoUsuario = Usuario.builder()
                .nome(nome)
                .senha(passwordEncoder.encode(senha)) // Criptografando a senha
                .dataCadastro(LocalDateTime.now())    // Definindo a data de cadastro
                .status('A')                          // Status inicial como ativo ('A')
                .build();

        // Salvando o usuário no repositório
        return usuarioRepository.save(novoUsuario);
    }





}
