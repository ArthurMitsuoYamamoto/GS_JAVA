package fiap.com.br.gs.java.painel.sensor;

import fiap.com.br.gs.java.painel.PainelSolar;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_Sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idPainelSolar", nullable = false)
    @NotNull(message = "{sensor.painelSolar.notNull}")
    PainelSolar painelSolar;

    @ManyToOne
    @JoinColumn(name = "idTipoSensor", nullable = false)
    @NotNull(message = "{sensor.tipoSensor.notNull}")
    TipoSensor tipoSensor;

    @ManyToOne
    @JoinColumn(name = "idUnidadeMedida", nullable = false)
    @NotNull(message = "{sensor.unidadeMedida.notNull}")
    UnidadeMedida unidadeMedida;

    @OneToMany(mappedBy = "sensor")
    List<LeituraSensor> leituras;
}
