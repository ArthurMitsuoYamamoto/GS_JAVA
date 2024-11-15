package fiap.com.br.gs_java.painel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "T_SOSE_Sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPainelSolar", nullable = false)
    private PainelSolar painelSolar;

    @ManyToOne
    @JoinColumn(name = "idTipoSensor", nullable = false)
    private TipoSensor tipoSensor;

    @ManyToOne
    @JoinColumn(name = "idUnidadeMedida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @OneToMany(mappedBy = "sensor")
    private List<LeituraSensor> leituras;
}