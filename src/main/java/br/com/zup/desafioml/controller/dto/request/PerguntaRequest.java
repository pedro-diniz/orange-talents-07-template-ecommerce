package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.model.PerguntaProduto;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.model.Usuario;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public PerguntaProduto toModel(Authentication authentication, Produto produto) {
        return new PerguntaProduto(
                titulo,
                (Usuario) authentication.getPrincipal(),
                produto,
                produto.getDono()
        );
    }
}
