package fiap.com.br.gs.java.painel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PainelService {

    @Autowired
    private PainelRepository painelRepository;

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
}
