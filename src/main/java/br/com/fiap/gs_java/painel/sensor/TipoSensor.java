package br.com.fiap.gs_java.painel.sensor;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Cacheable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_TipoSensor")
public class TipoSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "{tipoSensor.descricao.notBlank}")
    @Size(max = 100, message = "{tipoSensor.descricao.size}")
    private String descricao;
}
