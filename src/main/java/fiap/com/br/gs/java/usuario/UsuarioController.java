package fiap.com.br.gs.java.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("usuarios")
@CacheConfig(cacheNames = "usuarios")
@Slf4j
@Tag(name = "Usuários", description = "Endpoints para gerenciar os usuários.")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // Injeção do serviço

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Listar todos os usuários",
            description = "Retorna uma lista contendo todos os usuários cadastrados no sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso.")
            }
    )
    public List<Usuario> getAllUsuarios() {
        log.info("Listando todos os usuários");
        return usuarioService.findAll();  // Usando o serviço
    }

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Listar todos os usuários ou filtrar por nome",
            description = "Retorna uma lista de todos os usuários cadastrados ou filtra os usuários pelo nome, caso o parâmetro 'nome' seja fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro nos parâmetros de entrada.")
            }
    )
    public List<Usuario> findAll(@RequestParam(required = false) String nome) {
        log.info("Listando usuários, filtro nome: {}", nome);
        if (nome != null) {
            return usuarioService.findByNome(nome);  // Usando o serviço
        }
        return usuarioService.findAll();  // Usando o serviço
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os detalhes de um usuário específico com base no ID fornecido."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
            }
    )
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        log.info("Buscando usuário com id {}", id);
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());  // Usando o serviço
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
            summary = "Criar um novo usuário",
            description = "Adiciona um novo usuário ao sistema."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos.")
            }
    )
    public Usuario createUsuario(@RequestBody @Valid Usuario usuario) {
        log.info("Cadastrando novo usuário: {}", usuario);
        return usuarioService.save(usuario);  // Usando o serviço
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza as informações de um usuário específico pelo ID."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
            }
    )
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        log.info("Atualizando usuário com id {} para {}", id, usuario);
        verificarSeUsuarioExiste(id);
        usuario.setId(id);
        return usuarioService.update(id, usuario);  // Usando o serviço
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário do sistema pelo ID fornecido."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
            }
    )
    public void deleteUsuario(@PathVariable Long id) {
        log.info("Apagando usuário com id {}", id);
        verificarSeUsuarioExiste(id);
        usuarioService.deleteById(id);  // Usando o serviço
    }

    private void verificarSeUsuarioExiste(Long id) {
        usuarioService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Não existe usuário com o ID informado."
                ));
    }
}
