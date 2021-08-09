package br.com.zup.desafioml.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = Produto.class)
    private Produto produto;

    @NotNull @Positive
    private Integer quantidade;

    @NotNull
    @ManyToOne(targetEntity = Usuario.class)
    private Usuario comprador;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayCompra gatewayCompra;

    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra = StatusCompra.INICIADA;

    public Compra(Produto produto, Integer quantidade, Usuario comprador,
                  BigDecimal valor, GatewayCompra gatewayCompra) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valor = valor;
        this.gatewayCompra = gatewayCompra;
    }

    public Long getId() {
        return id;
    }

    public Compra(Long id) {
        this.id = id;
    }

    public Compra() {
    }

}
