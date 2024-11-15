package fiap.com.br.gs_java.painel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PainelSolarService {

    @Autowired
    private PainelSolarRepository painelSolarRepository;

    public List<PainelSolar> findAll() {
        return painelSolarRepository.findAll();
    }

    public Optional<PainelSolar> findById(Long id) {
        return painelSolarRepository.findById(id);
    }

    public PainelSolar save(PainelSolar painelSolar) {
        return painelSolarRepository.save(painelSolar);
    }

    public PainelSolar update(Long id, PainelSolar painelDetails) {
        PainelSolar painelSolar = painelSolarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Painel Solar não encontrado com ID: " + id));

        painelSolar.setNome(painelDetails.getNome());
        painelSolar.setLocalizacao(painelDetails.getLocalizacao());
        painelSolar.setStatus(painelDetails.getStatus());
        painelSolar.setCapacidadeProducao(painelDetails.getCapacidadeProducao());

        return painelSolarRepository.save(painelSolar);
    }

    public void deleteById(Long id) {
        painelSolarRepository.deleteById(id);
    }
}