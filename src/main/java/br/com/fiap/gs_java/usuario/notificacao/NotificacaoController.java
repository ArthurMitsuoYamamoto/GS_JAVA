package br.com.fiap.gs_java.usuario.notificacao;

import br.com.fiap.gs_java.usuario.Usuario;
import br.com.fiap.gs_java.usuario.notificacao.dto.NotificacaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "notificacoes")
@RequestMapping("notificacoes")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Notificações", description = "Endpoints para gerenciar notificações dos usuários.")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @PostMapping
    @Operation(
            summary = "Criar uma nova notificação",
            description = "Adiciona uma nova notificação ao sistema com a mensagem, tipo e usuário especificados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificação criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos."),
            @ApiResponse(responseCode = "404", description = "Tipo de notificação ou usuário não encontrado.")
    })
    public ResponseEntity<NotificacaoResponse> criarNotificacao(
            @RequestParam String mensagem,
            @RequestParam Long tipoNotificacaoId,
            @RequestParam Usuario usuario
    ) {
        log.info("Iniciando criação de notificação para o usuário: {}, tipo de notificação: {}, mensagem: {}", usuario.getId(), tipoNotificacaoId, mensagem);

        try {
            NotificacaoResponse notificacao = notificacaoService.criarNotificacao(mensagem, tipoNotificacaoId, usuario);
            log.info("Notificação criada com sucesso para o usuário: {}", usuario.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(notificacao);
        } catch (Exception e) {
            log.error("Erro ao criar notificação para o usuário: {}. Erro: {}", usuario.getId(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("usuario/{idUsuario}")
    @Operation(
            summary = "Listar notificações por usuário",
            description = "Retorna uma lista de notificações para um usuário específico, identificadas pelo ID do usuário."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificações retornada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public ResponseEntity<List<NotificacaoResponse>> listarNotificacoesPorUsuario(@PathVariable Long idUsuario) {
        log.info("Listando notificações para o usuário com ID: {}", idUsuario);

        try {
            List<NotificacaoResponse> notificacoes = notificacaoService.listarNotificacoesPorUsuario(idUsuario);
            if (notificacoes.isEmpty()) {
                log.info("Nenhuma notificação encontrada para o usuário com ID: {}", idUsuario);
            } else {
                log.info("Encontradas {} notificações para o usuário com ID: {}", notificacoes.size(), idUsuario);
            }
            return ResponseEntity.ok(notificacoes);
        } catch (Exception e) {
            log.error("Erro ao listar notificações para o usuário com ID: {}. Erro: {}", idUsuario, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
