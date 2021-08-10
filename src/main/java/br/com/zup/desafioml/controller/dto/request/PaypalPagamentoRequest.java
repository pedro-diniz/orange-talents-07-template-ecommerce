package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.config.validation.UniqueValue;
import br.com.zup.desafioml.config.validation.ValueOfEnum;
import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.Pagamento;
import br.com.zup.desafioml.model.StatusRetornoPagamento;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PaypalPagamentoRequest implements PagamentoRequest{

    @NotBlank
    @UniqueValue(domainClass = Pagamento.class, fieldName = "id_gateway_compra")
    private String idGatewayCompra;

    @NotNull
    @Min(0) @Max(1)
    private int statusRetornoPagamento;

    public String getIdGatewayCompra() {
        return idGatewayCompra;
    }

    public int getStatusRetornoPagamento() {
        return statusRetornoPagamento;
    }

    public Pagamento toModel(Compra compra) {

        return new Pagamento(
                idGatewayCompra,
                String.valueOf(statusRetornoPagamento),
                compra
        );

    }

}
