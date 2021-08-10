package br.com.zup.desafioml.controller.dto.request;

import javax.validation.constraints.NotNull;

public class RankingVendedorRequest {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idVendedor;

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    @Override
    public String toString() {
        return "RankingVendedorRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
