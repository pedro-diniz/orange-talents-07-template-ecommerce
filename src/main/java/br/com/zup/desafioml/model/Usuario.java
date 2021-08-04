package br.com.zup.desafioml.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.Collection;

@Entity
public class Usuario implements UserDetails {

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

    public Long getId() {
        return id;
    }

    public Usuario() {
    }

    private String encoda(String senhaLimpa) {
        return new BCryptPasswordEncoder().encode(senhaLimpa);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
