package fiap.com.br.gs.java.painel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PainelRepository extends JpaRepository<PainelSolar, Long> {

    Optional<Object> findByNome(String nome);
}
