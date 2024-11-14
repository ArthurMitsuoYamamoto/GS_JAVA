package fiap.com.br.gs_java.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "T_SOSE_Notificacao")
@Data
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
