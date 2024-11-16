package fiap.com.br.gs.java.usuario.sistemaconfig;

import fiap.com.br.gs.java.usuario.Usuario;
import jakarta.persistence.*;
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
