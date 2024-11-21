package br.com.fiap.gs_java.painel.sensor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_LeituraSensor")
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotNull(message = "{leituraSensor.dataLeitura.notNull}")
    @Temporal(TemporalType.TIMESTAMP)
    LocalDateTime dataLeitura;

    @Column(nullable = false)
    @NotNull(message = "{leituraSensor.valorLeitura.notNull}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{leituraSensor.valorLeitura.min}")
    BigDecimal valorLeitura;

    @Column(length = 200)
    @Size(max = 200, message = "{leituraSensor.descricao.size}")
    String descricao;


}
