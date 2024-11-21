package br.com.fiap.gs_java.auth;

import br.com.fiap.gs_java.usuario.Usuario;
import br.com.fiap.gs_java.usuario.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor

public class TokenService {

    private final UsuarioRepository usuarioRepository;  // Correção: Remover a variável redundante Usuario Repository
    private final Algorithm algorithm;

    //public TokenService(UsuarioRepository usuarioRepository, @Value("${jwt.secret}") String secret) {
      //  this.usuarioRepository = usuarioRepository;
       // this.algorithm = Algorithm.HMAC256(secret);
   // }

    public Token createToken(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        String token = JWT.create()
                .withSubject(credentials.email())
                .withIssuer("SolarSense")
                .withExpiresAt(expiresAt)
                .withClaim("role", "ADMIN")
                .sign(algorithm);

        return new Token(token, credentials.email());
    }

    public Usuario getUserFromToken(String token) {
        var email = JWT.require(algorithm)
                .withIssuer("SolarSense")
                .build()
                .verify(token)
                .getSubject();

        return usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }
}
