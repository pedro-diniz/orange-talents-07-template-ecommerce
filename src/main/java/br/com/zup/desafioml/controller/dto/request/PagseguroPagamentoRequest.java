package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.config.validation.UniqueValue;
import br.com.zup.desafioml.config.validation.ValueOfEnum;
import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.Pagamento;
import br.com.zup.desafioml.model.StatusRetornoPagamento;

import javax.validation.constraints.NotBlank;

public class PagseguroPagamentoRequest implements PagamentoRequest{

    @NotBlank
    @UniqueValue(domainClass = Pagamento.class, fieldName = "id_gateway_compra")
    private String idGatewayCompra;

    @NotBlank
    @ValueOfEnum(enumClass = StatusRetornoPagamento.class)
    private String statusRetornoPagamento;

    public String getIdGatewayCompra() {
        return idGatewayCompra;
    }

    public String getStatusRetornoPagamento() {
        return statusRetornoPagamento;
    }

    public Pagamento toModel(Compra compra) {

        return new Pagamento(
                idGatewayCompra,
                statusRetornoPagamento,
                compra
        );

    }

}
