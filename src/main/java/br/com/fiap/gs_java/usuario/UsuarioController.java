package br.com.fiap.gs_java.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("usuarios")
@CacheConfig(cacheNames = "usuarios")
@Slf4j
@RequiredArgsConstructor  // Usando o Lombok para injeção de dependências
@Tag(name = "Usuários", description = "Endpoints para gerenciar os usuários.")
public class UsuarioController {

    private final UsuarioService usuarioService; // Usando o construtor do Lombok para injeção

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Listar todos os usuários ou filtrar por nome",
            description = "Retorna uma lista de todos os usuários cadastrados ou filtra os usuários pelo nome, caso o parâmetro 'nome' seja fornecido."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro nos parâmetros de entrada.")
            }
    )
    public List<Usuario> getAllUsuarios(@RequestParam(required = false) String nome) {
        log.debug("Listando usuários com filtro: {}", nome);
        if (nome != null) {
            return usuarioService.findByNome(nome);  // Usando o serviço para filtrar por nome
        }
        return usuarioService.findAll();  // Retorna todos os usuários
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
        log.debug("Buscando usuário com id {}", id);
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());  // Caso não encontre o usuário
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)  // Evita que o cache com usuários antigos seja usado
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
        log.debug("Cadastrando novo usuário: {}", usuario);
        return usuarioService.save(usuario);  // Chama o serviço para salvar o novo usuário
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)  // Evita que o cache com usuários antigos seja usado
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
        log.debug("Atualizando usuário com id {} para {}", id, usuario);
        verificarSeUsuarioExiste(id);  // Verifica se o usuário existe antes de atualizar
        usuario.setId(id);
        return usuarioService.update(id, usuario);  // Chama o serviço para atualizar o usuário
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)  // Evita que o cache com usuários antigos seja usado
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
        log.debug("Apagando usuário com id {}", id);
        verificarSeUsuarioExiste(id);  // Verifica se o usuário existe antes de excluir
        usuarioService.deleteById(id);  // Chama o serviço para excluir o usuário
    }

    private void verificarSeUsuarioExiste(Long id) {
        usuarioService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Não existe usuário com o ID informado."
                ));
    }
}
