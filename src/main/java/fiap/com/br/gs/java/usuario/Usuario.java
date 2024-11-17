package fiap.com.br.gs.java.usuario;

import fiap.com.br.gs.java.painel.PainelSolar;
import fiap.com.br.gs.java.usuario.tipo.TipoUsuarioCliente;
import fiap.com.br.gs.java.validation.usuario.status.TipoStatusUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@Builder
@Cacheable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String nome;

    @Column(nullable = false, length = 100)
    @Email
    String email;

    @Column(nullable = false)
    String senha;

    @Column(name = "DataCadastro")
    LocalDateTime dataCadastro;

    @Column(nullable = false)
    @TipoStatusUsuario
    char status;

    @OneToMany(mappedBy = "usuario")
    List<PainelSolar> paineis = new ArrayList<>();  // Inicializado para evitar NullPointerException

    @ManyToMany
    @JoinTable(
            name = "T_SOSE_Usuario_TipoUsuario",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idTipoUsuario")
    )
    List<TipoUsuarioCliente> tipoUsuarioClientes = new ArrayList<>();  // Inicializado para evitar NullPointerException

}
