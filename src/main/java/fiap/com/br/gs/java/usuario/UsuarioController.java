package fiap.com.br.gs.java.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/SolarSense/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciar os usuários.")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(
            summary = "Listar todos os usuários",
            description = "Retorna uma lista contendo todos os usuários cadastrados no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso.")
            }
    )
    public List<Usuario>  getAllUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os usuários ou filtrar por nome",
            description = "Retorna uma lista de todos os usuários cadastrados no sistema ou filtra os usuários pelo nome, caso o parâmetro 'nome' seja fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro nos parâmetros de entrada.")
            }
    )
    public List<Usuario> findAll(@RequestParam(required = false) String nome) {
        if (nome != null) {
            return usuarioService.findByNome(nome);
        }
        return usuarioService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os detalhes de um usuário específico com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
            }
    )
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @PostMapping
    @Operation(
            summary = "Criar um novo usuário",
            description = "Adiciona um novo usuário ao sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos.")
            }
    )
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        return ResponseEntity.status(CREATED).body(novoUsuario);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza as informações de um usuário específico pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
            }
    )
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
        try {
            Usuario usuarioAtualizado = usuarioService.update(id, usuarioDetails);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário do sistema pelo ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
            }
    )
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
