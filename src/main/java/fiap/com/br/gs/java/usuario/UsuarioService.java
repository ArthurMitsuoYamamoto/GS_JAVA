package fiap.com.br.gs.java.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    // Método para buscar usuários por nome (com filtro)
    public List<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNomeContainingIgnoreCase(nome);  // Usando o usuarioRepository corretamente
    }
}
