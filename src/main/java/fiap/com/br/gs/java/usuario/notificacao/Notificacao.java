package fiap.com.br.gs.java.usuario.notificacao;

import fiap.com.br.gs.java.usuario.Usuario;
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
@Table(name = "T_SOSE_Notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "CLOB")
    String mensagem;

    @Temporal(TemporalType.DATE)
   Date dataEnvio;

    @ManyToOne
    @JoinColumn(name = "idTipoNotificacao", referencedColumnName = "id")
     TipoNotificacao tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    Usuario usuario;
}
