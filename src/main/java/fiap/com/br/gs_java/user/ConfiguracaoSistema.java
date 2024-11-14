package fiap.com.br.gs_java.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@Table(name = "T_SOSE_ConfiguracaoSistema")
public class ConfiguracaoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @NotNull(message = "{limiteEficiencia.notNull}")
    @Min(value = 0, message = "{limiteEficiencia.min}")
    @Column(name = "LimiteEficiencia", nullable = false)
    private Integer limiteConsistencia;


    @Min(value = 1, message = "{frequenciaAtualizacao.min}")
    @Column(name = "FrequenciaAtualizacao", nullable = false)
    private Integer frequenciaAtualizacao;

    @NotNull(message = "{avisosAtivados.notNull}")
    @Column(name = "AvisosAtivados", nullable = false)
    private Boolean avisosAtivados;
}
