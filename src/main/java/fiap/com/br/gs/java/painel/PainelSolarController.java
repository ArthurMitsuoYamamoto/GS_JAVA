package fiap.com.br.gs.java.painel;

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

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CacheConfig(cacheNames = "paineis")
@RequestMapping("paineis")
@Slf4j
@Tag(name = "Painéis Solares", description = "Endpoints para gerenciar os painéis solares.")
public class PainelSolarController {

    @Autowired
    private PainelService painelService;


    @GetMapping
    @Cacheable
    @Operation(
            summary = "Listar todos os painéis solares",
            description = "Retorna uma lista contendo todos os painéis solares cadastrados no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de painéis solares retornada com sucesso.")
            }
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Lista de painéis solares retornada com sucesso.")
            }
    )
    public List<PainelSolar> getAllPaineis() {
        return painelService.findAll();

    }


    @GetMapping("{id}")
    @Operation(
            summary = "Buscar painel solar por ID",
            description = "Retorna os detalhes de um painel solar específico com base no ID fornecido."
    )

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Painel solar encontrado."),
                    @ApiResponse(responseCode = "404", description = "Painel solar não encontrado.", useReturnTypeSchema = false)
            }
    )
    public ResponseEntity<PainelSolar> show(@PathVariable Long id) {
        log.info("buscando painel solar o com id {}", id);

        return painelService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }


    @PostMapping
    @ResponseStatus(CREATED)
    @CacheEvict(allEntries = true)
    @Operation(
            summary = "Criar um novo painel solar",
            description = "Adiciona um novo painel solar ao sistema."

    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",description = "Painel solar criado com sucesso."),
                    @ApiResponse(responseCode = "400",description = "Erro na validação dos dados fornecidos.", useReturnTypeSchema = false)
            }
    )

    public PainelSolar createPainel(@RequestBody @Valid PainelSolar painelSolar) {
        log.info("Cadastrando painel solar {}", painelSolar);
        return painelService.save(painelSolar);
    }




    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    @Operation(
            summary = "Atualizar painel solar",
            description = "Atualiza as informações de um painel solar específico pelo ID."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Painel solar atualizado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Painel solar não encontrado.", useReturnTypeSchema = false)
            }
    )
    public PainelSolar updatePainel(@PathVariable Long id, @RequestBody PainelSolar painelSolar) {
        log.info("atualizar painel solar {} para {}", id, painelSolar);

        verificarSePainelExiste(id);
        painelSolar.setId(id);
        return painelService.update(id, painelSolar);
    }

    private void  verificarSePainelExiste(Long id) {
        painelService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Não existe painel com o id informado"));
    }




    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(
            summary = "Excluir painel solar",
            description = "Remove um painel solar do sistema pelo ID fornecido."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Painel solar excluído com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Painel solar não encontrado.", useReturnTypeSchema = false)
            }
    )

    public void deletePainel(@PathVariable Long id) {
        log.info("apagando painel {}", id);
        verificarSePainelExiste(id);
        painelService.deleteById(id);
    }
}
