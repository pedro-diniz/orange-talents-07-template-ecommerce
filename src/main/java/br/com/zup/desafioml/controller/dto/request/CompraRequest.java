package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.config.validation.ValueOfEnum;
import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.GatewayCompra;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.model.Usuario;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @NotNull
    @Positive
    private Integer quantidade;

    @NotBlank
    @ValueOfEnum(enumClass = GatewayCompra.class)
    private String gateway;

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getGateway() {
        return gateway;
    }

    public Compra toModel(Authentication authentication, Produto produto) {
        return new Compra (
                produto,
                quantidade,
                (Usuario) authentication.getPrincipal(),
                produto.getValor(),
                GatewayCompra.valueOf(gateway.trim().toUpperCase())
        );
    }

}
