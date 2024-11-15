package fiap.com.br.gs.java.painel.sensor;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "T_SOSE_LeituraSensor")
public class LeituraSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataLeitura;

    @Column(nullable = false)
    private Double valorLeitura;

    @Column(length = 200)
    private String descricao;


}
