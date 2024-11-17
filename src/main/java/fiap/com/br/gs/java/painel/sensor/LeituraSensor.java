package fiap.com.br.gs.java.painel.sensor;

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
@Table(name = "T_SOSE_LeituraSensor")
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotNull(message = "{leituraSensor.dataLeitura.notNull}")
    @Temporal(TemporalType.TIMESTAMP)
    Date dataLeitura;

    @Column(nullable = false)
    @NotNull(message = "{leituraSensor.valorLeitura.notNull}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{leituraSensor.valorLeitura.min}")
    Double valorLeitura;

    @Column(length = 200)
    @Size(max = 200, message = "{leituraSensor.descricao.size}")
    String descricao;
}
