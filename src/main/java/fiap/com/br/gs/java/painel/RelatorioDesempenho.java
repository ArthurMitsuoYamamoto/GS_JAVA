package fiap.com.br.gs.java.painel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@Table(name = "T_SOSE_RelatorioDesempenho")
public class RelatorioDesempenho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idPainelSolar", nullable = false)
     PainelSolar painelSolar;

    @Column(nullable = false, precision = 5, scale = 2)
    Double eficienciaMedia;

    @Column(nullable = false)
    Double horasProducao;

    @Column(length = 250)
    String analiseProblemas;


}
