package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.config.security.TokenService;
import br.com.zup.desafioml.controller.dto.request.LoginRequest;
import br.com.zup.desafioml.controller.dto.response.TokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping // recebe login e senha, devolve o token
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converterParaUPAT();

        try {
            Authentication authentication = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);

            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        }
        catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
