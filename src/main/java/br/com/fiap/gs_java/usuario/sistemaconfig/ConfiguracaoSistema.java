package br.com.fiap.gs_java.usuario.sistemaconfig;

import br.com.fiap.gs_java.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "{configuracaoSistema.usuario.notNull}")
    Usuario usuario;

    @Column(name = "LimiteEficiencia", nullable = false)
    @NotNull(message = "{configuracaoSistema.limiteEficiencia.notNull}")
    @Min(value = 0, message = "{configuracaoSistema.limiteEficiencia.min}")
    Integer limiteEficiencia;

    @Column(name = "FrequenciaAtualizacao", nullable = false)
    @NotNull(message = "{configuracaoSistema.frequenciaAtualizacao.notNull}")
    @Min(value = 1, message = "{configuracaoSistema.frequenciaAtualizacao.min}")
    Integer frequenciaAtualizacao;
}
