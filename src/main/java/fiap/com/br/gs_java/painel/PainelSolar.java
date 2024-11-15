package fiap.com.br.gs_java.painel;


import fiap.com.br.gs_java.user.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "T_SOSE_PainelSolar")
public class PainelSolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private char status;

    @Column(nullable = false, length = 100)
    private String localizacao;

    @Column(nullable = false)
    private Double capacidadeProducao;

    @OneToMany(mappedBy = "painelSolar")
    private List<EficienciaPainel> eficiencias;

    @OneToMany(mappedBy = "painelSolar")
    private List<Sensor> sensores;

    @OneToMany(mappedBy = "painelSolar")
    private List<RelatorioDesempenho> relatorios;

}
