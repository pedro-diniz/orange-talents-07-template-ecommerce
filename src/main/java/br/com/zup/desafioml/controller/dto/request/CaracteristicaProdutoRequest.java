package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.model.CaracteristicaProduto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CaracteristicaProduto toModel() {
        return new CaracteristicaProduto(
                nome,
                descricao
        );
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
