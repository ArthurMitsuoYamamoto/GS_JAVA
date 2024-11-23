package br.com.fiap.gs_java.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http, AuthorizationFilter authorizationFilter) throws Exception {
        // Desativa o CSRF (não recomendado para produção sem outras proteções)
        http.csrf(csrf -> csrf.disable());

        // Configuração das permissões de acesso
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/login").permitAll() // Permite acesso ao login
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Permite criar usuários
                .requestMatchers(HttpMethod.POST, "/paineis").permitAll() // Permite acessar paineis
                .requestMatchers(HttpMethod.POST, "/notificacoes").permitAll() // Permite acessar notificações
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated() // Qualquer outra requisição precisa de autenticação
        );

        // Adiciona o filtro de autorização antes do filtro de autenticação padrão
        http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);

        // Retorna a configuração do filtro de segurança
        return http.build();
    }

    // Configura o PasswordEncoder para a codificação de senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
