package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.model.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6, message = "a senha deve ter no mínimo 6 dígitos")
    private String senhaLimpa;

    public Usuario toModel() {
        return new Usuario(login, senhaLimpa);
    }

    public String getLogin() {
        return login;
    }

    public String getSenhaLimpa() {
        return senhaLimpa;
    }

    public UsernamePasswordAuthenticationToken converterParaUPAT() {
        return new UsernamePasswordAuthenticationToken(login, senhaLimpa);
    }

}
