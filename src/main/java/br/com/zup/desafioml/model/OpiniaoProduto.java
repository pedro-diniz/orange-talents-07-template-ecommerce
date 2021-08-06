package br.com.zup.desafioml.model;

import br.com.zup.desafioml.controller.dto.response.OpiniaoProdutoResponse;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @NotNull
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuario;

    @NotNull
    @ManyToOne(targetEntity = Produto.class)
    private Produto produto;

    public OpiniaoProduto() {
    }

    public OpiniaoProduto(Long id) {
        this.id = id;
    }

    public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpiniaoProduto that = (OpiniaoProduto) o;
        return id.equals(that.id) && nota.equals(that.nota) && titulo.equals(that.titulo) &&
                descricao.equals(that.descricao) && usuario.equals(that.usuario) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, titulo, descricao, usuario, produto);
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public OpiniaoProdutoResponse toOutput() {
        return new OpiniaoProdutoResponse(
                nota,
                titulo,
                descricao
        );
    }
}
