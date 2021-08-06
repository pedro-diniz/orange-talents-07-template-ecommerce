package br.com.zup.desafioml.controller.dto.response;

import br.com.zup.desafioml.model.Categoria;

public class CategoriaProdutoResponse {

    private String nome;
    private Categoria categoriaMae;

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public CategoriaProdutoResponse(String nome, Categoria categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }
}
