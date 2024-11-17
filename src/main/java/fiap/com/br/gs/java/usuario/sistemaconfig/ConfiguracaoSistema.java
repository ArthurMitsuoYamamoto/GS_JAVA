package fiap.com.br.gs.java.usuario.sistemaconfig;

import fiap.com.br.gs.java.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_ConfiguracaoSistema")
public class ConfiguracaoSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", nullable = false)
    Usuario usuario;

    @Column(name = "LimiteEficiencia", nullable = false)
    Integer limiteEficiencia;



    @Column(name = "FrequenciaAtualizacao", nullable = false)
    Integer frequenciaAtualizacao;

}
