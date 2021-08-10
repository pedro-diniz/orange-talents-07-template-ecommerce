package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.Pagamento;

public interface EventoPosFalha {
    void processaFalhaPagamento(Pagamento pagamento);
}
