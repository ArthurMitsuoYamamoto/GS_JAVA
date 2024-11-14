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
    @Column(name = "LimiteEficiencia", nullable = false)
    private Integer limiteEficiencia;



    @Column(name = "FrequenciaAtualizacao", nullable = false)
    private Integer frequenciaAtualizacao;

}
