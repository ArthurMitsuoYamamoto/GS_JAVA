package br.com.fiap.gs_java.usuario;

import br.com.fiap.gs_java.painel.PainelSolar;
import br.com.fiap.gs_java.usuario.tipo.TipoUsuario;
import br.com.fiap.gs_java.validation.usuario.status.TipoStatusUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    String nome;

    @Column(nullable = false, length = 100, unique = true)
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
            joinColumns = @JoinColumn(name = "idUsuario", referencedColumnName = "id", insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "idTipoUsuario", referencedColumnName = "id", insertable = false, updatable = false)
    )
    List<TipoUsuario> tipoUsuarios = new ArrayList<>();  // Inicializado para evitar NullPointerException


}


