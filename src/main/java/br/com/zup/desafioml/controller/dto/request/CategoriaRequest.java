package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.config.validation.CheckIdExistence;
import br.com.zup.desafioml.config.validation.UniqueValue;
import br.com.zup.desafioml.model.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @CheckIdExistence(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoriaMae;

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria toModel() {
        Categoria categoria = new Categoria(nome);

        if (idCategoriaMae != null) {
            categoria.setCategoriaMae(new Categoria(idCategoriaMae));
        }

        return categoria;
    }

}
