package fiap.com.br.gs.java.painel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SolarSense/paineis")
public class PainelSolarController {

    @Autowired
    private PainelService painelSolarService;

    // Listar todos os painéis solares
    @GetMapping
    public List<PainelSolar> getAllPaineis() {
        return painelSolarService.findAll();
    }

    // Buscar painel solar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PainelSolar> getPainelById(@PathVariable Long id) {
        return painelSolarService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).build());
    }

    // Criar novo painel solar
    @PostMapping
    public ResponseEntity<PainelSolar> createPainel(@RequestBody PainelSolar painelSolar) {
        PainelSolar novoPainel = painelSolarService.save(painelSolar);
        return ResponseEntity.status(CREATED).body(novoPainel);
    }

    // Atualizar painel solar por ID
    @PutMapping("/{id}")
    public ResponseEntity<PainelSolar> updatePainel(@PathVariable Long id, @RequestBody PainelSolar painelDetails) {
        try {
            PainelSolar painelAtualizado = painelSolarService.update(id, painelDetails);
            return ResponseEntity.ok(painelAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(NOT_FOUND).build();
        }
    }

    // Excluir painel solar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePainel(@PathVariable Long id) {
        painelSolarService.deleteById(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}