package fiap.com.br.gs.java.usuario.notificacao;

import fiap.com.br.gs.java.validation.usuario.TipoUsuario;
import fiap.com.br.gs.java.validation.usuario.notificacao.TipoNotificacaoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Cacheable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_TipoNotificacao")
public class TipoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "{tipoNotificacao.tipoUsuario.notBlank}")
    @TipoNotificacaoUsuario
    String tipoUsuario;


    @Column(nullable = false, length = 50)
    @NotBlank(message = "{tipoNotificacao.descricao.notBlank}")
    @Size(max = 50, message = "{tipoNotificacao.descricao.size}")
    String descricao;
}
