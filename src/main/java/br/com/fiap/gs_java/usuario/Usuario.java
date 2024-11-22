package br.com.fiap.gs_java.usuario;

import br.com.fiap.gs_java.painel.PainelSolar;
import br.com.fiap.gs_java.usuario.tipo.TipoUsuario;
import br.com.fiap.gs_java.validation.usuario.status.TipoStatusUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Cacheable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_SOSE_Usuario")
public class Usuario {
    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "{nome.notBlank}")
    @Size(max = 100, message = "{nome.size}")
    String nome;

    @Column(nullable = false, length = 100, unique = true)
    @Email(message = "{email.email}")
    @NotBlank(message = "{email.notBlank}")
    String email;

    @Column(nullable = false)
    @NotBlank(message = "{senha.notBlank}")
    String senha;


    @Column(name = "DataCadastro")
    @NotBlank(message = "{data.notBlank}")
    LocalDateTime dataCadastro;

    @Column(nullable = false)
    @NotBlank(message = "{status.notBlank}")
    @TipoStatusUsuario
    char status;

    @OneToMany(mappedBy = "usuario")
    List<PainelSolar> paineis = new ArrayList<>();  // Inicializado para evitar NullPointerException

    @ManyToMany
    @JoinTable(
            name = "T_SOSE_Usuario_TipoUsuario",
            joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "idTipoUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    )
    List<TipoUsuario> tipoUsuarios = new ArrayList<>();  // Inicializado para evitar NullPointerException


}


