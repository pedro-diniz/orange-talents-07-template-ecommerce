package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.config.validation.CheckIdExistence;
import br.com.zup.desafioml.model.OpiniaoProduto;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.model.Usuario;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotNull
    @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public OpiniaoRequest() {
    }

    public OpiniaoProduto toModel(Authentication authentication, Long idProduto) {
        return new OpiniaoProduto(
                nota,
                titulo,
                descricao,
                ((Usuario) authentication.getPrincipal()),
                new Produto(idProduto)
        );
    }
}
