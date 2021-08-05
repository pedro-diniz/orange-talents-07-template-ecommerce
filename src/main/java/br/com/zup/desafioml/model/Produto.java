package br.com.zup.desafioml.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max=1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    private Instant instanteCadastro = Instant.now();

    public Produto() {}

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProduto> caracteristicas, String descricao, Categoria categoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(Long id) {
        this.id = id;
    }
}