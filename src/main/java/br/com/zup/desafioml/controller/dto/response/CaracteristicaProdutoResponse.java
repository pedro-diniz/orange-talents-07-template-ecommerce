package br.com.zup.desafioml.controller.dto.response;

public class CaracteristicaProdutoResponse {

    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProdutoResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
