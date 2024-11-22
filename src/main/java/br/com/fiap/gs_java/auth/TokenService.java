package br.com.fiap.gs_java.auth;

import br.com.fiap.gs_java.usuario.Usuario;
import br.com.fiap.gs_java.usuario.UsuarioRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public Algorithm ALGORITHM;
    private final UsuarioRepository usuarioRepository;

    public TokenService(UsuarioRepository usuarioRepository, @Value("${jwt.secret}") String secret) {
      this.usuarioRepository = usuarioRepository;
     this.ALGORITHM = Algorithm.HMAC256(secret);
     }

    public Token createToken(Credentials credentials) {
        var expiresAt = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));

        String token = JWT.create()
                .withSubject(credentials.email())
                .withIssuer("SolarSense")  // Certifique-se de que o issuer seja consistente
                .withClaim("role", "ADMIN")
                .withExpiresAt(expiresAt)
                .sign(ALGORITHM);

        return new Token(token, credentials.email());
    }

    public Usuario getUserFromToken(String token) {
        var email = JWT.require(ALGORITHM)
                .withIssuer("SolarSense")  // Consistência no issuer
                .build()
                .verify(token)
                .getSubject();

        return usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }


}
