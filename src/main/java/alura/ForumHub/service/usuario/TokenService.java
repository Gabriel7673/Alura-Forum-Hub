package alura.ForumHub.service.usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import alura.ForumHub.domain.Usuario;

@Service
public class TokenService {
    
    private final String ISSUER = "Forum Hub";

    @Value("${forum-hub.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getEmail())
                .withExpiresAt(dataExpiracao())
                .sign(algoritmo);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao criar token JWT", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
