package br.com.fiap.gs_java.usuario.tipo;

import br.com.fiap.gs_java.usuario.Usuario;
import br.com.fiap.gs_java.usuario.notificacao.Notificacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_TipoUsuario")
public class TipoUsuario {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "{tipoUsuario.tipoUsuario.notBlank}")
    @br.com.fiap.gs_java.validation.usuario.TipoUsuario
    String tipoUsuario;

    @Column(nullable = false, length = 50)
    private String descricao;

    // Relacionamento muitos-para-muitos com Usuario
    @ManyToMany(mappedBy = "tipoUsuarios")
    private List<Usuario> usuarios;

    // Relacionamento um-para-muitos com Notificacao
    @OneToMany(mappedBy = "id")
    private List<Notificacao> notificacoes;
}