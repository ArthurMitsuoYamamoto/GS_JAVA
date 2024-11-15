package fiap.com.br.gs.java.user.notificacao;

import fiap.com.br.gs.java.user.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Cacheable
@Table(name = "T_SOSE_Notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "CLOB")
    private String mensagem;

    @Temporal(TemporalType.DATE)
    private Date dataEnvio;

    @ManyToOne
    @JoinColumn(name = "idTipoNotificacao", referencedColumnName = "id")
    private TipoNotificacao tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario usuario;
}
