package br.com.zup.desafioml.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @NotNull
    @PastOrPresent
    private Instant instanteCadastro = Instant.now();

    public Usuario(String login, String senhaLimpa) {
        this.login = login;
        this.senha = encoda(senhaLimpa);
    }

    private String encoda(String senhaLimpa) {
        return new BCryptPasswordEncoder().encode(senhaLimpa);
    }

}
