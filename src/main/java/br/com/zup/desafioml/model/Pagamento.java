package br.com.zup.desafioml.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String idGatewayCompra;

    private String statusRetornoPagamento;

    @ManyToOne(targetEntity = Compra.class)
    private Compra compra;

    private Instant instanteCompra = Instant.now();

    public String getStatusRetornoPagamento() {
        return statusRetornoPagamento;
    }

    public String getIdGatewayCompra() {
        return idGatewayCompra;
    }

    public Pagamento() {
    }

    public Pagamento(String idGatewayCompra, String statusRetornoPagamento, Compra compra) {
        this.idGatewayCompra = idGatewayCompra;
        this.statusRetornoPagamento = statusRetornoPagamento;
        this.compra = compra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return idGatewayCompra.equals(pagamento.idGatewayCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGatewayCompra);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", idGatewayCompra='" + idGatewayCompra + '\'' +
                ", statusRetornoPagamento=" + statusRetornoPagamento +
                ", instanteCompra=" + instanteCompra +
                '}';
    }

    public Compra getCompra() {
        return compra;
    }

    public Instant getInstanteCompra() {
        return instanteCompra;
    }
}
