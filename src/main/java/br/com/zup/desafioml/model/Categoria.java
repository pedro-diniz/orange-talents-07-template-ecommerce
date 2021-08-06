package br.com.zup.desafioml.model;

import br.com.zup.desafioml.controller.dto.response.CategoriaProdutoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @ManyToOne // uma categoriaMae pode ter muitos filhos
    private Categoria categoriaMae;

    public Categoria() {
    }

    public Categoria(Long id) {
        this.id = id;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public CategoriaProdutoResponse toOutput() {
        return new CategoriaProdutoResponse(
                nome,
                categoriaMae
        );
    }
}
