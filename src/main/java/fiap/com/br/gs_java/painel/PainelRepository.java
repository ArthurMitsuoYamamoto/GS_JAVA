package fiap.com.br.gs_java.painel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PainelRepository extends JpaRepository<PainelSolar, Long> {
    
}
