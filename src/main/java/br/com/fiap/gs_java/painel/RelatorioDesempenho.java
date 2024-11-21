package br.com.fiap.gs_java.painel;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_RelatorioDesempenho")
public class RelatorioDesempenho {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "IdPainelSolar", nullable = false)
    @NotNull(message = "{relatorioDesempenho.painelSolar.notNull}")
    PainelSolar painelSolar;

    @Column(nullable = false)
    @NotNull(message = "{relatorioDesempenho.eficienciaMedia.notNull}")
    Double eficienciaMedia;

    @Column(nullable = false)
    @NotNull(message = "{relatorioDesempenho.horasProducao.notNull}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{relatorioDesempenho.horasProducao.min}")
    Double horasProducao;

    @Column(length = 250)
    @Size(max = 250, message = "{relatorioDesempenho.analiseProblemas.size}")
    String analiseProblemas;
}
