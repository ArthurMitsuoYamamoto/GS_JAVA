package fiap.com.br.gs.java.painel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "T_SOSE_EficienciaPainel")
public class EficienciaPainel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPainelSolar", nullable = false)
    private PainelSolar painelSolar;

    @Column(nullable = false, precision = 5, scale = 2)
    private Double eficiencia;

    @Column(nullable = false)
    private Date dataCalculo;


}
