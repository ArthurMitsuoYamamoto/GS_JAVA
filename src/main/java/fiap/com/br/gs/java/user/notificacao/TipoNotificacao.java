package fiap.com.br.gs.java.user.notificacao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Cacheable
@Table(name = "T_SOSE_TipoNotificacao")
public class TipoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String descricao;


}
