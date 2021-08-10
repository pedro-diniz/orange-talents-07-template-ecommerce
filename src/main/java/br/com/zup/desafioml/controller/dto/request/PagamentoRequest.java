package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.Pagamento;

public interface PagamentoRequest {

    Pagamento toModel(Compra compra);

}
