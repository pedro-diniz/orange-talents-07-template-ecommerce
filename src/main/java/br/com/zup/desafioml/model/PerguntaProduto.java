package br.com.zup.desafioml.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

@Entity
public class PerguntaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    private Instant instanteCriacao = Instant.now();

    @NotNull
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario usuarioPergunta;

    @NotNull
    @ManyToOne(targetEntity = Produto.class)
    private Produto produto;

    @NotNull
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario vendedor;

    public PerguntaProduto() {
    }

    public PerguntaProduto(Long id) {
        this.id = id;
    }

    public PerguntaProduto(String titulo, Usuario usuarioPergunta,
                           Produto produto, Usuario vendedor) {
        this.titulo = titulo;
        this.usuarioPergunta = usuarioPergunta;
        this.produto = produto;
        this.vendedor = vendedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntaProduto that = (PerguntaProduto) o;
        return id.equals(that.id) && titulo.equals(that.titulo) && instanteCriacao.equals(that.instanteCriacao)
                && usuarioPergunta.equals(that.usuarioPergunta) && produto.equals(that.produto)
                && vendedor.equals(that.vendedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, instanteCriacao, usuarioPergunta, produto, vendedor);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Instant getInstanteCriacao() {
        return instanteCriacao;
    }

    public Usuario getUsuarioPergunta() {
        return usuarioPergunta;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getVendedor() {
        return vendedor;
    }
}
