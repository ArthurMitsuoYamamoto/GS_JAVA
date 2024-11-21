package br.com.fiap.gs_java.painel;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_EficienciaPainel")
public class EficienciaPainel {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idPainelSolar", nullable = false)
    @NotNull(message = "{eficienciaPainel.painelSolar.notNull}")
    PainelSolar painelSolar;

    @Column(nullable = false)
    @NotNull(message = "{eficienciaPainel.eficiencia.notNull}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{eficienciaPainel.eficiencia.min}")
    Double eficiencia;

    @Column(nullable = false)
    @NotNull(message = "{eficienciaPainel.dataCalculo.notNull}")
    @Temporal(TemporalType.TIMESTAMP)
    Date dataCalculo;
}
