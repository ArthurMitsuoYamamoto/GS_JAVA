package br.com.fiap.gs_java.usuario.notificacao;

import br.com.fiap.gs_java.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@Builder
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_Notificacao")
public class Notificacao {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "CLOB")
    @NotBlank(message = "{notificacao.mensagem.notBlank}")
    String mensagem;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "{notificacao.dataEnvio.notNull}")
    Date dataEnvio;

    @ManyToOne
    @JoinColumn(name = "IdTipoNotificacao", referencedColumnName = "id")
    @NotNull(message = "{notificacao.tipoNotificacao.notNull}")

    TipoNotificacao tipoNotificacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @NotNull(message = "{notificacao.usuario.notNull}")
    Usuario usuario;
}
