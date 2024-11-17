package fiap.com.br.gs.java.painel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_EficienciaPainel")
public class EficienciaPainel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "idPainelSolar", nullable = false)
    PainelSolar painelSolar;

    @Column(nullable = false, precision = 5, scale = 2)
    Double eficiencia;

    @Column(nullable = false)
    Date dataCalculo;


}
