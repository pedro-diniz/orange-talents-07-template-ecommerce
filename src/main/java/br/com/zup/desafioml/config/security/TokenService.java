package br.com.zup.desafioml.config.security;

import br.com.zup.desafioml.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${desafio-ml.jwt.expiration}") // pega essa propriedade lá no application.properties
    private String expiration;

    @Value("${desafio-ml.jwt.secret}") // pega essa propriedade lá no application.properties
    private String secret;


    public String gerarToken(Authentication authentication) {

        // cria o token a partir dos atributos no retorno
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date agora = new Date();
        Date validadeToken = new Date(agora.getTime() + Long.parseLong(expiration));

        // de atributos que o infundidos e criptografados, como
        return Jwts.builder()
                .setIssuer("Desafio do Mercado Livre") // emissor
                .setSubject(usuarioLogado.getId().toString()) // id do usuário logado
                .setIssuedAt(agora) // data de emissão
                .setExpiration(validadeToken) // data de expiração
                .signWith(SignatureAlgorithm.HS256, secret) // criptografia da chave secreta
                .compact();

    }

    // valida o token a partir da chave secreta
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    // recupera o ID do usuário a partir do token
    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
