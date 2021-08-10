package br.com.zup.desafioml.controller.dto.request;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private Long idCompra;

    @NotNull
    private Long idPagante;

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdPagante() {
        return idPagante;
    }

    @Override
    public String toString() {
        return "NotaFiscalRequest{" +
                "idCompra=" + idCompra +
                ", idPagante=" + idPagante +
                '}';
    }
}
