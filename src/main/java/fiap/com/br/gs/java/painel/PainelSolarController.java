package fiap.com.br.gs.java.painel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SolarSense/paineis")
@Tag(name = "Painéis Solares", description = "Endpoints para gerenciar os painéis solares.")
public class PainelSolarController {

    @Autowired
    private PainelService painelSolarService;

    @GetMapping
    @Operation(
            summary = "Listar todos os painéis solares",
            description = "Retorna uma lista contendo todos os painéis solares cadastrados no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de painéis solares retornada com sucesso.")
            }
    )
    public List<PainelSolar> getAllPaineis() {
        return painelSolarService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar painel solar por ID",
            description = "Retorna os detalhes de um painel solar específico com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Painel solar encontrado."),
                    @ApiResponse(responseCode = "404", description = "Painel solar não encontrado.")
            }
    )
    public ResponseEntity<PainelSolar> getPainelById(@PathVariable Long id) {
        return painelSolarService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    @PostMapping
    @Operation(
            summary = "Criar um novo painel solar",
            description = "Adiciona um novo painel solar ao sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Painel solar criado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Erro na validação dos dados fornecidos.")
            }
    )
    public ResponseEntity<PainelSolar> createPainel(@RequestBody PainelSolar painelSolar) {
        PainelSolar novoPainel = painelSolarService.save(painelSolar);
        return ResponseEntity.status(CREATED).body(novoPainel);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar painel solar",
            description = "Atualiza as informações de um painel solar específico pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Painel solar atualizado com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Painel solar não encontrado.")
            }
    )
    public ResponseEntity<PainelSolar> updatePainel(@PathVariable Long id, @RequestBody PainelSolar painelDetails) {
        try {
            PainelSolar painelAtualizado = painelSolarService.update(id, painelDetails);
            return ResponseEntity.ok(painelAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir painel solar",
            description = "Remove um painel solar do sistema pelo ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Painel solar excluído com sucesso."),
                    @ApiResponse(responseCode = "404", description = "Painel solar não encontrado.")
            }
    )
    public ResponseEntity<Void> deletePainel(@PathVariable Long id) {
        painelSolarService.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
