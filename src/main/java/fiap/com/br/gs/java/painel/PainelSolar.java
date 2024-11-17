package fiap.com.br.gs.java.painel;

import fiap.com.br.gs.java.painel.sensor.Sensor;
import fiap.com.br.gs.java.usuario.Usuario;
import fiap.com.br.gs.java.validation.painel.StatusPainel;
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
@Table(name = "T_SOSE_PainelSolar")
public class PainelSolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @NotNull(message = "{painelSolar.usuario.notNull}")
    Usuario usuario;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "{painelSolar.nome.notBlank}")
    @Size(max = 100, message = "{painelSolar.nome.size}")
    String nome;

    @Column(nullable = false)
    @StatusPainel
    char status;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "{painelSolar.localizacao.notBlank}")
    @Size(max = 100, message = "{painelSolar.localizacao.size}")
    String localizacao;

    @Column(nullable = false)
    @NotNull(message = "{painelSolar.capacidadeProducao.notNull}")
    Double capacidadeProducao;

    @OneToMany(mappedBy = "painelSolar")
    List<EficienciaPainel> eficiencias;

    @OneToMany(mappedBy = "painelSolar")
    List<Sensor> sensores;

    @OneToMany(mappedBy = "painelSolar")
    List<RelatorioDesempenho> relatorios;
}
