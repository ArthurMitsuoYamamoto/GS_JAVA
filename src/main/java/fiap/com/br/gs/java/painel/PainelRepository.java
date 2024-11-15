package fiap.com.br.gs.java.painel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PainelRepository extends JpaRepository<PainelSolar, Long> {

}
