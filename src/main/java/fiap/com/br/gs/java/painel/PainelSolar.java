package fiap.com.br.gs.java.painel;

import fiap.com.br.gs.java.painel.sensor.Sensor;
import fiap.com.br.gs.java.usuario.Usuario;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @NotNull(message = "{painelSolar.usuario.notNull}")
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "{painelSolar.nome.notBlank}")
    @Size(max = 100, message = "{painelSolar.nome.size}")
    private String nome;

    @Column(nullable = false)
    @Pattern(regexp = "A|I", message = "{painelSolar.status.invalid}") // A (Ativo) ou I (Inativo)
    private char status;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "{painelSolar.localizacao.notBlank}")
    @Size(max = 100, message = "{painelSolar.localizacao.size}")
    private String localizacao;

    @Column(nullable = false)
    @NotNull(message = "{painelSolar.capacidadeProducao.notNull}")
    private Double capacidadeProducao;

    @OneToMany(mappedBy = "painelSolar")
    private List<EficienciaPainel> eficiencias;

    @OneToMany(mappedBy = "painelSolar")
    private List<Sensor> sensores;

    @OneToMany(mappedBy = "painelSolar")
    private List<RelatorioDesempenho> relatorios;
}
