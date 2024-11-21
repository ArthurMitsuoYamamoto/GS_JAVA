package br.com.fiap.gs_java.painel;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PainelService {

    private final PainelRepository painelRepository;

    public List<PainelSolar> findAll() {
        return painelRepository.findAll();
    }

    public Optional<PainelSolar> findById(Long id) {
        return painelRepository.findById(id);
    }

    @Transactional
    public PainelSolar save(PainelSolar painelSolar) {
        return painelRepository.save(painelSolar);
    }

    @Transactional
    public PainelSolar update(Long id, PainelSolar painelDetails) {
        PainelSolar painelSolar = painelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Painel Solar não encontrado com ID: " + id));

        // Atualizando os dados do painel
        painelSolar.setNome(painelDetails.getNome());
        painelSolar.setLocalizacao(painelDetails.getLocalizacao());
        painelSolar.setStatus(painelDetails.getStatus());
        painelSolar.setCapacidadeProducao(painelDetails.getCapacidadeProducao());
        painelSolar.setUsuario(painelDetails.getUsuario());

        // Salvando as alterações
        return painelRepository.save(painelSolar);
    }

    public void deleteById(Long id) {
        painelRepository.deleteById(id);
    }

    // Método para buscar painel solar por nome
    public PainelSolar findPainel(String nome) {
        return (PainelSolar) painelRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Painel Solar não encontrado com o nome: " + nome));
    }

    // Método para cadastrar painel solar
    public PainelSolar cadastrarPainelSolar(String nome) {
        log.info("Cadastrando painel solar com nome: {}", nome);

        // Construção do objeto PainelSolar utilizando o padrão Builder
        PainelSolar novoPainelSolar = PainelSolar.builder()
                .nome(nome)                      // Nome fornecido
                .build();

        // Salvando o painel no repositório
        return painelRepository.save(novoPainelSolar);
    }
}
