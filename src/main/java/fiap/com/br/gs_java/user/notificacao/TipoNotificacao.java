package fiap.com.br.gs_java.user.notificacao;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "T_SOSE_TipoNotificacao")
@Data
public class TipoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String descricao;


}
