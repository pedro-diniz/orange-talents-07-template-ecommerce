package br.com.zup.desafioml.model;

import br.com.zup.desafioml.config.exception.NegocioException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "compra_id")
    private Set<Pagamento> pagamentos = new HashSet<>();

    public Compra(Produto produto, Integer quantidade, Usuario comprador,
                  BigDecimal valor, GatewayCompra gatewayCompra) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valor = valor;
        this.gatewayCompra = gatewayCompra;
    }

    public void setStatusCompra(StatusCompra statusCompra) {
        if (this.statusCompra != StatusCompra.CONCLUÍDA) {
            this.statusCompra = statusCompra;
        }
    }

    public Long getId() {
        return id;
    }

    public Compra(Long id) {
        this.id = id;
    }

    public Compra() {
    }

    public String geraUrlRetorno(UriComponentsBuilder uriComponentsBuilder) {
        return this.gatewayCompra.criaUrlRetorno(this, uriComponentsBuilder);
    }

    public boolean adicionaPagamento(Pagamento novoPagamento) {

        // Verifica se a compra não já foi concluída
        if (statusCompra != StatusCompra.CONCLUÍDA) {

            pagamentos.add(novoPagamento);

            if (Objects.equals(novoPagamento.getStatusRetornoPagamento(), "SUCESSO")
                    || Objects.equals(novoPagamento.getStatusRetornoPagamento(), "1")) {
                setStatusCompra(StatusCompra.CONCLUÍDA);
                return true;
            }
            else {
                setStatusCompra(StatusCompra.PENDENTE);
                return false;
            }
        }
        else {
            throw new NegocioException("Essa compra já foi concluída com sucesso.");
        }

    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", valor=" + valor +
                ", gatewayCompra=" + gatewayCompra +
                ", statusCompra=" + statusCompra +
                ", pagamentos=" + pagamentos +
                '}';
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public GatewayCompra getGatewayCompra() {
        return gatewayCompra;
    }

    public Usuario getComprador() {
        return comprador;
    }
}
