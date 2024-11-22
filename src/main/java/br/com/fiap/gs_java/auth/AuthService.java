package br.com.fiap.gs_java.auth;

import br.com.fiap.gs_java.exception.EmailNotFoundException;
import br.com.fiap.gs_java.exception.InvalidPasswordException;
import br.com.fiap.gs_java.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public Token login(Credentials credentials) {
        var usuario = usuarioRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new EmailNotFoundException(credentials.email()));


        if (!passwordEncoder.matches(credentials.senha(), usuario.getSenha())) {
            throw new InvalidPasswordException();
        }

        return tokenService.createToken(credentials); // Gera o token usando o TokenService
    }
}
